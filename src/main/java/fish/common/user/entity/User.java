package fish.common.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Table(name = "USER")
@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;

    @Column(unique = true, nullable = false)
    private String uuid;

    // OAuth scope data
    private Long providerId;
    private String profileUrl;
    private String nickname;
    private Long level;

    private String type; //타입 구분

    @PrePersist
    public void generateUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public User(Map<String, Object> userInfo, String type) {
        Map<String, Object> properties = ((Map<String, Object>) userInfo.get("properties"));
        this.providerId = (Long) userInfo.get("id");
//        this.providerNickname = properties.get("nickname").toString();
        this.profileUrl = properties.get("profile_image").toString();
        this.type = type;
    }
}

