package com.bitcamp.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import com.bitcamp.board.servlet.BoardServlet;
import com.bitcamp.board.servlet.MemberServlet;
import com.bitcamp.servlet.Servlet;

public class ServerApp {
  public static void main(String[] args) {
    // 클라이언트 요청을 처리할 객체 준비 
    // local class가 사용하니까, 먼저 정의해주자 
    Hashtable<String,Servlet> servletMap = new Hashtable<>();
    servletMap.put("board", new BoardServlet("board"));
    servletMap.put("reading", new BoardServlet("reading"));
    servletMap.put("visit", new BoardServlet("visit"));
    servletMap.put("notice", new BoardServlet("notice"));
    servletMap.put("daily", new BoardServlet("daily"));
    servletMap.put("member", new MemberServlet("member"));

    // 스레드로 만드는 대신에 Thread가 실행할 수 있는 클래스로 변경한다. 

    System.out.println("[게시글 데이터 관리 서버]");

    try (ServerSocket serverSocket = new ServerSocket(8888);) {

      System.out.println("서버 소켓 준비 완료!");


      while (true) {
        // 람다 문법에서는 인스턴스 필드는 처리할 수 없다. 
        // 따라서 다시 로컬 변수로 전환한다. 
        Socket socket = serverSocket.accept(); // 클라이언트와 연결하는 순간 리턴 

        new Thread(() -> {
          try (Socket socket2 = socket;
              // socket2 사용 안하는데 그냥 안에서 안받아주면 안되나요? 
              // try 안에 묶어주는 근본적인 이유? 자동으로 닫아주라고 ~
              DataInputStream in = new DataInputStream(socket.getInputStream());
              DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {

            System.out.println("클라이언트와 연결 되었음!");

            String dataName = in.readUTF();

            Servlet servlet = servletMap.get(dataName);
            if (servlet != null) {
              servlet.service(in, out);
            } else {
              out.writeUTF("fail");
            }

            System.out.println("클라이언트와 연결을 끊었음!");

          } catch (Exception e) {
            System.out.println("클라이언트 요청 처리 중 오류 발생!");
            e.printStackTrace();
          }
        }).start();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } // 바깥 쪽 try 

    System.out.println("서버 종료!");
  }

}
