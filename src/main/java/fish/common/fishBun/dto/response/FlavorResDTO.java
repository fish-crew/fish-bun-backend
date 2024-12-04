package fish.common.fishBun.dto.response;

import fish.common.fishBun.entity.FishBunFlavor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FlavorResDTO {
    private Long id;
    private String flavor;
    private String image;

    @Builder
    public FlavorResDTO(Long id, String flavor, String image) {
        this.id = id;
        this.flavor = flavor;
        this.image = image;

    }

    public static FlavorResDTO toResponseDTO(FishBunFlavor fishBunFlavor) {
        return FlavorResDTO.builder()
                .id(fishBunFlavor.getId())
                .flavor(fishBunFlavor.getFlavor())
                .image(fishBunFlavor.getImage())
                .build();
    }
}
