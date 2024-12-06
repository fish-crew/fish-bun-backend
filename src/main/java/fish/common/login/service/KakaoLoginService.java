package fish.common.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.history.login.entity.LoginHistory;
import fish.common.history.login.serivce.LoginHistoryService;
import fish.common.login.dto.KakaoOauthDto;
import fish.common.user.entity.User;
import fish.common.user.service.UserService;
import fish.common.util.IpAddressUtil;
import fish.common.util.RestApiRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.UnknownHostException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUri;

    @Value("${kakao.user-info-uri}")
    private String userInfoUri;

    @Value("${kakao.logout-uri}")
    private String logoutUri;

    private final UserService userService;
    private final LoginHistoryService loginHistoryService;
    
    public void login(String code, HttpServletRequest request)
            throws JsonProcessingException, UnknownHostException {
        KakaoOauthDto oauth = getOauth(code);
        Map<String, Object> userInfo = getUserInfo(oauth);
        setLoginSession(userInfo, request, oauth);
        saveUser(userInfo);
        saveHistory(userInfo, request);
    }
    
    public void logout(HttpServletRequest request) {
        String url = logoutUri;
        HttpHeaders headers = new HttpHeaders();
        HttpSession session = request.getSession();
        String accessToken = (String) session.getAttribute("KakaoAccessToken");
        headers.set("Authorization", "Bearer " + accessToken);

        // Body set
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();

        body.add("target_id_type", "authorization_code");
        body.add("target_id", Long.valueOf((String) session.getAttribute("userId")));
        RestApiRequest.request(HttpMethod.POST, url, body, headers);

    }

    private KakaoOauthDto getOauth(String code) throws JsonProcessingException {
        String url = tokenUri;
        // Header set
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE) + ";charset=utf-8"));

        //카카오 Oauth 요청값 send
        String response = RestApiRequest.request(HttpMethod.POST, url, setOauthBody(code), headers);

        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return objectMapper.readValue(response, KakaoOauthDto.class);
    }

    private MultiValueMap<String,Object> setOauthBody(String code) {
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);
        return body;
    }

    private Map<String, Object> getUserInfo(KakaoOauthDto oauth) throws JsonProcessingException {
        String url = userInfoUri;
        // Header set
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE) + ";charset=utf-8"));
        headers.set("Authorization", "bearer " + oauth.getAccess_token());

        String response = RestApiRequest.request(HttpMethod.POST, url, null, headers);
        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        return objectMapper.readValue(response, Map.class);
    }

    private void setLoginSession(Map<String, Object> userInfo, HttpServletRequest request, KakaoOauthDto oauth) {
        String userId= String.valueOf(userInfo.get("id"));
        String nickname =((Map) userInfo.get("properties")).get("nickname").toString();
        request.getSession().setAttribute("userId", userId);
        request.getSession().setAttribute("nickname", nickname);
        request.getSession().setAttribute("KakaoAccessToken", oauth.getAccess_token());
    }

    private void saveUser(Map<String, Object> userInfo) {
        User user = new User(userInfo, "kakao");
        if (!isExistedUser(user.getProviderId())) {
            userService.saveUser(user);
        }
    }

    private Boolean isExistedUser(Long id) {
        return userService.isExistedId(id);
    }

    private void saveHistory(Map<String, Object> userInfo, HttpServletRequest request)
            throws UnknownHostException {
        String ip = IpAddressUtil.getClientIp(request);
        Long providerId = (Long) userInfo.get("id");
        LoginHistory loginHistory = new LoginHistory(ip, providerId);
        loginHistoryService.saveHistory(loginHistory);
    }
}

