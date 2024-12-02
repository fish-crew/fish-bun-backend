package fish.common.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "users")
@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    // OAuth scope data
    private String providerId;
    private String picture;

    private String nickname;
    private Long level;

    @PrePersist
    public void generateUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public User(String providerId, String picture, String nickname, Long level) {
        this.providerId = providerId;
        this.picture = picture;
        this.nickname = nickname;
        this.level = level;
    }
}

