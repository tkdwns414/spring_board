package practice.myboard.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.myboard.domain.member.Member;
import practice.myboard.domain.member.MemberRepository;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member){
        return "members/memberAddForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "members/memberAddForm";
        }

        //아이디 중복 체크
        if (memberRepository.findByLoginId(member.getLoginId()).isPresent()){
            bindingResult.reject("RegisterFail", "이미 사용중인 아이디입니다.");
            return "members/memberAddForm";
        }
        memberRepository.save(member);
        return "redirect:/posts";
    }
}
