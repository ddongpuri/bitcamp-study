package com.bitcamp.board.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.bitcamp.board.domain.Member;

@Component
public class AdminCheckFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // 메서드가 호출되었고, 객체가 생성되었다. 
    System.out.println("AdminCheckFilter.init() 실행!");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    System.out.println("AdminCheckFilter.doFilter() 실행!");
    if (httpRequest.getServletPath().startsWith("/member")) {
      Member loginMember = (Member) httpRequest.getSession().getAttribute("loginMember");
      if (loginMember == null || // 로그인 안됐거나 
          !loginMember.getEmail().equals("admin@test.com")) { // loginMember의 이메일이 관리자 계정이 아니라면  
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
        return;
      }
    }

    chain.doFilter(request, response);
  }
}
