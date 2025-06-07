package fish.domain.user.index;

import fish.global.oauth.dto.OAuth2UserInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "USER")
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

    private String providerType; //타입 구분(kakao, google, naver)
    private String providerProfile;

    @Setter
    private String nickname;
    private Long level;
    private String isFirstLogin;
    @CreationTimestamp
    private LocalDateTime regDate;
    private LocalDateTime lastDate;  // 최근 접속 일자

    @PrePersist
    public void generateUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public void updateLastDate() {
        this.lastDate = LocalDateTime.now();
    }

    public User(OAuth2UserInfo userInfo) {
        this.providerId = userInfo.getProviderId();
        this.providerProfile = userInfo.getProviderProfile();
        this.providerType = userInfo.getProviderType();
    }

    public void updateIsFirstLogin() {
        this.isFirstLogin = "N";
    }
}

