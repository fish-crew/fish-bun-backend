package fish.user.community.comment.likes.controller;

import fish.user.community.comment.likes.service.CommentLikesService;
import fish.user.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/community/comment-likes")
@Tag(name = "커뮤니티 API")
public class CommentLikesController {
        private final CommentLikesService commentLikesService;

        @Operation(summary = "댓글 좋아요 추가")
        @Parameter(name = "commentId", description = "댓글 고유 ID")
        @PostMapping(value = "{commentId}")
        public ResponseUtil<?> save(@PathVariable Long commentId, @AuthenticationPrincipal User user) {
                commentLikesService.saveCommentLike(commentId, user.getId());
                return ResponseUtil.success();
        }
}
