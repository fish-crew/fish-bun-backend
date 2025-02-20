package fish.global.oauth.dto;

/**
 * Oauth2UserInfo 구현체
 * */
public interface OAuth2UserInfo {

    String getProviderId();
    String getProviderType();
    String getProviderProfile();
}