package fish.member.community.post.report.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.domain.community.post.index.Post;
import fish.domain.community.post.index.PostRepository;
import fish.domain.community.post.report.PostReport;
import fish.domain.community.post.report.PostReportRepository;
import fish.member.community.post.index.dto.PostDetailResponse;
import fish.member.community.post.report.dto.PostReportRequest;
import fish.domain.community.vote.VoteRepository;
import fish.member.diary.file.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReportService {

    private final PostRepository postRepository;
    private final PostReportRepository postReportRepository;
    private final FileService fileService;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Transactional
    public void savePostReport(PostReportRequest request, Long userId) {
        PostReport entity = PostReport.toEntity(request, userId);
        postReportRepository.save(entity);
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
