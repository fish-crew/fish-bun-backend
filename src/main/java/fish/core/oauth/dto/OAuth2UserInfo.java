package fish.core.oauth.dto;

/**
 * Oauth2UserInfo 구현체(나중에 카카오톡 뿐만 아니라, 구글, 네이버 로그인 기능 구현시에 구현체로써 활용)
 * */
public interface OAuth2UserInfo {

    Long getProviderId();
    String getProviderType();
    String getProviderProfile();
}
