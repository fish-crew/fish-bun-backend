package fish.common.calendar.dto.response;

import fish.common.detail.dto.DetailFlavor;
import fish.common.detail.entity.DetailEntity;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
public class CalendarDetailResponse {
    private Long id;
    private String fileUrl;
    private String date;
    private List<DetailFlavor> flavors;
    private String contents;

    @Builder
    public CalendarDetailResponse(Long id, String fileUrl, String date, String contents, List<DetailFlavor> flavors) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.date = date;
        this.flavors = flavors;
        this.contents = contents;
    }

    public static CalendarDetailResponse toResDTO(DetailEntity fishBunDetail, String fileUrl) {
        return CalendarDetailResponse.builder()
                .id(fishBunDetail.getId())
                .fileUrl(fileUrl)
                .date(fishBunDetail.getDate())
                .contents(fishBunDetail.getContents())
                .flavors(fishBunDetail.getFlavors())
                .build();
    }
}
