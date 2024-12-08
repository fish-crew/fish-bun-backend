package fish.common.calendar.controller;

import fish.common.calendar.response.CalendarDetailResponse;
import fish.common.calendar.response.CalendarResponse;
import fish.common.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping(value = "/{userUUID}")
    public ResponseEntity<List<CalendarResponse>> getCalendarList(@PathVariable String userUUID) {
        List<CalendarResponse> data = calendarService.findAllCalendarDate(userUUID);
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/detail/{calendarId}")
    public ResponseEntity<CalendarDetailResponse> getCalendarDetail(@PathVariable("calendarId") Long calendarId) {
        return ResponseEntity.ok(calendarService.findCalendarDetail(calendarId));
    }
}
