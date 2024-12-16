package fish.common.book.service;

import fish.common.book.entity.UserBook;
import fish.common.book.repository.UserBookRepository;
import fish.common.book.response.UserBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final UserBookRepository userBookRepository;

    public List<UserBookResponse> findAllUserFishBunBook(Long userId) {
        return userBookRepository.findAllByUserId(userId).stream()
                .map(UserBookResponse::toResponseDTO)
                .toList();
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
