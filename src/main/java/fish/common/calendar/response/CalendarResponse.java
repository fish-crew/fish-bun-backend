package fish.common.calendar.response;

import fish.common.calendar.entity.FishBunCalendar;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponse {
    private Long id;
    private LocalDateTime date;

    @Builder
    public CalendarResponse(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public static CalendarResponse toResponseDTO(FishBunCalendar fishBunCalendar) {
        return CalendarResponse.builder()
                .id(fishBunCalendar.getId())
                .date(fishBunCalendar.getDate())
                .build();
    }
}
