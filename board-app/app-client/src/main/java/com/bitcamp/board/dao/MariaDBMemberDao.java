package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Member;

public class MariaDBMemberDao {

  public int insert(Member member) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            // 값 넣을 자리 in-parameter, ?로 표시 
            "insert into app_member(name, email, pwd) values(?,?, sha2(?,256))")) {

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);

      // 몇 개를 성공했는지 return
      return pstmt.executeUpdate(); // insert, update, delete 할 때는 executeUpdate! 
    }
  }

  public Member findByNo(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement( // 값 넣을 자리 in-parameter, ?로 표시 
            "select mno,name,email,cdt from app_member where mno=" + no); // no는 int타입 이라서 SQL injection 공격받을 가능성이 없음 
        ResultSet rs =  pstmt.executeQuery()) { // 리턴되는 것은 조회 결과 자체가 아니다. 
      // insert, update, delete 할 때는 executeUpdate! 
      if (!rs.next()) { // 가져왔으면 true, 못가져왔으면 false
        return null;
      }
      Member member = new Member(); // 값을 담을 멤버 객체 생성 
      member.no = rs.getInt("mno");
      member.name = rs.getString("name");
      member.email = rs.getString("email");
      member.createdDate = rs.getDate("cdt");

      return member;
    }
  }

  public int update(Member member) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            // 값 넣을 자리 in-parameter, ?로 표시 
            "update app_member set name=?, email=?, pwd=sha2(?,256) where mno=?")) {

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);
      pstmt.setInt(4, member.no);

      return pstmt.executeUpdate(); // insert, update, delete 할 때는 executeUpdate! 
    }

  }

  public int delete(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt1 = con.prepareStatement("delete from app_board where mno=?");
        PreparedStatement pstmt2 = con.prepareStatement("delete from app_member where mno=?")) {

      // 회원이 작성한 게시글을 삭제한다. 
      pstmt1.setInt(1, no);
      pstmt1.executeUpdate();

      // 회원을 삭제한다. 
      pstmt2.setInt(1, no);
      return pstmt2.executeUpdate();
    }  
  }

  public List<Member> findAll() throws Exception { // List 타입을 리턴한다. 
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(  
            "select mno,name,email from app_member");
        ResultSet rs =  pstmt.executeQuery()) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) { // 서버에서 데이터를 가져왔다면, 
        Member member = new Member(); // 값을 담을 멤버 객체 생성

        member.no = rs.getInt("mno");
        member.name = rs.getString("name");
        member.email = rs.getString("email");

        list.add(member); // 새로 만든 멤버 객체를 리스트에 담아
      }

      return list;
    }
  }
}













