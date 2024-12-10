package fish.core.oauth.service;

import fish.core.oauth.dto.AuthUserInfo;
import fish.core.oauth.dto.KakaoUserInfo;
import fish.core.oauth.dto.OAuth2UserInfo;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthUserService extends DefaultOAuth2UserService {
    private final UserService userService;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo userInfo;
        //현재 카카오톡만 있기에 카카오톡 처리만 들어감. (추후에 네이버, 구글 로그인도 들어갈 예정)
        switch (registrationId) {
            case "kakao": userInfo = new KakaoUserInfo(oAuth2User.getAttributes()); break;
            default: return null;
        }

        User user = saveUser(userInfo);
        return new AuthUserInfo(
                oAuth2User.getAuthorities(),
                oAuth2User.getAttributes(),
                "id",
                user
        );
    }

    private User saveUser(OAuth2UserInfo oauth2UserInfo) {
        return userService.saveUser(new User(oauth2UserInfo));
    }
}
