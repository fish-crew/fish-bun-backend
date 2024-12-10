package fish.common.calendar.service;

import fish.common.main.entity.FishBunDetail;
import fish.common.calendar.repository.CalendarRepository;
import fish.common.calendar.response.CalendarDetailResponse;
import fish.common.calendar.response.CalendarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public List<CalendarResponse> findAllCalendarDate(Long userId) {
        return calendarRepository.findAllById(userId).stream()
                .map(CalendarResponse::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CalendarDetailResponse findCalendarDetail(Long calendarId) {
        FishBunDetail fishBunDetail = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));

        return CalendarDetailResponse.toResDTO(fishBunDetail);
    }

}
