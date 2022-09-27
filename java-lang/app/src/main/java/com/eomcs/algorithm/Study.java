package com.eomcs.algorithm;

import java.util.Scanner;

public class Study {
  public static void main(String[] args) {
    String ID = null;
    String AdminID = "admin";
    String PW, AdminPW = "1234";
    int WrongCount = 0;
    boolean Start = false;
    Scanner sc = new Scanner(System.in);

    Start = true;

    while (Start) {
      System.out.printf("사용자 ID : ");
      ID = sc.nextLine();

      if (ID.isEmpty()|| ID.isBlank()) {
        System.out.println("ID는 필수입력값입니다.");
        continue;
      }

      System.out.printf("사용자 PW : ");
      PW = sc.nextLine();

      if (!ID.equals(AdminID)) {
        System.out.println("ID가 틀렸습니다.");
        WrongCount++;
        if (WrongCount == 3) {
          System.out.printf("오류가 %d번 누적되었습니다. 다음에 이용해주세요.\n", WrongCount);
          break;
        }
        continue;
      }

      if (!PW.equals(AdminPW)) {
        System.out.println("PW가 틀렸습니다.");
        WrongCount++;
        if (WrongCount == 3) {
          System.out.printf("오류가 %d번 누적되었습니다. 다음에 이용해주세요.\n", WrongCount);
          break;
        }
        continue;
      }

      if (ID.equals(AdminID) && PW.equals(AdminPW)) {
        System.out.println("sysout : \"로그인 성공\" \n");
        Start = false;
      }
    }
  }
}























