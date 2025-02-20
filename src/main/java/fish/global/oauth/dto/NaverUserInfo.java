
package fish.global.oauth.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class NaverUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes; // getAttributes()
    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("id");
    }

    @Override
    public String getProviderType() {
        return "naver";
    }

    @Override
    public String getProviderProfile() {
        return (String) attributes.get("profile_image");
    }

}