package practice.myboard.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Member save(Member member);

    public Member findByID(Long id);

    public Optional<Member> findByLoginId(String loginId);

    public List<Member> findAll();

    public void deleteById(Long id);
}