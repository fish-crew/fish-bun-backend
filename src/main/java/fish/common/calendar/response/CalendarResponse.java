package fish.common.calendar.response;

import fish.common.main.entity.FishBunDetail;
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

    public static CalendarResponse toResponseDTO(FishBunDetail fishBunDetail) {
        return CalendarResponse.builder()
                .id(fishBunDetail.getId())
                .date(fishBunDetail.getDate())
                .build();
    }
}
