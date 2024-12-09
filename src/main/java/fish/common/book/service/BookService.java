package fish.common.book.service;

import fish.common.book.repository.UserBookRepository;
import fish.common.book.response.UserBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final UserBookRepository userBookRepository;

    public List<UserBookResponse> findAllUserFishBunBook(Long id) {
        return userBookRepository.findAllByUserId(id).stream()
                .map(UserBookResponse::toResponseDTO)
                .toList();
    }
}
