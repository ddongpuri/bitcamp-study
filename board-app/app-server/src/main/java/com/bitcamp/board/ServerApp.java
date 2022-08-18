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
    System.out.println("[게시글 데이터 관리 서버]");

    try (
        ServerSocket serverSocket = new ServerSocket(8888);) {

      System.out.println("서버 소켓 준비 완료!");

      // 무한루프 돌때마다 새로 만들 필요 없음. 따라서 while문 밖으로 빼내자 
      Hashtable<String,Servlet> servletMap = new Hashtable<>();
      servletMap.put("board", new BoardServlet("board"));
      servletMap.put("reading", new BoardServlet("reading"));
      servletMap.put("visit", new BoardServlet("visit"));
      servletMap.put("notice", new BoardServlet("notice"));
      servletMap.put("daily", new BoardServlet("daily"));
      servletMap.put("member", new MemberServlet("member"));

      while (true) {
        try(
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {

          System.out.println("클라이언트와 연결 되었음!");


          while (true) {

            String dataName = in.readUTF(); // Client로부터 dataName을 입력받은 

            if (dataName.equals("exit")) { // 만약 exit이면, while문을 나가고, 서버 연결 끊긴다. 
              break;
            }

            Servlet servlet = servletMap.get(dataName);
            if (servlet != null) {
              servlet.service(in, out);
            } else {
              out.writeUTF("fail");
            }
          }
          System.out.println("클라이언트와 연결 끊었음!");

        } // 안쪽 try
      }
    } catch (Exception e) {
      e.printStackTrace();
    } // 바깥 쪽 try

    System.out.println("서버 종료!");
  }
}
