package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Board;

public class MariaDBBoardDao {

  public int insert(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "insert into app_board(title,cont,mno) values(?,?,?);")) {
      pstmt.setString(1, board.title);
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.memberNo);
      return pstmt.executeUpdate();
    }
  } 

  public Board findByNo(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            "select bno,title,cont,mno,cdt,vw_cnt from app_board where bno=" + no); // no는 int타입 이라서 SQL injection 공격받을 가능성이 없음 
        ResultSet rs =  pstmt.executeQuery()) { // 리턴되는 것은 조회 결과 자체가 아니다. 
      // insert, update, delete 할 때는 executeUpdate! 
      if (!rs.next()) { // 가져왔으면 true, 못가져왔으면 false
        return null;
      }
      Board board = new Board(); // 값을 담을 멤버 객체 생성 
      board.no = rs.getInt("bno");
      board.title = rs.getString("title");
      board.content = rs.getString("cont");
      board.memberNo = rs.getInt("mno");
      board.createdDate = rs.getDate("cdt");
      board.viewCount = rs.getInt("vw_cnt");

      return board;
    }
  }

  public int update(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(
            // 값 넣을 자리 in-parameter, ?로 표시 
            "update app_board set title=?, cont=? where bno=?")) {

      pstmt.setString(1, board.title);
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.no);

      return pstmt.executeUpdate(); // insert, update, delete 할 때는 executeUpdate! 
    }

  }

  public int delete(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement("delete from app_board where bno=?")) {

      pstmt.setInt(1, no);
      pstmt.executeUpdate();

      // 회원을 삭제한다. 
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();
    }  
  }

  public List<Board> findAll() throws Exception { // List 타입을 리턴한다. 
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");
        PreparedStatement pstmt = con.prepareStatement(  
            "select bno, title, mno, cdt, vw_cnt from app_board;");
        ResultSet rs =  pstmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) { 
        Board board = new Board(); 
        board.no = rs.getInt("bno");
        board.title = rs.getString("title");
        board.memberNo = rs.getInt("mno");
        board.createdDate = rs.getDate("cdt");
        board.viewCount = rs.getInt("vw_cnt");

        list.add(board); // 새로 만든 멤버 객체를 리스트에 담아
      }

      return list;
    }
  }
}













