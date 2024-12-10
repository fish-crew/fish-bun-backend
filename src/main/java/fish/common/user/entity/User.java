package fish.common.user.entity;

import fish.core.oauth.dto.OAuth2UserInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Long providerId;

    private String providerType; //타입 구분(kakao, google, naver)
    private String providerProfile;

    private String nickname;
    private Long level;

    @PrePersist
    public void generateUUID() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public User(OAuth2UserInfo userInfo) {
        this.providerId = userInfo.getProviderId();
        this.providerProfile = userInfo.getProviderProfile();
        this.providerType = userInfo.getProviderType();
    }
}

