package com.jaeygun.oauth.dto.login.oauth.naver;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class NaverToken {

    private String access_token;

    private String refresh_token;

    private String token_type;

    private String expires_in;

    private String error;

    private String error_description;
}
