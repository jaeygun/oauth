package com.jaeygun.oauth.dto.login.oauth.naver;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NaverRes {

    // API 호출 결과 코드
    private String resultcode;

    // 호출 결과 메시지
    private String message;

    // 응답 사용자 정보
    private NaverUser response;
}
