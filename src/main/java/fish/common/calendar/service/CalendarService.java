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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalendarService {
    @Value("${file-uri}")
    private String fileUri;

    private final CalendarRepository calendarRepository;
    private final FileRepository fileRepository;

    public List<CalendarResponse> findAllCalendarDate(int year, int month, Long userId) {
        return calendarRepository.findAllByUserId(year, month, userId).stream()
                .map(CalendarResponse::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CalendarDetailResponse findCalendarDetail(Long calendarId, Long userId) {
        DetailEntity detail = calendarRepository.findByIdAndUserId(calendarId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Calendar data not found with id: " + calendarId));
        FileEntity fileEntity = fileRepository.findById(detail.getFileId())
                .orElseThrow(() -> new IllegalArgumentException("File data not found with id: " + detail.getFileId()));

        String fileUrl = fileUri + fileEntity.getFilePath() + "/" +fileEntity.getSystemFileName();

        return CalendarDetailResponse.toResDTO(detail, fileUrl);
    }

    public int getFishBunCountByMonth(int year, int month, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> flavorJson = calendarRepository.getMonthlyCountByMonth(year, month, userId);

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
