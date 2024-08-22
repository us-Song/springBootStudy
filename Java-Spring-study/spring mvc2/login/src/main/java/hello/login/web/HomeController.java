package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static hello.login.web.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    private final SessionManager sessionManager;

    //@GetMapping("/")
    public String home() {
        return "home";
    }


    //@GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        //쿠키 없으면 홈으로 보냄
        if (memberId == null) {
            return "home";
        }

        //로그인 , 쿠키는 있지만 해당 회원이 없으면 홈으로 보냄
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        //쿠키가 있는 사용자는 로그인 회원 전용 홈으로 보냄
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        //세션 관리자에 저장된 회원 정보 조회
        Member member = (Member) sessionManager.getSession(request);
        if (member == null) {
            return "home";
        }

        //쿠키가 있는 사용자는 로그인 회원 전용 홈으로 보냄
        model.addAttribute("member", member);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false); //로그인 안한 사용자는 세션이 없어야 함
        if(session == null) {
            return "home";
        }
        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);

        //세션에 회원 데이터 없으면 홈화면
        if(loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = LOGIN_MEMBER, required = false) Member loginMember ,Model model) {
        //이미 로그인 된 사용자를 찾을 때 사용 -> name으로 세션을 꺼내서 컨트롤러로 넘겨줌

        //세션에 회원 데이터 없으면 홈화면
        if(loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}