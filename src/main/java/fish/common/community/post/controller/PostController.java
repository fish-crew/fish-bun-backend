package fish.common.community.post.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import fish.common.community.post.dto.response.PostDetailResponse;
import fish.common.community.post.dto.response.PostResponse;
import fish.common.community.post.service.PostService;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/community")
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
    public ResponseEntity<ResponseUtil<PostDetailResponse>> findPost(@PathVariable Long postId) throws JsonProcessingException {
        return ResponseEntity.ok(ResponseUtil.success(postService.findPost(postId)));
    }



}
