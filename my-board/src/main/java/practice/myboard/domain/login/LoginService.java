package practice.myboard.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.myboard.domain.member.Member;
import practice.myboard.domain.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    // 맞으면 Member 틀리면 null 반환
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
