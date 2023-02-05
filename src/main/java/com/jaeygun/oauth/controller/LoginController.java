package com.jaeygun.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaeygun.oauth.config.ApplicationEnvironmentConfig;
import com.jaeygun.oauth.dto.login.oauth.naver.NaverCallback;
import com.jaeygun.oauth.dto.login.oauth.naver.NaverRes;
import com.jaeygun.oauth.dto.login.oauth.naver.NaverToken;
import com.jaeygun.oauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final ApplicationEnvironmentConfig envConfig;
    private final LoginService loginService;

    @GetMapping("/oauth/login")
    public void callBack(HttpServletRequest request, HttpServletResponse response, NaverCallback callback) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException, JsonProcessingException {

        if (callback.getError() != null) {
            System.out.println(callback.getError_description());
        }

        String responseToken = loginService.getNaverTokenUrl("token", callback);

        ObjectMapper mapper = new ObjectMapper();
        NaverToken token = mapper.readValue(responseToken, NaverToken.class);
        String responseUser = loginService.getNaverUserByToken(token);
        NaverRes naverUser = mapper.readValue(responseUser, NaverRes.class);

        System.out.println("naverUser.toString() : " + naverUser.toString());
        System.out.println("naverUser.getResonse().getGender() : " + naverUser.getResponse().getGender());
        System.out.println("naverUser.getResonse().getBirthyear() : " + naverUser.getResponse().getBirthyear());
        System.out.println("naverUser.getResonse().getAge() : " + naverUser.getResponse().getAge());

    }

    @GetMapping("/naver-login")
    public void naverLogin(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        String url = loginService.getNaverAuthorizeUrl("authorize");
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
