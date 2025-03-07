package fish.common.store.index.controller;

import fish.common.store.index.entity.StoreLikesEntity;
import fish.common.store.index.service.StoreLikesService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/store-likes")
public class StoreLikesController {
    private final StoreLikesService storeLikesService;

    @PostMapping("{storeId}")
    public ResponseUtil<?> save(@PathVariable Long storeId, @AuthenticationPrincipal User user) {
        StoreLikesEntity entity = StoreLikesEntity.toEntity(user.getId(), storeId);
        storeLikesService.save(entity);
        return ResponseUtil.success();
    }
}
