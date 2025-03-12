package fish.common.store.likes.controller;

import fish.common.store.likes.entity.StoreLikesEntity;
import fish.common.store.likes.service.StoreLikesService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/store-likes")
@Tag(name = "붕어빵 가게 API")
public class StoreLikesController {
    private final StoreLikesService storeLikesService;

    @Operation(summary = "가게 찜하기")
    @PostMapping("{storeId}")
    public ResponseUtil<?> save(@PathVariable Long storeId, @AuthenticationPrincipal User user) {
        StoreLikesEntity entity = StoreLikesEntity.toEntity(user.getId(), storeId);
        storeLikesService.save(entity);
        return ResponseUtil.success();
    }
}