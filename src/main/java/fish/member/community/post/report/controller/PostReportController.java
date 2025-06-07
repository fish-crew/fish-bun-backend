package fish.member.community.post.report.controller;

import fish.common.util.ResponseUtil;
import fish.domain.user.index.User;
import fish.member.community.post.report.dto.PostReportRequest;
import fish.member.community.post.report.service.PostReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member/community/post")
@Tag(name = "커뮤니티 API")
public class PostReportController {
    private final PostReportService postReportService;
    @Operation(summary = "주제 추천")
    @PostMapping(value = "/report")
    public ResponseEntity<?> report(@RequestBody PostReportRequest request, @AuthenticationPrincipal User user) {
        postReportService.savePostReport(request, user.getId());

        return ResponseEntity.ok(ResponseUtil.success());
    }
}
