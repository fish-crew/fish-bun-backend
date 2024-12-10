package fish.common.book.response;

import fish.common.book.entity.UserBook;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserBookResponse {
    private Long id;
    private String completed_flavor;
    private LocalDateTime date;

    @Builder
    public UserBookResponse(Long id, String completed_flavor, LocalDateTime date) {
        this.id = id;
        this.completed_flavor = completed_flavor;
        this.date = date;
    }

    public static UserBookResponse toResponseDTO(UserBook userFishBunBook) {
        return UserBookResponse.builder()
                .id(userFishBunBook.getId())
                .completed_flavor(userFishBunBook.getCompletedFlavor())
                .date(userFishBunBook.getDate())
                .build();
    }
}
