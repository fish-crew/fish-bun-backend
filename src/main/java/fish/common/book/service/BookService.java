package fish.common.book.service;

import fish.common.book.entity.UserBook;
import fish.common.book.repository.UserBookRepository;
import fish.common.book.response.UserBookResponse;
import fish.common.detail.response.DetailResponse;
import fish.common.flavor.entity.FishBunFlavor;
import fish.common.flavor.repository.FlavorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final UserBookRepository userBookRepository;
    private final FlavorRepository flavorRepository;

    public List<UserBookResponse> findAllUserFishBunBook(Long userId) {
        return userBookRepository.findAllByUserId(userId).stream()
                .map((userBook) -> {
                    Long flavorId = userBook.getCompletedFlavorId();
                    FishBunFlavor flavor = flavorRepository.findById(flavorId).orElseThrow(() -> new EntityNotFoundException("FishBun Flavor Entity not found with ID: " + flavorId));
                    return UserBookResponse.toResponseDTO(userBook, flavor);
                })
                .collect(Collectors.toList());
    }

    public void saveUserCompletedFlavor(List<Long> flavorIdList, Long userId) {
        List<Long> completedFlavorIdList = userBookRepository.findAllByUserId(userId).stream()
                .map(UserBook::getCompletedFlavorId)
                .toList();

        // Save the newly tried flavor as a completed flavor ID
        for (Long id : flavorIdList) {
            if (!completedFlavorIdList.contains(id)) {
                userBookRepository.save(UserBook.toEntity(userId, id));
            }
        }
    }
}
