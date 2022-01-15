package practice.myboard.domain.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id; // 회원 id

    @NotEmpty
    private String loginId; // 로그인 아이디

    @NotEmpty
    private String password; // 로그인 비밀번호

    @NotEmpty
    @Email
    private String email; // 회원 이메일

    @NotEmpty
    private String name; // 회원 이름
}
