package com.jaeygun.oauth.dto.login.oauth.naver;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NaverUser {

    // 동일인 식별 정보(사용자 고유 값)
    private String id;

    // 사용자 별명
    private String nickname;

    // 사용자 이름
    private String name;

    // 사용자 메일 주소
    private String email;

    // 성별 (F:여성, M:남성, U:확인불가)
    private String gender;

    // 사용자 연령대 (ex 20-29)
    private String age;

    // 사용자 생일 (MM-DD)
    private String birthday;

    // 출생년도
    private String birthyear;

    // 사용자 프로필 사진 URL
    private String profile_image;

    // 휴대전화번호 (010-0000-0000)
    private String mobile;

    private String mobile_e164;
}

