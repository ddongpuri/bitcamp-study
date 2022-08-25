// 1) 스레드 사용 전 
package com.eomcs.concurrent;

public class Exam0110x {

  public static void main(String[] args) {

    int count = 1000;

    for (int i = 0; i < count; i++) {
      System.out.println("==> " + i);
    } // 위에 작성된 for문을 모두 끝낸 뒤에 

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    } // 그 다음의 for문을 실행한다. 
  }
}

// 자바는 main() 메서드를 실행하는 한 개의 "실행 흐름"이 있다.
// 실행 흐름에 따라 순서대로 코드가 실행된다.


