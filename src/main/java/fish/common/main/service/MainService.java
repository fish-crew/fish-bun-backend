package fish.common.main.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.main.repository.MainRepository;
import fish.common.main.dto.response.FishBunDayCountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {
    private final MainRepository mainRepository;

    public FishBunDayCountResponse countFishBunDaysInWeek(Long userId) throws JsonProcessingException {
        Map<String, Object> result = mainRepository.countCurrentWeekData(userId);
        int monthlyCount = mainRepository.countCurrentMonthData(userId);
        int weeklyCount = Integer.parseInt(result.get("weeklyCount").toString());
        if (weeklyCount != 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> daysInWeek = objectMapper.readValue(result.get("daysInWeek").toString(), Map.class);
            return new FishBunDayCountResponse(daysInWeek, weeklyCount, monthlyCount);
        }
        return new FishBunDayCountResponse(null, 0, monthlyCount);
    }
}
