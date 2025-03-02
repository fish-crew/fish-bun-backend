package fish.common.community.comment.controller;

import fish.common.community.comment.dto.request.CommentRequest;
import fish.common.community.comment.dto.response.CommentResponse;
import fish.common.community.comment.service.CommentService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/community")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/{postId}/comment/save")
    public ResponseEntity<ResponseUtil<Long>> save(@PathVariable Long postId, @RequestBody CommentRequest request, @AuthenticationPrincipal User user) {
        Long id = commentService.saveComment(postId, request, user);
        return ResponseEntity.ok(ResponseUtil.success(id));
    }


    @GetMapping(value = "/{postId}/comments")
    public ResponseEntity<ResponseUtil<List<CommentResponse>>> findAllComments(@PathVariable Long postId) {
        return ResponseEntity.ok(ResponseUtil.success(commentService.findAllComments(postId)));
    }

    @PatchMapping(value = "/comments/{commentId}")
    public ResponseEntity<ResponseUtil> modifyComment(@PathVariable Long commentId, @RequestBody CommentRequest request) {
        commentService.modifyComment(commentId, request);
        return ResponseEntity.ok(ResponseUtil.success());
    }
}
