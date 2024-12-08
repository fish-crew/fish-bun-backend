package fish.common.calendar.service;

import fish.common.calendar.entity.FishBunCalendar;
import fish.common.calendar.repository.CalendarRepository;
import fish.common.calendar.response.CalendarDetailResponse;
import fish.common.calendar.response.CalendarResponse;
import fish.common.flavor.repository.FlavorRepository;
import fish.common.flavor.response.FlavorResponse;
import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserService userService;
    private final FlavorRepository flavorRepository;

    public List<CalendarResponse> findAllCalendarDate(String userUUID) {
        Long userId = userService.getUserId(userUUID);

        return calendarRepository.findAllById(userId).stream()
                .map(CalendarResponse::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CalendarDetailResponse findCalendarDetail(Long calendarId) {
        FishBunCalendar fishBunCalendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));

        return CalendarDetailResponse.toResDTO(fishBunCalendar);
    }

}
