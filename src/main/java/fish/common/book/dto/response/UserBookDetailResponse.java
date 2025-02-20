package fish.common.book.dto.response;

import fish.common.book.dto.UserBookDateDetail;
import fish.common.flavor.entity.FishBunFlavorEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class UserBookDetailResponse {
    private List<UserBookDateDetail> dateList;
    private FishBunFlavorEntity fishBunFlavorEntity;

    public UserBookDetailResponse(List<UserBookDateDetail> dateList, FishBunFlavorEntity fishBunFlavorEntity) {
        this.dateList = dateList;
        this.fishBunFlavorEntity = fishBunFlavorEntity;
    }
}
