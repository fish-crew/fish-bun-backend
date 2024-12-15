package fish.common.calendar.response;

import fish.common.detail.entity.DetailEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarDetailResponse {
    private Long id;
    private String fileUrl;
    private LocalDateTime date;
    private String flavors;

    @Builder
    public CalendarDetailResponse(Long id, String fileUrl, LocalDateTime date, String flavors) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.date = date;
        this.flavors = flavors;
    }

    public static CalendarDetailResponse toResDTO(DetailEntity fishBunDetail, String fileUrl) {
        return CalendarDetailResponse.builder()
                .id(fishBunDetail.getId())
                .fileUrl(fileUrl)
                .date(fishBunDetail.getDate())
                .flavors(fishBunDetail.getFlavors())
                .build();
    }
}
