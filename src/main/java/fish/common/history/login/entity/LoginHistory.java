package fish.common.history.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "LOGIN_HISTORY")
@Getter
@NoArgsConstructor
@Entity
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logNum;
    private Long providerId;
    private String ip;
    @CreationTimestamp
    private LocalDateTime date;

    public LoginHistory(String ip, Long providerId) {
        this.providerId = providerId;
        this.ip = ip;
    }
}
