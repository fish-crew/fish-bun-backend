package fish.common.flavor.response;

import fish.common.flavor.entity.FishBunFlavor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FlavorResponse {
    private Long id;
    private String flavor;
    private String iconCode;
    private int seq;

    @Builder
    public FlavorResponse(Long id, String flavor, String iconCode, int seq) {
        this.id = id;
        this.flavor = flavor;
        this.iconCode = iconCode;
        this.seq = seq;
    }

    public static FlavorResponse toResponseDTO(FishBunFlavor fishBunFlavor) {
        return FlavorResponse.builder()
                .id(fishBunFlavor.getId())
                .flavor(fishBunFlavor.getFlavor())
                .iconCode(fishBunFlavor.getIconCode())
                .seq(fishBunFlavor.getSeq())
                .build();
    }
}
