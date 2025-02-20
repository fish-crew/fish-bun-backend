package fish.global.oauth.service;

import fish.global.oauth.dto.*;
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
        String attributeKey;
        switch (registrationId) {
            case "kakao":
                userInfo = new KakaoUserInfo(oAuth2User.getAttributes());
                attributeKey = "id";
                break;
            case "google":
                userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
                attributeKey = "sub";
                break;
            case "naver":
                userInfo = new NaverUserInfo(oAuth2User.getAttributes());
                attributeKey = "response";
                break;
            default: return null;
        }

        User user = saveUser(userInfo);
        return new AuthUserInfo(
                oAuth2User.getAuthorities(),
                oAuth2User.getAttributes(),
                attributeKey,
                user
        );
    }

    private User saveUser(OAuth2UserInfo oauth2UserInfo) {
        return userService.saveUser(new User(oauth2UserInfo));
    }
}
