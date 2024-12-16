package fish.common.book.response;

import fish.common.book.entity.UserBook;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserBookResponse {
    private Long id;
    private Long completedFlavorId;
    private LocalDateTime date;

    @Builder
    public UserBookResponse(Long id, Long completedFlavorId, LocalDateTime date) {
        this.id = id;
        this.completedFlavorId = completedFlavorId;
        this.date = date;
    }

    public static UserBookResponse toResponseDTO(UserBook userFishBunBook) {
        return UserBookResponse.builder()
                .id(userFishBunBook.getId())
                .completedFlavorId(userFishBunBook.getCompletedFlavorId())
                .date(userFishBunBook.getDate())
                .build();
    }
}
