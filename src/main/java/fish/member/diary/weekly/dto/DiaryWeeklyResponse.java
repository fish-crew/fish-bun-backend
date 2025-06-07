package fish.member.diary.weekly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter
@AllArgsConstructor
public class DiaryWeeklyResponse {
    private Map<String, Object> daysInWeek;
    private int weeklyCount;
    private int monthlyCount;
}
