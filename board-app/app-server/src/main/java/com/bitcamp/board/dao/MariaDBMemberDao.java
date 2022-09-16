package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Member;

public class MariaDBMemberDao implements MemberDao {

  Connection con;

  // DAO가 사용할 의존 객체 Connection을 생성자의 파라미터로 받는다. 
  public MariaDBMemberDao(Connection con) { // 여기서 try 쓰면 안됑 닫아버리자나 
    this.con = con;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        // 값 넣을 자리 in-parameter, ?로 표시 
        "insert into app_member(name, email, pwd) values(?,?, sha2(?,256))")) {

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);

      // 몇 개를 성공했는지 return
      return pstmt.executeUpdate(); // insert, update, delete 할 때는 executeUpdate! 
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement( // 값 넣을 자리 in-parameter, ?로 표시 
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

  @Override
  public int update(Member member) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        // 값 넣을 자리 in-parameter, ?로 표시 
        "update app_member set name=?, email=?, pwd=sha2(?,256) where mno=?")) {

      pstmt.setString(1, member.name);
      pstmt.setString(2, member.email);
      pstmt.setString(3, member.password);
      pstmt.setInt(4, member.no);

      return pstmt.executeUpdate(); // insert, update, delete 할 때는 executeUpdate! 
    }

  }

  @Override
  public int delete(int no) throws Exception {
    try (PreparedStatement pstmt1 = con.prepareStatement("delete from app_board where mno=?");
        PreparedStatement pstmt2 = con.prepareStatement("delete from app_member where mno=?")) {

      // 커넥션 객체를 수동 커밋 상태로 설정한다. 
      con.setAutoCommit(false);

      // 회원이 작성한 게시글을 삭제한다. 
      pstmt1.setInt(1, no);
      pstmt1.executeUpdate();

      // 회원을 삭제한다. 
      pstmt2.setInt(1, no); // 여기서 에러 뜨면 commit 못하고 catch 문으로 내려감 
      int count = pstmt2.executeUpdate(); // 삭제한 record수를 변수에 저장해놓는다. (리턴 전에 커밋하려고)

      // 현재까지 작업한 데이터 변경 결과를 실제 테이블에 적용해 달라 요청한다. 
      con.commit();

      return count;

    } catch (Exception e) { // 여기서 catch한 이유는 예외처리를 위한 것이 아니라 Rollback을 위한 것이다. 
      // 예외가 발생하면 마지막 커밋 상태로 돌린다. 
      // => 임시 데이터베이스에 보관된 이전 작업 결과를 모두 취소한다. 
      con.rollback();

      throw e;

      // 예외발생 사실을 호출자에게 전달한다. 
    }  finally {
      // 삭제 작업 후 자동커밋 상태로 전환한다. 
      con.setAutoCommit(true);
    }
  }

  @Override
  public List<Member> findAll() throws Exception { // List 타입을 리턴한다. 
    try (PreparedStatement pstmt = con.prepareStatement(  
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













