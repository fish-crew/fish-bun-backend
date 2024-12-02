package fish.common.fishBun.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "fish_bun_calendar")
@Getter
@Entity
@NoArgsConstructor
public class FishBunCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
