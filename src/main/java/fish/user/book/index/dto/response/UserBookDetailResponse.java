package fish.user.book.index.dto.response;

import fish.user.book.rating.dto.UserBookRatingDetail;
import fish.user.book.index.dto.UserBookDateDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class UserBookDetailResponse {
    private List<UserBookDateDetail> dateList;
    private UserBookRatingDetail fishBunFlavorEntity;

    public UserBookDetailResponse(List<UserBookDateDetail> dateList, UserBookRatingDetail ratingDetail) {
        this.dateList = dateList;
        this.fishBunFlavorEntity = ratingDetail;
    }
}