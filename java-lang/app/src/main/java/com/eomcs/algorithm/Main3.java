package com.eomcs.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;

    stk = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(stk.nextToken());

    int[] arr = new int[N];

    for (int i = 0; i < N-1; i++) {
      arr[i] = Integer.parseInt(stk.nextToken());
      System.out.println(arr[i]);
    }

  }

}





