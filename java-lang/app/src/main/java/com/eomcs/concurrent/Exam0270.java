// 1) 스레드 사용 전 
// 4) 스태틱 중첩 클래스를 로컬 클래스로 만든다. 
// 5) 로컬 클래스의 특징을 활용하여 바깥 변수의 값을 받는 코드를 제거한다. 
// 6) 로컬 클래스를 익명 클래스로 만든다. 
// 7) 익명 클래스 코드를 메서드 파라미터에 직접 넣는다. 

package com.eomcs.concurrent;

public class Exam0270 {

  public static void main(String[] args) {

    int count = 1000;

    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }).start();

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    } 
  }



}


