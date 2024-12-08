package fish.common.flavor.response;

import fish.common.flavor.entity.FishBunFlavor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FlavorResponse {
    private Long id;
    private String flavor;
    private String image;

    @Builder
    public FlavorResponse(Long id, String flavor, String image) {
        this.id = id;
        this.flavor = flavor;
        this.image = image;

    }

    public static FlavorResponse toResponseDTO(FishBunFlavor fishBunFlavor) {
        return FlavorResponse.builder()
                .id(fishBunFlavor.getId())
                .flavor(fishBunFlavor.getFlavor())
                .image(fishBunFlavor.getImage())
                .build();
    }
}
