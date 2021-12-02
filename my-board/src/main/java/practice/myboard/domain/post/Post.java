package practice.myboard.domain.post;

import lombok.Data;
import practice.myboard.domain.member.Member;

import java.time.LocalDateTime;

@Data
public class Post {

    private Long id;

    private String title;
    private String content;
    private Member writer;
    private boolean secret; // 비밀글 설정
    private String password; // 게시판 비밀번호(수정글 사용시)
    private LocalDateTime pubDate;
    private LocalDateTime editDate;
    // todo: 첨부파일


    public Post() {
    }

    public Post(String title, String content, Member writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
