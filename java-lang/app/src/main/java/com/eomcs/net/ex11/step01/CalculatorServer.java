// 계산기 서버 만들기 - 1단계: 단순히 클라이언트 요청에 응답하기
package com.eomcs.net.ex11.step01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(8888)) { // 서버 소켓 준비 
      System.out.println("서버 실행 중...");

      try (Socket socket = serverSocket.accept(); // 클라이언트 승인 
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 읽을 도구 준비 
          PrintStream out = new PrintStream(socket.getOutputStream());) { // 출력 도구 준비 

        out.println("계산기 서버에 오신 걸 환영합니다!");
        out.println("계산식을 입력하세요!");
        out.println("예) 23 + 7");
        out.flush();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
