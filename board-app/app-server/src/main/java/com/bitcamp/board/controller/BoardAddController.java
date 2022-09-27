package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Board board = new Board();
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");
      board.memberNo = Integer.parseInt(request.getParameter("writerNo"));

      if (boardDao.insert(board) == 0) {
        throw new Exception("게시글 등록 실패!");
      }

      // refresh
      response.setContentType("text/html;charset=UTF-8");  
      request.getRequestDispatcher("/board/add.jsp").include(request, response); 

      // Redirect
      // - 클라이언트에게 콘트를 보내지 않는다. 
      // - 응답 프로토콜 
      // response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);  
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }  
}






