package fish.common.calendar.controller;

import fish.common.calendar.dto.request.CalendarModifyRequest;
import fish.common.calendar.dto.response.CalendarDetailResponse;
import fish.common.calendar.dto.response.CalendarResponse;
import fish.common.calendar.service.CalendarService;
import fish.common.user.entity.User;
import fish.global.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun/calendar")
@Tag(name = "붕어빵 캘린더 API")
public class CalendarController {
    private final CalendarService calendarService;

    @Operation(summary = "사용자별 붕어빵 캘린더 전체 조회")
    @Parameter(name = "date", description = "YYYY-MM (ex. 2024-03)")
    @GetMapping(value = "/{date}")
    public ResponseEntity<ResponseUtil<List<CalendarResponse>>> getCalendarList
            (@AuthenticationPrincipal User user, @PathVariable("date") String date) {
        List<CalendarResponse> data = calendarService.findAllCalendarDate(date, user.getId());
        int monthlyCount = calendarService.getFishBunCountByMonth(date, user.getId());
        Map<Object, Object> map = Map.of("monthlyCount", monthlyCount);
        return ResponseEntity.ok(ResponseUtil.success(data, map));
    }

    @Operation(summary = "사용자별 붕어빵 캘린더 상세 조회")
    @Parameter(name = "calendarId", description = "상세 일지 고유 ID")
    @GetMapping(value = "/detail/{calendarId}")
    public ResponseEntity<ResponseUtil<CalendarDetailResponse>> getCalendarDetail(
            @AuthenticationPrincipal User user, @PathVariable("calendarId") Long calendarId) {
        CalendarDetailResponse data = calendarService.findCalendarDetail(calendarId, user.getId());
        return ResponseEntity.ok(ResponseUtil.success(data));
    }

    @Operation(summary = "사용자별 붕어빵 캘린더 일지 편집")
    @PostMapping(value = "/detail/mod-contents")
    public ResponseEntity<ResponseUtil<CalendarDetailResponse>> addCalendarDetail(
            @AuthenticationPrincipal User user, @RequestBody CalendarModifyRequest request) {
        calendarService.modifyContents(user.getId(), request);
        return ResponseEntity.ok(ResponseUtil.success());
    }
}
