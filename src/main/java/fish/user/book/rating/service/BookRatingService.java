package fish.user.book.rating.service;

import fish.user.book.index.entity.UserBookEntity;
import fish.user.book.index.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRatingService {
    private final UserBookRepository userBookRepository;

    public void modify(Long flavorId, Long userId, Double rating) {
        Optional<UserBookEntity> optional = userBookRepository.findByUserIdAndCompletedFlavorId(userId, flavorId);
        //modify
        if (optional.isPresent()) {
            UserBookEntity userBook = optional.get();
            userBook.setRating(rating);
            userBookRepository.save(userBook);
        }
    }
}