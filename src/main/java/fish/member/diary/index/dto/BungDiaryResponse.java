package fish.member.diary.index.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BungDiaryResponse {
    private Long flavorId;
    private String iconCode;

    @Builder
    public BungDiaryResponse(Long flavorId, String iconCode) {
        this.flavorId = flavorId;
        this.iconCode = iconCode;
    }

    public static BungDiaryResponse toResponse(Long flavorId, String iconCode) {
        return BungDiaryResponse.builder()
                .flavorId(flavorId)
                .iconCode(iconCode)
                .build();
    }
}
