// 1) 스레드 사용 전 
// 2) 스레드 사용 후 => 패키지 멤버 클래스로 스레드 구현하기 
// 3) 인스턴스 생성 후 즉시 메서드 호출하기
// 4) 패키지 멤버를 static 중첩 클래스로 만든다. 
// 5) static 중첩 클래스를 로컬 클래스로 만든다. 
// 6) 로컬 클래스가 바깥 메서드의 변수를 사용할 때 
//    로컬 클래스에서 그 변수의 값을 다룰 수 있도록 
//    그와 관련된 인스턴스 필드와 생성자 파라미터를 
//   컴파일러가  자동으로 만드는 기법을 활용한다.   
// 7) 로컬 클래스를 익명 클래스로 만든다. 
// 8) 익명 클래스의 인스턴스가 들어갈 자리에 익명 클래스 정의 코드를 직접 둔다. 

package com.eomcs.concurrent;

public class Exam0180 {

  public static void main(String[] args) {

    int count = 1000;

    // 굳이 사용하지 않아도 되는 레퍼런스 변수는 선언하지 않아도 된다. 
    new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < count; i++) {
          System.out.println("==> " + i);
        }
      }
    }.start();

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    } // 그 다음의 for문을 실행한다. 

  }
}



// 자바는 main() 메서드를 실행하는 한 개의 "실행 흐름"이 있다.
// 실행 흐름에 따라 순서대로 코드가 실행된다.


