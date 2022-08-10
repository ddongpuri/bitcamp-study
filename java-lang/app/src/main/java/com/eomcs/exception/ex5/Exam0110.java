// 예외 처리 전 - 1) 수동으로 자원해제 close()
package com.eomcs.exception.ex5;

import java.sql.Date;
import java.util.Scanner;

public class Exam0110 {

  static Board read() {
    Scanner keyScan = new Scanner(System.in);

    Board board = new Board();

    System.out.print("번호> ");
    board.setNo(Integer.parseInt(keyScan.nextLine()));

    System.out.print("제목> ");
    board.setTitle(keyScan.nextLine());

    System.out.print("내용> ");
    board.setContent(keyScan.nextLine());

    System.out.print("등록일> ");
    board.setCreatedDate(Date.valueOf(keyScan.nextLine()));

    keyScan.close();
    // 주의!
    // close()를 호출하기 전에 예외가 발생한다면, 
    //  Scanner 자원이 해제되지 못한다.
    return board;
  } 


  public static void main(String[] args) {
    Board board = read();
    System.out.println("---------------------");
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("등록일: %s\n", board.getCreatedDate());
  }
}


