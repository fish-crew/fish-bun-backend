package fish.common.main.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class FishBunDayCountResponse {
    private List<String> daysInWeek;
    private int weeklyCount;
}
