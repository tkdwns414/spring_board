package practice.myboard.web.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.myboard.domain.member.Member;
import practice.myboard.domain.post.Post;
import practice.myboard.domain.post.PostRepository;
import practice.myboard.web.argumentresolver.Login;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public String posts(@Login Member loginMember, Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("member", loginMember);
        return "posts/posts";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable long postId, @Login Member loginMember, Model model){
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("loginMember", loginMember);
        return "posts/post";
    }

    @GetMapping("/add")
    public String addPost(Model model){
        model.addAttribute("post", new Post());
        return "posts/addForm";
    }

    @PostMapping("/add")
    public String addPost(@Validated @ModelAttribute("post") Post form, @Login Member loginMember, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "posts/addForm";
        }

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setWriter(loginMember);

        Post savedPost = postRepository.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@Login Member loginMember, @PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);

        if (post.getWriter() != loginMember){
            log.info("잘못된 게시물 수정 요청");
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "posts/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable long postId, @Validated @ModelAttribute("post") Post form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "posts/editForm";
        }

        Post postParam = new Post();
        postParam.setTitle(form.getTitle());
        postParam.setContent(form.getContent());

        postRepository.update(postId,postParam);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("{postId}/delete")
    public String deletePost(@PathVariable long postId, @Login Member loginMember){
        if (postRepository.findById(postId).getWriter() != loginMember){
            log.info("잘못된 게시물 삭제 요청");
            return "redirect:/posts";
        }
        postRepository.delete(postId);
        return "redirect:/posts";
    }

}
