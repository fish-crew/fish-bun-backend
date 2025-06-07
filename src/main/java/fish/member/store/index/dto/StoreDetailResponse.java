package fish.member.store.index.dto;

import fish.member.diary.index.dto.BungDiaryFlavor;
import fish.common.util.JsonUtil;
import fish.common.util.StringUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class StoreDetailResponse {
    private Long id;
    private String fileUrl;
    private String date;
    private List<BungDiaryFlavor> flavors;
    private String contents;
    private String nickname;

    @Builder
    public StoreDetailResponse(Long id, String fileUrl, String date, String contents, List<BungDiaryFlavor> flavors, String nickname) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.date = date;
        this.flavors = flavors;
        this.contents = contents;
        this.nickname = nickname;
    }

    public static StoreDetailResponse toResDTO(Map<String, Object> detail, String fileUrl) {
        return StoreDetailResponse.builder()
                .id(Long.parseLong(detail.get("id").toString()))
                .fileUrl(fileUrl)
                .date(StringUtil.NVL(detail.get("date")))
                .contents(StringUtil.NVL(detail.get("contents")))
                .flavors(JsonUtil.readValue(detail.get("flavors").toString(), BungDiaryFlavor.class))
                .nickname(StringUtil.NVL(detail.get("nickname")))
                .build()
                ;
    }
}
