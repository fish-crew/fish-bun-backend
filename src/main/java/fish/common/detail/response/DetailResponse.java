package fish.common.detail.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DetailResponse {
    private Long flavorId;
    private String iconCode;

    @Builder
    public DetailResponse(Long flavorId, String iconCode) {
        this.flavorId = flavorId;
        this.iconCode = iconCode;
    }

    public static DetailResponse toResponse(Long flavorId, String iconCode) {
        return DetailResponse.builder()
                .flavorId(flavorId)
                .iconCode(iconCode)
                .build();
    }
}
