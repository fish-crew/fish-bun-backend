package fish.common.fishBun.controller;

import fish.common.fishBun.dto.response.CalendarDetailResDTO;
import fish.common.fishBun.dto.response.CalendarResDTO;
import fish.common.fishBun.dto.response.FlavorResDTO;
import fish.common.fishBun.service.FishBunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
public class FishBunController {
    private final FishBunService fishBunService;

    @GetMapping(value = "/calendar/{userUUID}")
    public ResponseEntity<List<CalendarResDTO>> getCalendarList(@RequestParam String userUUID) {
        List<CalendarResDTO> data = fishBunService.findAllCalendarDate(userUUID);

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/calendar/detail/{calendarId}")
    public ResponseEntity<CalendarDetailResDTO> getCalendarDetail(@PathVariable("calendarId") Long calendarId) {
        return ResponseEntity.ok(fishBunService.findCalendarDetail(calendarId));
    }

    @GetMapping(value = "/flavors")
    public ResponseEntity<List<FlavorResDTO>> getFlavorList() {
        return ResponseEntity.ok(fishBunService.findAllFlavors());
    }
}
