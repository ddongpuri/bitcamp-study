package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Board;

public class BoardDaoTest {
  public static void main(String[] args) throws Exception {
    MariaDBBoardDao dao = new MariaDBBoardDao();
    List<Board> list = dao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    System.out.println("----------------------");


    // Board member = new Board();
    // member.name = "홍길동";
    // member.email = "hong@test.com";
    // member.password = "1111";
    // dao.insert(member);

    dao.delete(12);
    //
    Board board = new Board();
    board.no = 13;
    board.title = "xxxx";
    board.content = "okok";
    dao.update(board);

    //    Board member2 = dao.findByNo(1);
    //    System.out.println(member2);

    //    Board member = dao.findByNo(1);
    //    System.out.println(member);

    list = dao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }

    System.out.println("----------------------");

  }
}
