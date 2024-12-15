package fish.common.calendar.controller;

import fish.common.calendar.response.CalendarDetailResponse;
import fish.common.calendar.response.CalendarResponse;
import fish.common.calendar.service.CalendarService;
import fish.common.user.entity.User;
import fish.core.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping()
    public ResponseEntity<ResponseUtil<List<CalendarResponse>>> getCalendarList
            (@AuthenticationPrincipal User user) {
        List<CalendarResponse> data = calendarService.findAllCalendarDate(user.getId());
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @GetMapping(value = "/detail/{calendarId}")
    public ResponseEntity<ResponseUtil<CalendarDetailResponse>> getCalendarDetail(@PathVariable("calendarId") Long calendarId) {
        CalendarDetailResponse data = calendarService.findCalendarDetail(calendarId);
        return ResponseEntity.ok(ResponseUtil.success(data));
    }
}
