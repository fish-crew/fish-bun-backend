package fish.common.book.index.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.book.rating.dto.UserBookRatingDetail;
import fish.common.book.index.dto.UserBookDateDetail;
import fish.common.book.index.entity.UserBookEntity;
import fish.common.book.index.repository.UserBookRepository;
import fish.common.book.index.dto.response.UserBookDetailResponse;
import fish.common.book.index.dto.response.UserBookResponse;
import fish.common.flavor.entity.FishBunFlavorEntity;
import fish.common.flavor.repository.FlavorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserBookRepository userBookRepository;
    private final FlavorRepository flavorRepository;

    public List<UserBookResponse> findAllUserFishBunBook(Long userId) {
        return userBookRepository.findAllByUserId(userId).stream()
                .map((userBookEntity) -> {
                    Long flavorId = userBookEntity.getCompletedFlavorId();
                    FishBunFlavorEntity flavor = flavorRepository.findById(flavorId).orElseThrow(() -> new EntityNotFoundException("FishBun Flavor Entity not found with ID: " + flavorId));
                    return UserBookResponse.toResponseDTO(userBookEntity, flavor);
                })
                .collect(Collectors.toList());
    }

    public void saveUserCompletedFlavor(List<Long> flavorIdList, Long userId) {
        List<Long> completedFlavorIdList = userBookRepository.findAllByUserId(userId).stream()
                .map(UserBookEntity::getCompletedFlavorId)
                .toList();

        // Save the newly tried flavor as a completed flavor ID
        for (Long id : flavorIdList) {
            if (!completedFlavorIdList.contains(id)) {
                userBookRepository.save(UserBookEntity.toEntity(userId, id));
            }
        }
    }

    public UserBookDetailResponse findUserBookDetail(Long userId, Long flavorId) {
        List<String> list = userBookRepository.findUserBookDetail(userId, flavorId);
        Map<String, Object> ratingDetail = userBookRepository.findUserBookRatingDetail(userId, flavorId);
        return convertToResponse(list, ratingDetail);
    }

    private UserBookDetailResponse convertToResponse(List<String> rawData, Map<String, Object> ratingDetailMap) {
        List<UserBookDateDetail> dateList = rawData.stream()
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, UserBookDateDetail.class);
                    } catch (Exception e) {
                        throw new RuntimeException("JSON 변환 실패: " + json, e);
                    }
                })
                .collect(Collectors.toList());
        //붕어빵 정보 +
        UserBookRatingDetail ratingDetail = objectMapper.convertValue(ratingDetailMap, UserBookRatingDetail.class);
        return new UserBookDetailResponse(dateList, ratingDetail);
    }
}