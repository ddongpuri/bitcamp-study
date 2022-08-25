// Runnable interface를 구현하여 Thread 만들어보

// 1) 스레드 사용 전
// 2) 스레드 사용 후 : Runnable 구현체를 패키지 멤버로 만들어 스레드로 실행한다. 
package com.eomcs.concurrent;

public class Exam0210 {

  public static void main(String[] args) {

    int count = 1000;

    for (int i = 0; i < count; i++) {
      System.out.println("==> " + i);
    }

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    } 
  }

}


