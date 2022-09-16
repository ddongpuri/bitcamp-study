package com.bitcamp.board;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.dao.MariaDBBoardDao;
import com.bitcamp.board.dao.MariaDBMemberDao;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.ErrorHandler;
import com.bitcamp.board.handler.MemberHandler;
import com.bitcamp.board.handler.WelcomeHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MiniWebServer {
  public static void main(String[] args) throws Exception { 
    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");

    BoardDao boardDao = new MariaDBBoardDao(con);
    MemberDao memberDao = new MariaDBMemberDao(con);

    WelcomeHandler welcomeHandler = new WelcomeHandler();
    ErrorHandler errorHandler = new ErrorHandler();
    BoardHandler boardHandler = new BoardHandler(boardDao);
    MemberHandler memberHandler = new MemberHandler(memberDao);

    // 팩토리 메서드( create ) 
    // 메서드 안에다가 객체 생성을 넣어버려서 객체 생성 과정을 단순화 시킨다. 

    // 클라이언트 요청을 처리하는 객체 
    class MyHttpHandler implements HttpHandler {
      @Override
      public void handle(HttpExchange exchange) throws IOException {
        System.out.println("클라이언트가 요청함!");

        URI requestUri = exchange.getRequestURI();
        // return 받은 URI에서 우리가 필요한 것은 Path와 Query 
        String path = requestUri.getPath(); 
        // String query = requestUri.getQuery(); // 디코딩을 제대로 수행하지 못한다. 
        String query = requestUri.getRawQuery(); // 디코딩 없이 query string을 그대로 리턴받기 


        byte[] bytes = null; // Client로 보낼 데이터를 담을 바이트 배열 

        // 입출력 스트림 
        try (StringWriter stringWriter = new StringWriter(); // 출력을 보아서 보내기 위해 StringWriter 
            PrintWriter printWriter = new PrintWriter(stringWriter)) { // PrintWriter로 출력하는 것은 StringWriter 버퍼에 저장됨 

          Map<String, String> paramMap = new HashMap<>();
          if (query != null && query.length() > 0) { // 예) no=1&title=aaaa&content=bbb
            String[] entries = query.split("&"); // &를 기준으로 값을 자른다. 
            for (String entry : entries) { // 예) no=1
              String[] kv = entry.split("=");
              // 웹브라우저가 보낸 파라미터 값을 저장하기전에 URL 디코딩 한다. 
              paramMap.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
            }
          }
          System.out.println(paramMap);

          if (path.equals("/")) {
            welcomeHandler.service(paramMap,printWriter);

          } else if (path.equals("/board/list")) {
            boardHandler.list(paramMap, printWriter);

          } else if (path.equals("/board/detail")) {
            boardHandler.detail(paramMap, printWriter);

          } else if (path.equals("/board/update")) {
            boardHandler.update(paramMap, printWriter);

          } else if (path.equals("/board/add")) {
            boardHandler.add(paramMap, printWriter);

          } else if (path.equals("/board/delete")) {
            boardHandler.delete(paramMap, printWriter);

          } else if (path.equals("/board/form")) {
            boardHandler.form(paramMap, printWriter);

          } else if (path.equals("/member/list")) {
            memberHandler.list(paramMap, printWriter);

          } else if (path.equals("/member/detail")) {
            memberHandler.detail(paramMap, printWriter);

          } else if (path.equals("/member/update")) {
            memberHandler.update(paramMap, printWriter);

          } else if (path.equals("/member/add")) {
            memberHandler.add(paramMap, printWriter);

          } else if (path.equals("/member/delete")) {
            memberHandler.delete(paramMap, printWriter);

          } else if (path.equals("/member/form")) {
            memberHandler.form(paramMap, printWriter);

          } else {
            errorHandler.error(paramMap, printWriter);
          }

          bytes = stringWriter.toString().getBytes("UTF-8");

        } catch (Exception e) {
          bytes = "요청 처리 중 오류발생!".getBytes("UTF-8");
          e.printStackTrace();
        }

        // 보내는 콘텐트의 MIME 타입이 무엇인지 응답 헤더에 추가한다. 
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type","text/html; charset=UTF-8");

        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream out = exchange.getResponseBody();
        out.write(bytes); // 스트링 객체에 있는 것을 바이트로 뽑아낸다.
        out.close();
      }
    }

    HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
    server.createContext("/", new MyHttpHandler()); // (자원의 경로, HTTPHandler 인터페이스 규칙에 따라 작성된 인스턴스)
    server.setExecutor(null);  // HttpServer에 기본으로 설정되어 있는 Executor 사용 
    // Executor? 멀티 스레딩을 수행하는 객체 
    server.start(); // HttpServer를 시작시킨 후 즉시 리턴한다. 
    System.out.println("서버 시작!");
    // main() 메서드 호출이 끝났다 하더라도
    // 내부에서 생성한 스레드(예 : HttpServer)가 종료되지 않으면 JVM도 종료되지 않는다. 
  }
}
