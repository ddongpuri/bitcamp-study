package com.eomcs.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  protected class Meeting {
    int start;
    int finish;

    public Meeting(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer stk;

    int N = Integer.parseInt(br.readLine());

    System.out.println(N);

  }

}









