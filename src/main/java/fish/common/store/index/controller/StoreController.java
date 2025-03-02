package fish.common.store.index.controller;

import fish.common.store.index.dto.request.StoreRequest;
import fish.common.store.index.entity.StoreEntity;
import fish.common.store.index.service.StoreService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/save")
    public ResponseUtil<?> save(@RequestBody StoreRequest request, @AuthenticationPrincipal User user) {
        StoreEntity entity = StoreEntity.toEntity(request, user.getId());
        storeService.save(entity);
        return ResponseUtil.success();
    }
}
