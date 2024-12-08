package fish.core.oauth.service;


import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OAuthUserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 부모 클래스가 제공하는 기본 구현으로 사용자 정보 로드
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Kakao 사용자 정보 추출
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        // 필요한 정보 가져오기
        String id = attributes.get("id").toString(); // 사용자 고유 ID
        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");

        // 사용자 정보를 커스터마이징하여 반환
        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                Map.of("id", id, "nickname", nickname, "email", email),
                "id" // 기본 속성으로 사용할 필드
        );
    }
}
