package com.eomcs.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer stk;

    stk = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(stk.nextToken()); // 다리를 건너는 트럭의 수 
    int w = Integer.parseInt(stk.nextToken()); // 다리의 길이
    int L = Integer.parseInt(stk.nextToken()); // 다리의 최대하중 
    int minTime = 0;

    Queue<Integer> trucks = new LinkedList<>();
    Queue<Integer> trucksOnBr = new LinkedList<>();

    stk = new StringTokenizer(br.readLine());

    for (int i = 0; i < n; i++) {
      trucks.add(Integer.parseInt(stk.nextToken()));
    }

    while (!trucks.isEmpty()) {

      if (trucksOnBr.isEmpty()) { // 다리에 올라와 있는 트럭이 현재 없으면 

        trucksOnBr.add(trucks.poll());
        minTime += 1;
      }

      if (trucksOnBr.size() == 1 && trucks)



    }

  }
}























