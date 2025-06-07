package fish.member.community.post.index.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.member.community.post.index.dto.PostRequest;
import fish.domain.community.post.index.Post;
import fish.domain.community.post.report.PostReportRepository;
import fish.domain.community.post.index.PostRepository;
import fish.member.community.post.index.dto.PostDetailResponse;
import fish.member.community.post.index.dto.PostResponse;
import fish.domain.community.vote.VoteRepository;
import fish.domain.diary.file.BungDiaryFile;
import fish.member.diary.file.service.FileService;
import fish.common.util.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostReportRepository postReportRepository;
    private final VoteRepository voteRepository;
    private final FileService fileService;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public List<PostResponse> findPostList() {
        List<PostResponse> responses = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            int voteCount = voteRepository.findTotalVoteCountByPostId(post.getId());
            responses.add(PostResponse.toResponse(post, voteCount));
        }

        return responses;
    }

    public PostDetailResponse findPost(Long postId, Long userId) throws JsonProcessingException {
        Map<String, Object> post = postRepository.getStatsByPostId(userId, postId);
        List<Long> fileIdList = objectMapper.readValue(post.get("fileIdList").toString(), new TypeReference<List<Long>>() {});
        List<String> fileUrls = fileIdList.stream()
                .map(fileService::findFileById)
                .map(fileEntity -> fileService.getFileUrl(fileEntity.getId()))
                .toList();

        return PostDetailResponse.toResponse(post, fileUrls);
    }

    public Long savePost(Post post, List<MultipartFile> pictures) throws IOException {
        String jsonFileIdList = getJsonFileIdList(pictures);
        post.setFileIdList(jsonFileIdList);

        return postRepository.save(post).getId();
    }

    private String getJsonFileIdList(List<MultipartFile> pictures) throws IOException {
        List<Long> fileIdList = new ArrayList<>();

        for (MultipartFile pic : pictures) {
            if (pic.isEmpty() || pic.getSize() == 0) continue;

            BungDiaryFile bungDiaryFile = FileUtils.fileUpload(pic, "community");
            fileService.save(bungDiaryFile);
            fileIdList.add(bungDiaryFile.getId());
        }

        return objectMapper.writeValueAsString(fileIdList);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void updatePost(Long postId, PostRequest request, List<MultipartFile> pictures) throws IOException {
        Post entity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post data not found with id: " + postId));

        String jsonFileIdList = getJsonFileIdList(pictures);
        if (!jsonFileIdList.equals("[]")) {
            entity.updateFileIdList(jsonFileIdList);
        }
        entity.modify(request);
    }

    /* admin service */
    /**
     * admin전용 Service (UserId로 핸들링 안해도 됨)
     * */

    public PostDetailResponse findPost(Long postId) throws JsonProcessingException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post Entity not founded with Id: " + postId));
        List<Long> fileIdList = Arrays.asList(objectMapper.readValue(post.getFileIdList(), Long[].class));
        List<String> fileUrls = fileIdList.stream()
                .map(fileService::findFileById)
                .map(fileEntity -> fileService.getFileUrl(fileEntity.getId()))
                .toList();

        return PostDetailResponse.toResponse(post, fileUrls);
    }
}
