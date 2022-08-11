package com.bitcamp.board.domain;

import java.io.Serializable;

public class Board implements Serializable {
  private static final long serialVersionUID = 1L;
  // 인스턴스를 생성할 때 준비되는 메모리를 선언
  public int no;
  public String title;
  public String content;
  public String writer;
  public String password;
  public int viewCount;
  public long createdDate;
  public String gender;

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", password=" + password + ", viewCount=" + viewCount 
        + ", createdDate=" + new java.sql.Date(createdDate) 
        + "]";
  }

  // GoF의 Factory Method 패턴 
  // - 객체 생성 과정이 복잡할 때 별도의 메서드로 캡슐화한다. 
  // 
  public static Board create(String csv) {
    String[] values = csv.split(",");

    Board board = new Board();
    board.no = Integer.parseInt(values[0]);
    board.title = values[1]; 
    board.content = values[2]; 
    board.writer = values[3];
    board.password = values[4];
    board.viewCount = Integer.parseInt(values[5]);
    board.createdDate = Long.parseLong(values[6]);

    return board;
  }

  // 정보 생성은 그 데이터를 갖고 있는 전문가에게 맡긴다. 
  // => GRASP 패턴의 Information Expert 패턴 
  public String toCsv() {
    return String.format("%d,%s,%s,%s,%s,%d,%d",
        this.no,
        this.title,
        this.content,
        this.writer,
        this.password,
        this.viewCount,
        this.createdDate);
  }
}









