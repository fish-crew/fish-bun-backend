package fish.member.diary.weekly.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.domain.diary.weekly.DiaryWeeklyRepository;
import fish.member.diary.weekly.dto.DiaryWeeklyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryWeeklyService {
    private final DiaryWeeklyRepository diaryWeeklyRepository;

    public DiaryWeeklyResponse countFishBunDaysInWeek(Long userId) throws JsonProcessingException {
        Map<String, Object> result = diaryWeeklyRepository.countCurrentWeekData(userId);
        int monthlyCount = diaryWeeklyRepository.countCurrentMonthData(userId);
        int weeklyCount = Integer.parseInt(result.get("weeklyCount").toString());
        if (weeklyCount != 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> daysInWeek = objectMapper.readValue(result.get("daysInWeek").toString(), Map.class);
            return new DiaryWeeklyResponse(daysInWeek, weeklyCount, monthlyCount);
        }
        return new DiaryWeeklyResponse(null, 0, monthlyCount);
    }
}
