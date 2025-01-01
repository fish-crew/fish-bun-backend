package fish.common.book.response;

import fish.common.book.entity.UserBook;
import fish.common.flavor.entity.FishBunFlavor;
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

    public static UserBookResponse toResponseDTO(UserBook userFishBunBook, FishBunFlavor flavor) {
        return UserBookResponse.builder()
                .id(userFishBunBook.getId())
                .completedFlavorId(userFishBunBook.getCompletedFlavorId())
                .date(userFishBunBook.getRegDate())
                .iconCode(flavor.getIconCode())
                .seq(flavor.getSeq())
                .build();
    }
}
