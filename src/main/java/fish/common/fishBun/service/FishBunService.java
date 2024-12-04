package fish.common.fishBun.service;

import fish.common.fishBun.dto.response.CalendarDetailResDTO;
import fish.common.fishBun.dto.response.CalendarResDTO;
import fish.common.fishBun.dto.response.FlavorResDTO;
import fish.common.fishBun.entity.FishBunCalendar;
import fish.common.fishBun.repository.FishBunBookRepository;
import fish.common.fishBun.repository.FishBunCalendarRepository;
import fish.common.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FishBunService {
    private final FishBunCalendarRepository fishBunCalendarRepository;
    private final FishBunBookRepository fishBunBookRepository;
    private final UserService userService;

    public List<CalendarResDTO> findAllCalendarDate(String userUUID) {
        Long userId = userService.getUserId(userUUID);

        return fishBunCalendarRepository.findAllByUserId(userId).stream()
                .map(CalendarResDTO::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CalendarDetailResDTO findCalendarDetail(Long calendarId) {
        FishBunCalendar fishBunCalendar = fishBunCalendarRepository.findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));
        List<FlavorResDTO> flavorResDTOList =
                fishBunCalendarRepository.findTodayFlavorsByCalendarId(calendarId)
                        .stream()
                        .map(FlavorResDTO::toResponseDTO)
                        .toList();

        return CalendarDetailResDTO.toResDTO(fishBunCalendar, flavorResDTOList);
    }
}
