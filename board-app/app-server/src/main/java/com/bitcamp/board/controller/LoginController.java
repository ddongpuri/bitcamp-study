package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      Member member = memberDao.findByEmailPassword(email, password);

      if (member != null) {
        HttpSession session = request.getSession(); // 요청한 클라이언트의 전용 HttpSession 보관소를 얻는다. 
        session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장 
      }

      request.setAttribute("member", member);

      response.setContentType("text/html;charset=UTF-8"); // JSP가 출력할 콘텐트의 MIME 타입 설정 (누가? 서블릿이) 
      request.getRequestDispatcher("/auth/loginResult.jsp").include(request, response); // JSP 실행한 후 리턴된다. 

    } catch (Exception e) {
      request.setAttribute("exception", e); // exception이라는 이름으로 에러 정보를 담는다. 
      request.getRequestDispatcher("/error.jsp").forward(request, response); // JSP 실행한 후 리턴된다. 
    }
  }  
}





