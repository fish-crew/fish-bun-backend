
package fish.global.oauth.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class GoogleUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes; // getAttributes()
    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("sub");
    }

    @Override
    public String getProviderType() {
        return "google";
    }

    @Override
    public String getProviderProfile() {
        return (String) attributes.get("picture");
    }

}