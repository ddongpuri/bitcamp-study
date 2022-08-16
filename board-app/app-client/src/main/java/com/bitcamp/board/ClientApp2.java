package com.bitcamp.board;

import java.net.Socket;

public class ClientApp2 {
  public static void main(String[] args) /* throws Exception */ {

    // 네트워크 준비 (Socket 클래스 준비. 생성자에 접속할 서버의 IP 주소와 포트번호를 넘긴다 ) 
    // => 정상적으로 연결되었으면 Socket 객체를 리턴한다. 
    try {
      Socket socket = new Socket("127.0.0.1", 8888);

      System.out.println("연결되었음!");
      // 네트워크 끊기 
      // => 서버와 연결된 것을 끊는다. 
      socket.close();
      System.out.println("연결을 끊었음!");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("종료!!");
  }
}