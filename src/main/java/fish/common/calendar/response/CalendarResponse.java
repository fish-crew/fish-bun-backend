package fish.common.calendar.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;


@Getter
public class CalendarResponse {
    private Long id;
    private LocalDateTime date;

    @Builder
    public CalendarResponse(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public static CalendarResponse toResponseDTO(Map<String, Object> fishBunDetail) {
        Timestamp timestamp = Timestamp.valueOf(fishBunDetail.get("date").toString());
        return CalendarResponse.builder()
                .id(Long.parseLong(fishBunDetail.get("id").toString()))
                .date(timestamp.toLocalDateTime())
                .build();
    }
}
