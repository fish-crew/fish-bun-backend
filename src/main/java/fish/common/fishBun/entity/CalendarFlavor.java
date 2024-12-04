package fish.common.fishBun.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "CALENDAR_FLAVOR")
@Getter
@Entity
@NoArgsConstructor
public class CalendarFlavor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private FishBunCalendar fishBunCalendar;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private FishBunFlavor fishBunFlavor;


}
