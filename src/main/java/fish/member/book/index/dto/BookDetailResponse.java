package fish.member.book.index.dto;

import fish.member.book.rating.dto.BookRatingResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class BookDetailResponse {
    private List<BookDateDetail> dateList;
    private BookRatingResponse fishBunFlavorEntity;

    public BookDetailResponse(List<BookDateDetail> dateList, BookRatingResponse ratingDetail) {
        this.dateList = dateList;
        this.fishBunFlavorEntity = ratingDetail;
    }
}