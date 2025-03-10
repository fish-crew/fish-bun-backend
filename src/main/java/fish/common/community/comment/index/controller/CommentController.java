package fish.common.community.comment.index.controller;

import fish.common.community.comment.index.dto.request.CommentRequest;
import fish.common.community.comment.index.dto.response.CommentResponse;
import fish.common.community.comment.index.service.CommentService;
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

    @PostMapping(value = "/{postId}/comment")
    public ResponseEntity<ResponseUtil<Long>> save(@PathVariable Long postId, @RequestBody CommentRequest request, @AuthenticationPrincipal User user) {
        Long id = commentService.saveComment(postId, request, user);
        return ResponseEntity.ok(ResponseUtil.success(id));
    }


    @GetMapping(value = "/{postId}/comments")
    public ResponseEntity<ResponseUtil<List<CommentResponse>>> findAllComments(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(ResponseUtil.success(commentService.findAllComments(postId, user.getId())));
    }

    @PatchMapping(value = "/comments/{commentId}")
    public ResponseEntity<ResponseUtil> modifyComment(@PathVariable Long commentId, @RequestBody CommentRequest request) {
        commentService.modifyComment(commentId, request);
        return ResponseEntity.ok(ResponseUtil.success());
    }

    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<ResponseUtil> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(ResponseUtil.success());
    }
}