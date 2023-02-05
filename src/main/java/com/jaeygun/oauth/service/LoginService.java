package com.jaeygun.oauth.service;

import com.jaeygun.oauth.config.ApplicationEnvironmentConfig;
import com.jaeygun.oauth.dto.login.oauth.naver.NaverCallback;
import com.jaeygun.oauth.dto.login.oauth.naver.NaverToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final ApplicationEnvironmentConfig envConfig;

    public String getNaverAuthorizeUrl(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {

        String baseUrl = envConfig.getConfigValue("login.naver.baseUrl");
        String clientId = envConfig.getConfigValue("login.naver.clientId");
        String redirectUrl = envConfig.getConfigValue("login.naver.redirectUrl");

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(baseUrl + "/" + type)
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", URLEncoder.encode(redirectUrl, "UTF-8"))
                .queryParam("state", URLEncoder.encode("1234", "UTF-8"))
                .build();

        return uriComponents.toString();
    }
    public String getNaverTokenUrl(String type, NaverCallback callback) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {

        String baseUrl = envConfig.getConfigValue("login.naver.baseUrl");
        String clientId = envConfig.getConfigValue("login.naver.clientId");
        String clientSecret = envConfig.getConfigValue("login.naver.clientSecret");

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(baseUrl + "/" + type)
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", callback.getCode())
                .queryParam("state", URLEncoder.encode(callback.getState(), "UTF-8"))
                .build();

        try {
            URL url = new URL(uriComponents.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNaverUserByToken(NaverToken token) {

        try {
            String accessToken = token.getAccess_token();
            String tokenType = token.getToken_type();

            URL url = new URL("https://openapi.naver.com/v1/nid/me");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", tokenType + " " + accessToken);

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
