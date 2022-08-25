// 1) 스레드 사용 전 
package com.eomcs.concurrent;

public class Exam0230 {

  public static void main(String[] args) {

    int count = 1000;

    new Thread(new MyRunnable(count)).start();
    // 최종적으로, MyRunnable의 run()이 실행된다. 

    for (int i = 0; i < count; i++) {
      System.out.println(">>> " + i);
    } 
  }

  static class MyRunnable implements Runnable {

    int count;

    public MyRunnable(int count) {
      this.count = count;
    }

    @Override
    public void run() {
      for (int i = 0; i < count; i++) {
        System.out.println("==> " + i);
      }

    }
  }


}


