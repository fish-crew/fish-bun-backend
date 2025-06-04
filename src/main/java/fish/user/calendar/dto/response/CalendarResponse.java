package fish.user.calendar.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.Map;


@Getter
public class CalendarResponse {
    private Long id;
    private String date;

    @Builder
    public CalendarResponse(Long id, String date) {
        this.id = id;
        this.date = date;
    }

    public static CalendarResponse toResponseDTO(Map<String, Object> fishBunDetail) {
        return CalendarResponse.builder()
                .id(Long.parseLong(fishBunDetail.get("id").toString()))
                .date(fishBunDetail.get("date").toString())
                .build();
    }
}
