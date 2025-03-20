package fish.common.store.index.service;

import fish.common.calendar.dto.response.CalendarDetailResponse;
import fish.common.detail.entity.DetailEntity;
import fish.common.detail.service.DetailService;
import fish.common.file.service.FileService;
import fish.common.store.index.dto.request.StoreSearchRequest;
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
        List<DetailEntity> details = detailService.findByStoreId(storeId);
        return StoreResponse.toResponse(data, convertDomain(details));
    }

    /**
     * 가게와 매핑된 N개의 일지 데이터를 가져다
     * 추가적으로 fileUrl이 추가적으로 들어가야 해서 일지상세 응답값을 재활용함
     * 붕어빵 등록 데이터 -> 일지 상세 응답값으로 변환 메소드
     * */
    private List<CalendarDetailResponse> convertDomain(List<DetailEntity> details) {
        List<CalendarDetailResponse> result = new ArrayList<>();
        for (DetailEntity detail : details) {
            String fileUrl = fileService.getFileUrl(detail.getFileId());
            CalendarDetailResponse response = CalendarDetailResponse.toResDTO(detail, fileUrl);
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