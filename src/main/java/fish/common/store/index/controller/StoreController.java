package fish.common.store.index.controller;

import fish.common.store.index.dto.request.StoreRegRequest;
import fish.common.store.index.dto.request.StoreSearchRequest;
import fish.common.store.index.dto.response.StoreResponse;
import fish.common.store.index.entity.StoreEntity;
import fish.common.store.index.service.StoreService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public ResponseUtil<?> save(@RequestBody StoreRegRequest request, @AuthenticationPrincipal User user) {
        StoreEntity entity = StoreEntity.toEntity(request, user.getId(), null);
        storeService.save(entity);
        return ResponseUtil.success();
    }

    @GetMapping
    public ResponseUtil<List<StoreResponse>> findAll(@RequestBody StoreSearchRequest request) {
        return ResponseUtil.success(storeService.findAllByBounds(request));
    }

    @GetMapping("{storeId}")
    public ResponseUtil<StoreResponse> findOne(@PathVariable Long storeId) {
        return ResponseUtil.success(storeService.findById(storeId));
    }

    @DeleteMapping("{storeId}")
    public ResponseUtil<?> delete(@PathVariable Long storeId) {
        storeService.delete(storeId);
        return ResponseUtil.success();
    }

    @PatchMapping("{storeId}")
    public ResponseUtil<?> update(@PathVariable Long storeId, @RequestBody StoreRegRequest request
            , @AuthenticationPrincipal User user) {
        StoreEntity entity = StoreEntity.toEntity(request, user.getId(), storeId);
        storeService.modify(entity);
        return ResponseUtil.success();
    }
}
