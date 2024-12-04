package fish.common.fishBun.dto.response;

import fish.common.fishBun.entity.UserFishBunBook;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserFishBunBookResDTO {
    private Long id;
    private String completed_flavor;

    @Builder
    public UserFishBunBookResDTO(Long id, String completed_flavor) {
        this.id = id;
        this.completed_flavor = completed_flavor;
    }

    public static UserFishBunBookResDTO toResponseDTO(UserFishBunBook userFishBunBook) {
        return UserFishBunBookResDTO.builder()
                .id(userFishBunBook.getId())
                .completed_flavor(userFishBunBook.getCompletedFlavor())
                .build();
    }
}
