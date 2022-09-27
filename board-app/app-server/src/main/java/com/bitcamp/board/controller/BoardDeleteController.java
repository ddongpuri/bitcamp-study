package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;

@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {

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
      int no = Integer.parseInt(request.getParameter("no"));

      if (boardDao.delete(no) == 0) {
        throw new Exception("게시글 삭제 실패!");
      }
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);  
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }  
}





