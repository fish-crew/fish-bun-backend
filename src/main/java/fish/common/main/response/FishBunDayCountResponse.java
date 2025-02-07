package fish.common.main.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter
@AllArgsConstructor
public class FishBunDayCountResponse {
    private Map<String, Object> daysInWeek;
    private int weeklyCount;
    private int monthlyCount;
}
