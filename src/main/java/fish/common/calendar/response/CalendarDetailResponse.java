package fish.common.calendar.response;

import fish.common.detail.entity.DetailEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarDetailResponse {
    private Long id;
    private String photo;
    private LocalDateTime date;
    private String flavors;

    @Builder
    public CalendarDetailResponse(Long id, String photo, LocalDateTime date, String flavors) {
        this.id = id;
        this.photo = photo;
        this.date = date;
        this.flavors = flavors;
    }

    public static CalendarDetailResponse toResDTO(DetailEntity fishBunDetail) {
        return CalendarDetailResponse.builder()
                .id(fishBunDetail.getId())
                .photo("Photo Test Url")
                .date(fishBunDetail.getDate())
                .flavors(fishBunDetail.getFlavors())
                .build();
    }
}
