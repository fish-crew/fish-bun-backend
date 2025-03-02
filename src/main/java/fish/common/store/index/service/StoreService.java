package fish.common.store.index.service;

import fish.common.store.index.dto.request.StoreSearchRequest;
import fish.common.store.index.dto.response.StoreResponse;
import fish.common.store.index.entity.StoreEntity;
import fish.common.store.index.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public void save(StoreEntity entity) {
        storeRepository.save(entity);
    }

    public List<StoreResponse> findAllByBounds(StoreSearchRequest request) {
        List<Map<String, Object>> stores = storeRepository
                .findByLatLngBounds(request.getMinLat(), request.getMaxLat(), request.getMinLng(), request.getMaxLng());
        return StoreResponse.toResponseList(stores);
    }

    public StoreResponse findById(Long storeId) {
        Map<String, Object> data = storeRepository.findByStoreId(storeId);
        return StoreResponse.toResponse(data);
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
