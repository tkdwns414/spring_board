package practice.myboard.domain.member;

import lombok.Data;

@Data
public class Member {

    private Long id; // 회원 id

    private String loginId; // 로그인 아이디
    private String password; // 로그인 비밀번호
    private String email; // 회원 이메일
    private String name; // 회원 이름
}
