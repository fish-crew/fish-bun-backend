package fish.common.calendar.response;

import fish.common.calendar.entity.FishBunCalendar;
import fish.common.flavor.response.FlavorResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CalendarDetailResponse {
    private Long id;
    private String photo;
    private LocalDateTime date;
    private String todayFishBun;

    @Builder
    public CalendarDetailResponse(Long id, String photo, LocalDateTime date, String todayFishBun) {
        this.id = id;
        this.photo = photo;
        this.date = date;
        this.todayFishBun = todayFishBun;
    }

    public static CalendarDetailResponse toResDTO(FishBunCalendar fishBunCalendar) {
        return CalendarDetailResponse.builder()
                .id(fishBunCalendar.getId())
                .photo(fishBunCalendar.getPhoto())
                .date(fishBunCalendar.getDate())
                .todayFishBun("dsfsfdf")
                .build();
    }
}
