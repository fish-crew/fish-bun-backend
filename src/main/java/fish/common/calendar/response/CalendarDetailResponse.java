package fish.common.calendar.response;

import fish.common.detail.dto.DetailFlavor;
import fish.common.detail.entity.DetailEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CalendarDetailResponse {
    private Long id;
    private String fileUrl;
    private LocalDateTime regDate;
    private List<DetailFlavor> flavors;

    @Builder
    public CalendarDetailResponse(Long id, String fileUrl, LocalDateTime date, List<DetailFlavor> flavors) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.regDate = date;
        this.flavors = flavors;
    }

    public static CalendarDetailResponse toResDTO(DetailEntity fishBunDetail, String fileUrl) {
        return CalendarDetailResponse.builder()
                .id(fishBunDetail.getId())
                .fileUrl(fileUrl)
                .date(fishBunDetail.getRegDate())
                .flavors(fishBunDetail.getFlavors())
                .build();
    }
}
