package fish.common.detail.controller;

import fish.common.detail.dto.DetailRequest;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.service.DetailService;
import fish.common.user.entity.User;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/detail")
public class DetailController {
    private final DetailService mainService;

    @PostMapping(value="/save", consumes = {"multipart/form-data"})
    public ResponseEntity save(@ModelAttribute DetailRequest request,
                     @AuthenticationPrincipal User user) throws IOException {
        DetailEntity entity = request.toEntity(request, user.getId());
        Long id = mainService.save(entity, request.getPicture());
        return ResponseEntity.ok(ResponseUtil.success(id));
    }
}
