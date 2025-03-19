package fish.common.community.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.community.post.dto.request.PostReportRequest;
import fish.common.community.post.dto.request.PostRequest;
import fish.common.community.post.entity.PostEntity;
import fish.common.community.post.entity.PostReportEntity;
import fish.common.community.post.repository.PostReportRepository;
import fish.common.community.post.repository.PostRepository;
import fish.common.community.post.dto.response.PostDetailResponse;
import fish.common.community.post.dto.response.PostResponse;
import fish.common.community.vote.repository.VoteRepository;
import fish.common.file.entity.FileEntity;
import fish.common.file.service.FileService;
import fish.global.util.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final PostReportRepository postReportRepository;
    private final VoteRepository voteRepository;
    private final FileService fileService;


    public List<PostResponse> findPostList() {
        List<PostResponse> responses = new ArrayList<>();
        List<PostEntity> posts = postRepository.findAll();
        for (PostEntity post : posts) {
            int voteCount = voteRepository.findTotalVoteCountByPostId(post.getId());
            responses.add(PostResponse.toResponse(post, voteCount));
        }

        return responses;
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

    public String getJsonFileIdList(List<MultipartFile> pictures) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Long> fileIdList = new ArrayList<>();

        for (MultipartFile pic : pictures) {
            if (pic.isEmpty() || pic.getSize() == 0) continue;

            FileEntity fileEntity = FileUtils.fileUpload(pic, "community");
            fileService.save(fileEntity);
            fileIdList.add(fileEntity.getId());
        }

        return objectMapper.writeValueAsString(fileIdList);
    }

    public Long savePost(PostEntity post, List<MultipartFile> pictures) throws IOException {
        String jsonFileIdList = getJsonFileIdList(pictures);
        post.setFileIdList(jsonFileIdList);

        return postRepository.save(post).getId();
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void updatePost(Long postId, PostRequest request, List<MultipartFile> pictures) throws IOException {
        PostEntity entity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post data not found with id: " + postId));

        String jsonFileIdList = getJsonFileIdList(pictures);
        if (!jsonFileIdList.equals("[]")) {
            entity.updateFileIdList(jsonFileIdList);
        }
        entity.modify(request);
    }

    @Transactional
    public void savePostReport(PostReportRequest request, Long userId) {
        PostReportEntity entity = PostReportEntity.toEntity(request, userId);
        postReportRepository.save(entity);
    }
}
