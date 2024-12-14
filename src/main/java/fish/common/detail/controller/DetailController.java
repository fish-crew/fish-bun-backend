package fish.common.detail.controller;

import fish.common.detail.dto.DetailRequest;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.service.DetailService;
import fish.core.oauth.dto.AuthUserInfo;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fish-bun/detail")
public class DetailController {
    private final DetailService mainService;

    @PostMapping(value="/save", consumes = {"multipart/form-data"})
    public ResponseEntity save(@ModelAttribute DetailRequest request,
                     @AuthenticationPrincipal AuthUserInfo userInfo) throws IOException {
        DetailEntity entity = request.toEntity(request, userInfo.getUser().getId());
        mainService.save(entity, request.getPicture());
        return ResponseEntity.ok(ResponseUtil.success());
    }
}
