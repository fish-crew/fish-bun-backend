package fish.member.book.index.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fish.member.book.rating.dto.BookRatingResponse;
import fish.member.book.index.dto.BookDateDetail;
import fish.domain.book.BungDiaryBook;
import fish.domain.book.BungDiaryBookRepository;
import fish.member.book.index.dto.BookDetailResponse;
import fish.member.book.index.dto.BookResponse;
import fish.domain.flavor.index.BungFlavorEntity;
import fish.domain.flavor.index.BungFlavorRepository;
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
    private final BungDiaryBookRepository bungDiaryBookRepository;
    private final BungFlavorRepository bungFlavorRepository;

    public List<BookResponse> findAllUserFishBunBook(Long userId) {
        return bungDiaryBookRepository.findAllByUserId(userId).stream()
                .map((userBookEntity) -> {
                    Long flavorId = userBookEntity.getCompletedFlavorId();
                    BungFlavorEntity flavor = bungFlavorRepository.findById(flavorId).orElseThrow(() -> new EntityNotFoundException("FishBun Flavor Entity not found with ID: " + flavorId));
                    return BookResponse.toResponseDTO(userBookEntity, flavor);
                })
                .collect(Collectors.toList());
    }

    public void saveUserCompletedFlavor(List<Long> flavorIdList, Long userId) {
        List<Long> completedFlavorIdList = bungDiaryBookRepository.findAllByUserId(userId).stream()
                .map(BungDiaryBook::getCompletedFlavorId)
                .toList();

        // Save the newly tried flavor as a completed flavor ID
        for (Long id : flavorIdList) {
            if (!completedFlavorIdList.contains(id)) {
                bungDiaryBookRepository.save(BungDiaryBook.toEntity(userId, id));
            }
        }
    }

    public BookDetailResponse findUserBookDetail(Long userId, Long flavorId) {
        List<String> list = bungDiaryBookRepository.findUserBookDetail(userId, flavorId);
        Map<String, Object> ratingDetail = bungDiaryBookRepository.findUserBookRatingDetail(userId, flavorId);
        return convertToResponse(list, ratingDetail);
    }

    private BookDetailResponse convertToResponse(List<String> rawData, Map<String, Object> ratingDetailMap) {
        List<BookDateDetail> dateList = rawData.stream()
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, BookDateDetail.class);
                    } catch (Exception e) {
                        throw new RuntimeException("JSON 변환 실패: " + json, e);
                    }
                })
                .collect(Collectors.toList());
        //붕어빵 정보 +
        BookRatingResponse ratingDetail = objectMapper.convertValue(ratingDetailMap, BookRatingResponse.class);
        return new BookDetailResponse(dateList, ratingDetail);
    }
}