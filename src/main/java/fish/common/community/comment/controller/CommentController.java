package fish.common.community.comment.controller;

import fish.common.community.comment.dto.request.CommentRequest;
import fish.common.community.comment.service.CommentService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/community")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/{postId}/comment/save")
    public ResponseEntity<ResponseUtil> save(@PathVariable Long postId, @RequestBody CommentRequest request, @AuthenticationPrincipal User user) {
        commentService.saveComment(postId, request, user);
        return ResponseEntity.ok(ResponseUtil.success());
    }

}
