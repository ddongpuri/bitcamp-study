package com.bitcamp.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

// - 이 애노테이션을 붙이면 Spring IoC 컨테이너가 객체를 자동으로 생성할 것이다. 
// - 생성자에 파라미터가 있다면 
@Service // 서비스 역할을 수행하는 객체에 붙이는 애노테이션 
public class DefaultBoardService implements BoardService {

  @Autowired
  BoardDao boardDao; // 인터페이스로 명시 : 교체가 쉽다. 

  @Transactional
  @Override
  public void add(Board board) throws Exception {

    if (boardDao.insert(board) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    if (board.getAttachedFiles().size() > 0) { // 첨부파일이 1개라도 있을 때 
      // 2) 첨부파일 등록 
      boardDao.insertFiles(board);
    }

  }

  @Transactional
  @Override
  public boolean update(Board board) throws Exception {

    // 1) 게시글 변경
    if (boardDao.update(board) == 0) {
      return false;
    }
    // 넘어온 board객체에 새롭게 첨부된 파일이 있는지 우선 체크한 후에 
    // 있으면, 첨부파일을 insert한다. 
    // 없는데도 insert하려하면 오류난다. 

    if (board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
    return true;
  }

  @Override
  public Board get(int no) throws Exception {
    return boardDao.findByNo(no); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다. 
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 1) 첨부파일 삭제
    boardDao.deleteFiles(no);

    // 2) 게시글 삭제
    boolean result = boardDao.delete(no) > 0;

    return result;
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return boardDao.deleteFile(fileNo) > 0;
  }

}








