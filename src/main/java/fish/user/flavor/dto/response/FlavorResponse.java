package fish.user.flavor.dto.response;

import fish.user.flavor.entity.FishBunFlavorEntity;
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

    public static FlavorResponse toResponseDTO(FishBunFlavorEntity fishBunFlavorEntity) {
        return FlavorResponse.builder()
                .id(fishBunFlavorEntity.getId())
                .flavor(fishBunFlavorEntity.getFlavor())
                .iconCode(fishBunFlavorEntity.getIconCode())
                .seq(fishBunFlavorEntity.getSeq())
                .build();
    }
}
