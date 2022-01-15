package practice.myboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.myboard.domain.member.Member;
import practice.myboard.domain.member.MemberRepository;
import practice.myboard.domain.post.Post;
import practice.myboard.domain.post.PostRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitialDataInit {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        member.setEmail("sj@naver.com");

        memberRepository.save(member);

        Post post1 = new Post("Test title A", "This is test content for first post", memberRepository.findByID(1L));
        postRepository.save(post1);
        Post post2 = new Post("Test title B", "내용내욘내용내용내용", memberRepository.findByID(1L));
        postRepository.save(post2);

    }
}
