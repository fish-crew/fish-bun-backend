package fish.common.fishBun.controller;

import fish.common.fishBun.dto.response.CalendarResDTO;
import fish.common.fishBun.service.FishBunService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fish-bun")
public class FishBunController {
    private final FishBunService fishBunService;

    @GetMapping(value = "/calendar")
    public ResponseEntity<List<CalendarResDTO>> getCalendarList(@RequestParam String userUUID) {
        List<CalendarResDTO> data = fishBunService.findAllCalendarDate(userUUID);

        return ResponseEntity.ok(data);
    }
}
