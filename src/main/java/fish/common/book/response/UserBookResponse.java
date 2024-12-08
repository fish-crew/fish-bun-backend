package fish.common.book.response;

import fish.common.book.entity.UserBook;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserBookResponse {
    private Long id;
    private String completed_flavor;

    @Builder
    public UserBookResponse(Long id, String completed_flavor) {
        this.id = id;
        this.completed_flavor = completed_flavor;
    }

    public static UserBookResponse toResponseDTO(UserBook userFishBunBook) {
        return UserBookResponse.builder()
                .id(userFishBunBook.getId())
                .completed_flavor(userFishBunBook.getCompletedFlavor())
                .build();
    }
}
