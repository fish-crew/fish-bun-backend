package fish.common.calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Table(name = "FISH_BUN_CALENDAR")
@Getter
@Entity
@NoArgsConstructor
public class FishBunCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String photo;
    @CreationTimestamp
    private LocalDateTime date;
}
