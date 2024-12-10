package fish.common.main.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Table(name = "FISH_BUN_DETAIL")
@Getter
@Entity
@NoArgsConstructor
public class FishBunDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String flavors;         // 비정규화
    @CreationTimestamp
    private LocalDateTime date;
}
