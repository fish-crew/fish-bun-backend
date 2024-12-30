package fish.common.main.service;

import fish.common.main.repository.MainRepository;
import fish.common.main.response.FishBunDayCountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {
    private final MainRepository mainRepository;

    public FishBunDayCountResponse countFishBunDaysInWeek(Long userId) {
        Map<String, Object> result = mainRepository.countCurrentWeekData(userId);
        int monthlyCount = mainRepository.countCurrentMonthData(userId);
        int weeklyCount = Integer.parseInt(result.get("weeklyCount").toString());
        if (weeklyCount != 0) {
            List<String> days = Arrays.asList(result.get("days").toString().split(","));
            return new FishBunDayCountResponse(days, weeklyCount, monthlyCount);
        }
        return new FishBunDayCountResponse(Collections.emptyList(), 0, monthlyCount);
    }
}
