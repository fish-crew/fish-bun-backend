package fish.common.fishBun.dto.response;

import fish.common.fishBun.entity.FishBunCalendar;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResDTO {
    private Long id;
    private LocalDateTime date;

    @Builder
    public CalendarResDTO(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public static CalendarResDTO toResponseDTO(FishBunCalendar fishBunCalendar) {
        return CalendarResDTO.builder()
                .id(fishBunCalendar.getId())
                .date(fishBunCalendar.getDate())
                .build();
    }
}
