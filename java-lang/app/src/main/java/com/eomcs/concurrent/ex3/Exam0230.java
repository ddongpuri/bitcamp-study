// Runnable 인터페이스 구현 + Thread - 람다(lambda)로 구현하기
// 람다식은 functional interface(=추상메서드가 하나!)만 가능 
package com.eomcs.concurrent.ex3;

public class Exam0230 {

  public static void main(String[] args) {

    new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        System.out.println("===> " + i);
      }
    }).start();

    for (int i = 0; i < 1000; i++) {
      System.out.println(">>>> " + i);
    }

  }

}
