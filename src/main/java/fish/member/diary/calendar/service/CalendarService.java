package fish.member.diary.calendar.service;

import fish.member.diary.calendar.dto.request.CalendarModifyRequest;
import fish.member.diary.index.dto.BungDiaryFlavor;
import fish.domain.diary.index.BungDiary;
import fish.domain.diary.calendar.CalendarRepository;
import fish.member.diary.calendar.dto.response.CalendarDetailResponse;
import fish.member.diary.calendar.dto.response.CalendarResponse;
import fish.member.diary.file.service.FileService;
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
        BungDiary detail = calendarRepository.findByIdAndUserId(calendarId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));
        String fileUrl = fileService.getFileUrl(detail.getFileId());
        return CalendarDetailResponse.toResDTO(detail, fileUrl);
    }

    public int getFishBunCountByMonth(String date, Long userId) {
        List<BungDiary> bungDiary = calendarRepository.getMonthlyCountByMonth(date, userId);
        return bungDiary.stream().flatMap(entity -> entity.getFlavors().stream()).mapToInt(BungDiaryFlavor::getCount).sum();
    }

    @Transactional
    public void modifyContents(Long userId, CalendarModifyRequest request) {
        BungDiary detail = calendarRepository.findByIdAndUserId(request.getCalendarId(), userId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + request.getCalendarId()));
        detail.modifyContents(request.getContents());
        calendarRepository.save(detail);
    }
}
