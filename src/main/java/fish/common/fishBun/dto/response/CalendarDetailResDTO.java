package fish.common.fishBun.dto.response;

import fish.common.fishBun.entity.FishBunCalendar;
import fish.common.fishBun.entity.FishBunFlavor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CalendarDetailResDTO {
    private Long id;
    private String photo;
    private LocalDateTime date;
    private List<FishBunFlavor> todayFishBun;

    @Builder
    public CalendarDetailResDTO(Long id, String photo, LocalDateTime date, List<FishBunFlavor> todayFishBun) {
        this.id = id;
        this.photo = photo;
        this.date = date;
        this.todayFishBun = todayFishBun;
    }

    public static CalendarDetailResDTO toResDTO(FishBunCalendar fishBunCalendar, List<FishBunFlavor> fishBunFlavorList) {
        return CalendarDetailResDTO.builder()
                .id(fishBunCalendar.getId())
                .photo(fishBunCalendar.getPhoto())
                .date(fishBunCalendar.getDate())
                .todayFishBun(fishBunFlavorList)
                .build();
    }
}
