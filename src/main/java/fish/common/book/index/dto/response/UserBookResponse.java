package fish.common.book.index.dto.response;

import fish.common.book.index.entity.UserBookEntity;
import fish.common.flavor.entity.FishBunFlavorEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserBookResponse {
    private Long id;
    private Long completedFlavorId;
    private LocalDateTime regDate;
    private String iconCode;
    private int seq;


    @Builder
    public UserBookResponse(Long id, Long completedFlavorId, LocalDateTime date, String iconCode, int seq) {
        this.id = id;
        this.completedFlavorId = completedFlavorId;
        this.regDate = date;
        this.iconCode = iconCode;
        this.seq = seq;
    }

    public static UserBookResponse toResponseDTO(UserBookEntity userFishBunBook, FishBunFlavorEntity flavor) {
        return UserBookResponse.builder()
                .id(userFishBunBook.getId())
                .completedFlavorId(userFishBunBook.getCompletedFlavorId())
                .date(userFishBunBook.getRegDate())
                .iconCode(flavor.getIconCode())
                .seq(flavor.getSeq())
                .build();
    }
}
