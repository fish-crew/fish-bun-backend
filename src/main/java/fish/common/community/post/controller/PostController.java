package fish.common.community.post.controller;


import fish.common.community.post.response.PostResponse;
import fish.common.community.post.service.PostService;
import fish.global.util.ResponseUtil;
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
public class PostController {
    private final PostService postService;

    @GetMapping()
    public ResponseEntity<ResponseUtil<List<PostResponse>>> findPostList() {
        return ResponseEntity.ok(ResponseUtil.success(postService.findPostList()));
    }

    @GetMapping(value = "/{postId}")
    public ResponseEntity<ResponseUtil<PostResponse>> findPost(@PathVariable Long postId) {
        return ResponseEntity.ok(ResponseUtil.success(postService.findPost(postId)));
    }



}
