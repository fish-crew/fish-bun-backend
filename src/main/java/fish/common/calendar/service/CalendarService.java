package fish.common.calendar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.detail.dto.DetailFlavor;
import fish.common.detail.entity.DetailEntity;
import fish.common.calendar.repository.CalendarRepository;
import fish.common.calendar.response.CalendarDetailResponse;
import fish.common.calendar.response.CalendarResponse;
import fish.common.file.entity.FileEntity;
import fish.common.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final FileRepository fileRepository;

    public List<CalendarResponse> findAllCalendarDate(Long userId) {
        return calendarRepository.findAllByUserId(userId).stream()
                .map(CalendarResponse::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CalendarDetailResponse findCalendarDetail(Long calendarId) {
        DetailEntity detail = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));
        FileEntity fileEntity = fileRepository.findById(detail.getFileId())
                .orElseThrow(() -> new IllegalArgumentException("File data not found with id: " + detail.getFileId()));

        String fileUrl = fileEntity.getFilePath() + fileEntity.getSystemFileName();

        return CalendarDetailResponse.toResDTO(detail, fileUrl);
    }

    public int getFishBunCountByMonth(int year, int month) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> flavorJson = calendarRepository.getMonthlyCountByMonth(year, month);

        int monthlyTotal = 0;
        for (String flavors : flavorJson) {
            DetailFlavor[] detailFlavors = objectMapper.readValue(flavors, DetailFlavor[].class);
            for (DetailFlavor detailFlavor : detailFlavors) {
                monthlyTotal += detailFlavor.getCount();
            }
        }
        return monthlyTotal;
    }

}
