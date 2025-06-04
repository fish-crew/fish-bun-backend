package fish.user.calendar.service;

import fish.user.calendar.dto.request.CalendarModifyRequest;
import fish.user.detail.dto.DetailFlavor;
import fish.user.detail.entity.DetailEntity;
import fish.user.calendar.repository.CalendarRepository;
import fish.user.calendar.dto.response.CalendarDetailResponse;
import fish.user.calendar.dto.response.CalendarResponse;
import fish.user.file.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final FileService fileService;

    public List<CalendarResponse> findAllCalendarDate(String date, Long userId) {
        return calendarRepository.findAllByUserId(date, userId).stream()
                .map(CalendarResponse::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CalendarDetailResponse findCalendarDetail(Long calendarId, Long userId) {
        DetailEntity detail = calendarRepository.findByIdAndUserId(calendarId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));
        String fileUrl = fileService.getFileUrl(detail.getFileId());
        return CalendarDetailResponse.toResDTO(detail, fileUrl);
    }

    public int getFishBunCountByMonth(String date, Long userId) {
        List<DetailEntity> detailEntity = calendarRepository.getMonthlyCountByMonth(date, userId);
        return detailEntity.stream().flatMap(entity -> entity.getFlavors().stream()).mapToInt(DetailFlavor::getCount).sum();
    }

    @Transactional
    public void modifyContents(Long userId, CalendarModifyRequest request) {
        DetailEntity detail = calendarRepository.findByIdAndUserId(request.getCalendarId(), userId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + request.getCalendarId()));
        detail.modifyContents(request.getContents());
        calendarRepository.save(detail);
    }
}
