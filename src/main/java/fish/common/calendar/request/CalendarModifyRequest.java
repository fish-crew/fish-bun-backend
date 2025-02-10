package fish.common.calendar.request;

import lombok.Getter;

@Getter
public class CalendarModifyRequest {
    private Long calendarId;
    private String contents;
}
