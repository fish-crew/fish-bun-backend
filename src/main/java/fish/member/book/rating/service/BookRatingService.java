package fish.member.book.rating.service;

import fish.domain.book.BungDiaryBook;
import fish.domain.book.BungDiaryBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRatingService {
    private final BungDiaryBookRepository bungDiaryBookRepository;

    public void modify(Long flavorId, Long userId, Double rating) {
        Optional<BungDiaryBook> optional = bungDiaryBookRepository.findByUserIdAndCompletedFlavorId(userId, flavorId);
        //modify
        if (optional.isPresent()) {
            BungDiaryBook userBook = optional.get();
            userBook.setRating(rating);
            bungDiaryBookRepository.save(userBook);
        }
    }
}