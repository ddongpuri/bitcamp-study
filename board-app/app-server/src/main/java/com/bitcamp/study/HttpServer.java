package com.bitcamp.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
  public static void main(String[] args) throws Exception{
    try (ServerSocket ss = new ServerSocket(80);
        Socket socket = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {




    }
  }
}
