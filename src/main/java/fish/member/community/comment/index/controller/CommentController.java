package fish.member.community.comment.index.controller;

import fish.member.community.comment.index.dto.request.CommentRequest;
import fish.member.community.comment.index.dto.response.CommentResponse;
import fish.member.community.comment.index.service.CommentService;
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
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 등록")
    @Parameter(name = "postId", description = "게시글 고유 ID")
    @PostMapping(value = "/{postId}/comment")
    public ResponseEntity<ResponseUtil<Long>> save(@PathVariable Long postId, @RequestBody CommentRequest request, @AuthenticationPrincipal User user) {
        Long id = commentService.saveComment(postId, request, user);
        return ResponseEntity.ok(ResponseUtil.success(id));
    }

    @Operation(summary = "댓글 조회")
    @Parameter(name = "postId", description = "게시글 고유 ID")
    @GetMapping(value = "/{postId}/comments")
    public ResponseEntity<ResponseUtil<List<CommentResponse>>> findAllComments(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(ResponseUtil.success(commentService.findAllComments(postId, user.getId())));
    }

    @Operation(summary = "댓글 수정")
    @Parameter(name = "commentId", description = "댓글 고유 ID")
    @PatchMapping(value = "/comments/{commentId}")
    public ResponseEntity<ResponseUtil> modifyComment(@PathVariable Long commentId, @RequestBody CommentRequest request) {
        commentService.modifyComment(commentId, request);
        return ResponseEntity.ok(ResponseUtil.success());
    }

    @Operation(summary = "댓글 삭제")
    @Parameter(name = "commentId", description = "댓글 고유 ID")
    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<ResponseUtil> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(ResponseUtil.success());
    }
}