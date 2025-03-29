package fish.common.store.index.controller;

import fish.common.store.index.dto.request.StoreRegRequest;
import fish.common.store.index.dto.request.StoreSearchRequest;
import fish.common.store.index.dto.response.StoreResponse;
import fish.common.store.index.entity.StoreEntity;
import fish.common.store.index.service.StoreService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fish-bun/store")
@Tag(name = "붕어빵 가게 API")
public class StoreController {
    private final StoreService storeService;

    @Operation(summary = "가게 등록")
    @PostMapping
    public ResponseUtil<?> save(@RequestBody StoreRegRequest request, @AuthenticationPrincipal User user) {
        StoreEntity entity = StoreEntity.toEntity(request, user.getId(), null);
        storeService.save(entity);
        return ResponseUtil.success();
    }

    @Operation(summary = "가게 조회")
    @GetMapping
    public ResponseUtil<List<StoreResponse>> findAll(@ModelAttribute StoreSearchRequest request
            , @AuthenticationPrincipal User user) {
        return ResponseUtil.success(storeService.findAllByBounds(request, user.getId()));
    }

    @Operation(summary = "가게 상세 조회")
    @GetMapping("{storeId}")
    public ResponseUtil<StoreResponse> findOne(@PathVariable Long storeId, @AuthenticationPrincipal User user) {
        return ResponseUtil.success(storeService.findById(storeId, user.getId()));
    }

    @Operation(summary = "가게 삭제")
    @DeleteMapping("{storeId}")
    public ResponseUtil<?> delete(@PathVariable Long storeId) {
        storeService.delete(storeId);
        return ResponseUtil.success();
    }

    @Operation(summary = "가게 수정")
    @PatchMapping("{storeId}")
    public ResponseUtil<?> update(@PathVariable Long storeId, @RequestBody StoreRegRequest request
            , @AuthenticationPrincipal User user) {
        StoreEntity entity = StoreEntity.toEntity(request, user.getId(), storeId);
        storeService.modify(entity);
        return ResponseUtil.success();
    }
}