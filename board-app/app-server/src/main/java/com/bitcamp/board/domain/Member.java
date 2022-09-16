package com.bitcamp.board.domain;

import java.sql.Date;

public class Member {

  public int no;
  public String name;
  public String email;
  public String password;
  public Date createdDate;

  @Override // 가끔 인스턴스에 들어있는 값이 무엇인지 확인해야 할 때 필요하기 때문에 만들어줌 
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
        + ", createdDate=" + createdDate + "]";
  }
}
