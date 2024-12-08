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
    private List<FlavorResponse> todayFishBun;

    @Builder
    public CalendarDetailResponse(Long id, String photo, LocalDateTime date, List<FlavorResponse> todayFishBun) {
        this.id = id;
        this.photo = photo;
        this.date = date;
        this.todayFishBun = todayFishBun;
    }

    public static CalendarDetailResponse toResDTO(FishBunCalendar fishBunCalendar, List<FlavorResponse> flavorResDTOList) {
        return CalendarDetailResponse.builder()
                .id(fishBunCalendar.getId())
                .photo(fishBunCalendar.getPhoto())
                .date(fishBunCalendar.getDate())
                .todayFishBun(flavorResDTOList)
                .build();
    }
}
