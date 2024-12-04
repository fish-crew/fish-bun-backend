package fish.common.fishBun.entity;

import fish.common.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "FISH_BUN_CALENDAR")
@Getter
@Entity
@NoArgsConstructor
public class FishBunCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String photo;

    @CreationTimestamp
    private LocalDateTime date;

    @OneToMany(mappedBy = "fishBunCalendar", cascade = CascadeType.ALL)
    private List<CalendarFlavor> flavors;
}
