package com.bitcamp.board.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  //  @Override
  //  // Tomcat서버는 얘를 호출한다. 
  //  public void service(ServletRequest req, ServletResponse res)
  //      throws ServletException, IOException {
  //    HttpServletRequest httpRequest = (HttpServletRequest) req;
  //    HttpServletResponse httpResponse = (HttpServletResponse) res;
  //    service(httpRequest, httpResponse); // 얘는 아래애를 호출한다. 
  //  }

  //  @Override
  //  protected void service(HttpServletRequest req, HttpServletResponse resp)
  //      throws ServletException, IOException {
  //    super.service(req, resp);
  //  }

  BoardDao boardDao;

  // init(ServletConfig) 메서드에 코드를 넣고 싶으면 
  // 다음과 같이 JspPage.jspInit() 메서드를 오버라이딩 하라!
  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      List<Board> boards = boardDao.findAll(); // 요청 데이터 준비 

      // JSP가 사용할 수 있도록 ServletRequest보관소에 저장한다. 
      req.setAttribute("boards", boards);

      // JSP에게 UI 생성을 위임한다. 
      resp.setContentType("text/html;charset=UTF-8"); // JSP가 출력할 콘텐트의 MIME 타입 설정 (누가? 서블릿이) 
      RequestDispatcher 요청배달자 = req.getRequestDispatcher("/board/list.jsp");
      요청배달자.include(req, resp); // JSP 실행한 후 리턴된다. 

    } catch (Exception e) {
      // 예외가 발생하면 예외를 처리하는 JSP에게 UI생성을 위임한다. 
      RequestDispatcher 요청배달자 = req.getRequestDispatcher("/error.jsp");

      // JSP를 실행하기 전에 ServletRequest 보관소에 오류 정보를 담는다. 
      req.setAttribute("exception", e); // exception이라는 이름으로 에러 정보를 담는다. 

      // forward(): 
      // - 예외가 발생하면 기존의 출력 내용을 모두 버린다.
      // - JSP에게 처음부터 새로 출력하게 전권을 위임한다. 
      요청배달자.forward(req, resp); // JSP 실행한 후 리턴된다. 
    }
  }  

}






