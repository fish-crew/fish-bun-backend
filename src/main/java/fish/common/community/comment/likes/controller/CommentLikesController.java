package fish.common.community.comment.likes.controller;

import fish.common.community.comment.likes.service.CommentLikesService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/community/comment-likes")
public class CommentLikesController {
        private final CommentLikesService commentLikesService;

        @PostMapping(value = "{commentId}")
        public ResponseUtil<?> save(@PathVariable Long commentId, @AuthenticationPrincipal User user) {
                commentLikesService.saveCommentLike(commentId, user.getId());
                return ResponseUtil.success();
        }
}
