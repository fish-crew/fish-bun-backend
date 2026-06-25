package fish.common.store.index.service;

import fish.common.detail.service.DetailService;
import fish.common.file.service.FileService;
import fish.common.store.index.dto.request.StoreSearchRequest;
import fish.common.store.index.dto.response.StoreDetailResponse;
import fish.common.store.index.dto.response.StoreResponse;
import fish.common.store.index.entity.StoreEntity;
import fish.common.store.index.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final DetailService detailService;
    private final FileService fileService;

    public void save(StoreEntity entity) {
        storeRepository.save(entity);
    }

    public List<StoreResponse> findAllByBounds(StoreSearchRequest request, Long userId) {
        List<Map<String, Object>> stores = storeRepository
                .findByLatLngBounds(
                        request.getMinLat(),
                        request.getMaxLat(),
                        request.getMinLng(),
                        request.getMaxLng(),
                        userId
                );
        return StoreResponse.toResponseList(stores);
    }

    public StoreResponse findById(Long storeId, Long userId) {
        Map<String, Object> data = storeRepository.findByStoreId(storeId, userId);
        List<Map<String, Object>> details;
        try {
            details = detailService.findByStoreId(storeId);
        } catch (Exception e) {
            details = List.of();
        }
        return StoreResponse.toResponse(data, convertDomain(details));
    }

    private List<StoreDetailResponse> convertDomain(List<Map<String, Object>> details) {
        List<StoreDetailResponse> result = new ArrayList<>();
        for (Map<String, Object> detail : details) {
            String fileUrl = fileService.getFileUrl(Long.parseLong(detail.get("fileId").toString()));
            StoreDetailResponse response = StoreDetailResponse.toResDTO(detail, fileUrl);
            result.add(response);
        }
        return result;
    }

    public void delete(Long storeId) {
        storeRepository.deleteById(storeId);
    }

    public void modify(StoreEntity entity) {
        StoreEntity storeEntity = storeRepository.findById(entity.getId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        storeEntity.modifyDetail(entity);
        storeRepository.save(storeEntity);
    }
}
