package fish.common.book.response;

import fish.common.book.dto.UserBookDateDetail;
import fish.common.flavor.entity.FishBunFlavor;
import lombok.Getter;

import java.util.List;

@Getter
public class UserBookDetailResponse {
    private List<UserBookDateDetail> dateList;
    private FishBunFlavor fishBunFlavor;

    public UserBookDetailResponse(List<UserBookDateDetail> dateList, FishBunFlavor fishBunFlavor) {
        this.dateList = dateList;
        this.fishBunFlavor = fishBunFlavor;
    }
}
