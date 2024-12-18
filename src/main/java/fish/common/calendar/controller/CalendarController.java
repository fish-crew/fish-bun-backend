package fish.common.calendar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fish.common.calendar.response.CalendarDetailResponse;
import fish.common.calendar.response.CalendarResponse;
import fish.common.calendar.service.CalendarService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping(value = "/{date}")
    public ResponseEntity<ResponseUtil<List<CalendarResponse>>> getCalendarList
            (@AuthenticationPrincipal User user, @PathVariable("date") String date) throws JsonProcessingException {
        String[] part = date.split("-");
        int year = Integer.parseInt(part[0]);
        int month = Integer.parseInt(part[1]);
        List<CalendarResponse> data = calendarService.findAllCalendarDate(user.getId());
        int monthlyCount = calendarService.getFishBunCountByMonth(year, month, user.getId());
        Map<Object, Object> map = Map.of("monthlyCount", monthlyCount);
        return ResponseEntity.ok(ResponseUtil.success(data, map));
    }

    @GetMapping(value = "/detail/{calendarId}")
    public ResponseEntity<ResponseUtil<CalendarDetailResponse>> getCalendarDetail(@PathVariable("calendarId") Long calendarId) {
        CalendarDetailResponse data = calendarService.findCalendarDetail(calendarId);
        return ResponseEntity.ok(ResponseUtil.success(data));
    }
}
