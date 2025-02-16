package fish.common.community.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.community.post.entity.PostEntity;
import fish.common.community.post.repository.PostRepository;
import fish.common.community.post.dto.response.PostDetailResponse;
import fish.common.community.post.dto.response.PostResponse;
import fish.common.file.entity.FileEntity;
import fish.common.file.service.FileService;
import fish.global.util.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    @Value("${file-uri}")
    private String fileUri;

    private final PostRepository postRepository;
    private final FileService fileService;



    public List<PostResponse> findPostList() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(PostResponse::toResponse).toList();
    }

    public PostDetailResponse findPost(Long postId) throws JsonProcessingException {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post Entity not founded with Id: " + postId));

        ObjectMapper objectMapper = new ObjectMapper();
        List<Long> fileIdList = Arrays.asList(objectMapper.readValue(post.getFileIdList(), Long[].class));
        List<String> fileUrls = fileIdList.stream()
                .map(fileService::findFileById)
                .map(fileEntity -> fileUri + fileEntity.getFilePath() + fileEntity.getSystemFileName())
                .toList();

        return PostDetailResponse.toResponse(post, fileUrls);
    }

    public Long savePost(PostEntity post, List<MultipartFile> pictures) throws IOException {
        List<Long> fileIdList = new ArrayList<>();
        for (MultipartFile pic : pictures) {
            FileEntity fileEntity = FileUtils.fileUpload(pic, "community");
            fileService.save(fileEntity);
            fileIdList.add(fileEntity.getId());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFileIdList = objectMapper.writeValueAsString(fileIdList);
        post.setFileIdList(jsonFileIdList);

        return postRepository.save(post).getId();
    }
}
