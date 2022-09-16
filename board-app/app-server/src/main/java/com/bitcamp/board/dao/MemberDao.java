package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Member;

public interface MemberDao { // public이고 abstract라서 앞에 붙일 필요 없음 

  int insert(Member member) throws Exception;

  Member findByNo(int no) throws Exception; 

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  List<Member> findAll() throws Exception;   

}













