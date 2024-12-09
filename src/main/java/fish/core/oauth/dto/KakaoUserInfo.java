
package fish.core.oauth.dto;

import lombok.Getter;
import java.util.Map;

@Getter
public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes; // getAttributes()
    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Long getProviderId() {
        return (Long) attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderProfile() {
        return ((Map)attributes.get("properties")).get("profile_image").toString();
    }


}