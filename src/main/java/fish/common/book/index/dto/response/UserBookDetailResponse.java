package fish.common.book.index.dto.response;

import fish.common.book.rating.dto.UserBookRatingDetail;
import fish.common.book.index.dto.UserBookDateDetail;
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