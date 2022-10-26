package com.bitcamp.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;


@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/auth/")
public class AuthController {

  MemberService memberService;

  public AuthController(MemberService memberService) {
    this.memberService = memberService;
  }

  // InternalResourceViewResolver 설정 후 
  @GetMapping("form")
  public String form(@CookieValue(name="email",defaultValue="") String email, Model model) throws Exception {
    model.addAttribute("email", email);
    return "auth/form";
  }

  // value나 path나 같다. 
  @PostMapping("login")
  public ModelAndView login(
      String email, 
      String password, 
      String saveEmail, 
      HttpServletResponse response,
      HttpSession session) throws Exception {

    Member member = memberService.get(email, password);

    if (member != null) {
      session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장 
    }

    // 클라이언트에게 쿠키 보내기   
    // 쿠키 데이터는 '문자열'만 가능하다. (객체 안된다 ~)
    Cookie cookie = new Cookie("email", email); // 클라이언트쪽에 저장할 쿠키 생성
    if (saveEmail == null) {
      cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 지울 것을 명령한다. 
    } else {
      cookie.setMaxAge(60 * 60 * 24 * 7);
    }
    response.addCookie(cookie);

    ModelAndView mv = new ModelAndView("auth/loginResult");
    mv.addObject("member", member);
    return mv;
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate(); // 현재 세션을 무효화시킨다. 
    return "redirect:../";
  }  
}





