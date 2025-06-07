package fish.member.community.post.index.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import fish.member.community.post.index.dto.PostDetailResponse;
import fish.member.community.post.index.dto.PostResponse;
import fish.member.community.post.index.service.PostService;
import fish.domain.user.index.User;
import fish.common.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member/community")
@Tag(name = "커뮤니티 API")
public class PostController {
    private final PostService postService;

    @Operation(summary = "게시글 리스트 조회")
    @GetMapping()
    public ResponseEntity<ResponseUtil<List<PostResponse>>> findPostList() {
        return ResponseEntity.ok(ResponseUtil.success(postService.findPostList()));
    }

    @Operation(summary = "단일 게시글 상세 조회")
    @Parameter(name = "postId", description = "게시글 고유 ID")
    @GetMapping(value = "/{postId}")
    public ResponseEntity<ResponseUtil<PostDetailResponse>> findPost(@PathVariable Long postId
            , @AuthenticationPrincipal User user) throws JsonProcessingException {
        return ResponseEntity.ok(ResponseUtil.success(postService.findPost(postId, user.getId())));
    }
}
