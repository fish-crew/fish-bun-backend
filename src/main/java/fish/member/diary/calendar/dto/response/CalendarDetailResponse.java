package fish.member.diary.calendar.dto.response;

import fish.member.diary.index.dto.BungDiaryFlavor;
import fish.domain.diary.index.BungDiary;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
public class CalendarDetailResponse {
    private Long id;
    private String fileUrl;
    private String date;
    private List<BungDiaryFlavor> flavors;
    private String contents;

    @Builder
    public CalendarDetailResponse(Long id, String fileUrl, String date, String contents, List<BungDiaryFlavor> flavors) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.date = date;
        this.flavors = flavors;
        this.contents = contents;
    }

    public static CalendarDetailResponse toResDTO(BungDiary fishBunDetail, String fileUrl) {
        return CalendarDetailResponse.builder()
                .id(fishBunDetail.getId())
                .fileUrl(fileUrl)
                .date(fishBunDetail.getDate())
                .contents(fishBunDetail.getContents())
                .flavors(fishBunDetail.getFlavors())
                .build();
    }
}
