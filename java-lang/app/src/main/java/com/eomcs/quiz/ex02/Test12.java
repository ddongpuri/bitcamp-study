package com.eomcs.quiz.ex02;

import java.util.Arrays;

// copyright by codefights.com
//
// 배열의 절반을 교환하시오!
//
// 형식:
//   swapArrayHalves(정수 배열)
// 예) 
//   swapArrayHalves(new int[] {1, 3, 2, 1}) ==> [2, 1, 1, 3]
//
//
/*
Swap two halves of a given array.

Example

for [1, 3, 2, 1] output should be [2, 1, 1, 3]

[input] array.integer inputArray

array of integers of even length L
[output] array.integer

array consisting of last L/2 elements of the given inputArray followed by its first L/2 elements
 */
//
// [시간 복잡도]
// - O(n/2) = O(n): n의 배열의 개수이다.
//
public class Test12 {

  public static void main(String[] args) {
    System.out.println(Arrays.compare(
        swapArrayHalves(new int[] {1, 3, 2, 1}), 
        new int[] {2, 1, 1, 3}) == 0);
    System.out.println(Arrays.compare(
        swapArrayHalves(new int[] {1, 5, 3, 2, 8}), 
        new int[] {2, 8, 3, 1, 5}) == 0);
  }

  static int[] swapArrayHalves(int[] inputArray) {
    int temp = 0;
    int half = (inputArray.length / 2);

    if (inputArray.length % 2 == 0) {
      for (int i = 0; i < half; i++) {
        temp = inputArray[i];
        inputArray[i] = inputArray[half + i];
        inputArray[half + i] = temp;
      }
    } else { 
      for (int i = 0; i < half; i++) {
        temp = inputArray[i];
        inputArray[i] = inputArray[half + i + 1];
        inputArray[half + i + 1] = temp;
      }
    }
    return inputArray;
  }
}
