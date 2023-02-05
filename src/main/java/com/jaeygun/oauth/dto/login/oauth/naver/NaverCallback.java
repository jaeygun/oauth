package com.jaeygun.oauth.dto.login.oauth.naver;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NaverCallback {

    private String code;

    private String state;

    private String error;

    private String error_description;
}
