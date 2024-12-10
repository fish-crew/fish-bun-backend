package fish.common.history.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "USER_HISTORY")
@Getter
@NoArgsConstructor
@Entity
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long providerId;
    private String ip;
    @CreationTimestamp
    private LocalDateTime date;

    public UserHistory(Long providerId, String ip) {
        this.providerId = providerId;
        this.ip = ip;
    }
}
