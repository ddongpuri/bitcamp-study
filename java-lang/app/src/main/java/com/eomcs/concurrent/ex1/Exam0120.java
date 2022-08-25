// 멀티 스레드 적용 후
package com.eomcs.concurrent.ex1;

public class Exam0120 {

  // CPU의 시간을 쪼개서 왔다갔다 하면서
  // 동시에 실행하고픈 코드가 있다면,
  // 다음과 같이 Thread를 상속 받아
  // run() 메서드에 그 코드를 두어라!
  //
  static class MyThread extends Thread {
    @Override
    public void run() {
      // 기존 실행 흐름과 분리하여 따로 실행시킬 코드를
      // 이 메서드에 둔다.
      for (int i = 0; i < 1000; i++) {
        //        System.out.println("==> " + i);
        System.out.println("OOO MAIN");
      }
    }
  }

  public static void main(String[] args) {
    // => 동시에 실행할 코드를 담고 있는 Thread 객체를 생성한다.
    // => 그리고 현재 실행과 분리하여 작업을 시작시킨다.
    // => JVM은 이 스레드에 들어 있는 코드와 다음에 진행하는 코드를
    // 왔다갔다 하면서 처리할 것이다.
    new MyThread().start();

    for (int i = 0; i < 1000; i++) {
      System.out.println("XXX THREAD");
      //      System.out.println(">>> " + i);
    }
  }

}
//
// main() 메서드를 실행하는 기본 실행 흐름에서 새로운 실행 흐름으로 분기하고 싶다면,
// Thread 클래스를 정의할 때 분기해서 실행할 코드를 담으면 된다.
// 그러면 두 개의 실행 흐름이 서로 왔다 갔다 하면서 실행된다.
//
// ## 멀티태스킹(multi-tasking)
// - 한 개의 CPU가 여러 코드를 동시(?)에 실행하는 것.
// - 실제는 일정한 시간을 쪼개 이 코드와 저 코드를 왔다갔다 하면서 실행한다.
// - 그럼에도 불구하고 외부에서 봤을 때는 명령어가 동시에 실행되는 것 처럼 보인다.
// - 왜? CPU 속도 워낙 빠르기 때문이다.
//
// ## CPU의 실행 시간을 쪼개서 배분하는 정책 : CPU Scheculing 또는 프로세스 스케줄링
// - CPU의 실행 시간을 쪼개 코드를 실행하는 방법이다.
// 1) Round-Robin 방식
// - Windows OS에서 사용하는 방식
// - CPU 실행 시간을 일정하게 쪼개서 각 프로세스에 분배하는 방식
// 2) Priority 방식
// - Unix, Linux 에서 사용하는 방식
// - 우선 순위가 높은 프로세스에 더 많은 실행 시간을 배정하는 방식
// - 문제점:
//   - 우선 순위가 낮은 프로그램인 경우 CPU 시간을 배정 받지 못하는 문제가 발생했다.
//   - 그래서 몇 년이 지나도록 실행되지 않는 경우가 나타났다.
// - 해결책?
//   - CPU 시간을 배정 받지 못할 때 마다 
//     즉 다른 프로세스에 밀릴 대 마다 우선 순위를 높여서
//     언젠가는 실행되게 만들었다.
//   - 이런 방식을 "에이징(aging) 기법"이라 부른다.
//
// ## 멀티 태스킹을 구현하는 방법
// 1) 멀티 프로세싱
// - 프로세스(실행 중인 프로그램)를 복제하여 분기한다.
// - 그리고 분기된 프로세스를 실행시켜서 작업을 동시에 진행하게 한다.
// - 장점:
//   - 분기하기가 쉽다. fork() 호출.
//   - 즉 구현(프로그래밍)하기가 쉽다.
// - 단점:
//   - 프로세스를 그대로 복제하기 때문에 
//     프로세스가 사용하는 메모리도 그대로 복제된다.
//   - 메모리 낭비가 심하다.
//   - 복제된 프로세스는 독립적이기 때문에 
//     실행 종료할 때도 일일이 종료해야 한다.
//
// 2) 멀티 스레딩
// - 특정 코드만 분리하여 실행한다.
// - 따라서 프로세스가 사용하는 메모리를 공유한다.
// - 장점:
//   - 프로세스의 힙 메모리를 공유하기 때문에 메모리 낭비가 적다.
//   - 모든 스레드는 프로세스에 종속되기 때문에 프로세스를 종료하면
//     스레드도 자동 종료된다.
// - 단점:
//   - 프로세스 복제 방식에 비해 코드 구현이 복잡하다.
//
// ## 컨텍스트 스위칭(context switching)
// - CPU의 실행 시간을 쪼개 이 코드 저 코드를 실행할 때 마다
//   실행 위치 및 정보(context)를 저장하고 로딩하는 과정이 필요하다.
// - 이 과정을 '컨텍스트 스위칭'이라 부른다.
//
// ## 스레드(thread)
// - '실'이라는 뜻을 갖고 있다.
// - 한 실행 흐름을 가리킨다.
// - 하나의 실은 끊기지 않은 하나의 실행 흐름을 의미한다.
//
// ## 스레드 생성
// - 새 실을 만든다는 것이다.
// - 즉 새 실행 흐름을 시작하겠다는 의미다.
// - CPU는 스레드를 프로세스와 마찬가지로 동일한 자격을 부여하여
//   스케줄링에 참여시킨다.
// - 즉 프로세스에 종속된 스레드라고 취급하여
//   한 프로세스에 부여된 실행 시간을 다시 쪼개 스레드에 나눠주는 방식이 아니다.
// - 그냥 단독적인 프로세스처럼 동일한 실행 시간을 부여한다.
//


