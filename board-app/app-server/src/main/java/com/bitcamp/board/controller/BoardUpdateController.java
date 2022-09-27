package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

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
      board.no = Integer.parseInt(request.getParameter("no"));
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");

      if (boardDao.update(board) == 0) {
        throw new Exception("게시글 변경 실패!");
      }

      // refresh
      response.setHeader("Refresh", "1;url=list"); //응답헤더에 refresh 삽입 
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






