package fish.common.book.service;

import fish.common.book.repository.UserBookRepository;
import fish.common.book.response.UserBookResponse;
import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final UserBookRepository userBookRepository;
    private final UserService userService;

    public List<UserBookResponse> findAllUserFishBunBook(String userUUID) {
        Long userId = userService.getUserId(userUUID);

        return userBookRepository.findAllById(userId).stream()
                .map(UserBookResponse::toResponseDTO)
                .toList();
    }
}
