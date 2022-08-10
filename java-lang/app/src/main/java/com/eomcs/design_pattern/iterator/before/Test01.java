package com.eomcs.design_pattern.iterator.before;

import java.util.HashSet;

public class Test01 { // Iterator 도입 전. 어떤 부분이 불편한건지, iterator의 필요성을 느껴보자 

  public static void main(String[] args) {
    ArrayList<String> list1 = new ArrayList<>();
    list1.add("aaa");
    list1.add("bbb");
    list1.add("ccc");
    list1.add("ddd");

    LinkedList<String> list2 = new LinkedList<>();
    list2.add("aaa2");
    list2.add("bbb2");
    list2.add("ccc2");
    list2.add("ddd2");

    Stack<String> list3 = new Stack<>();
    list3.push("aaa3");
    list3.push("bbb3");
    list3.push("ccc3");
    list3.push("ddd3");

    Queue<String> list4 = new Queue<>();
    list4.offer("aaa4");
    list4.offer("bbb4");
    list4.offer("ccc4");
    list4.offer("ddd4");

    HashSet<String> list5 = new HashSet<String>();
    list5.add("aaa5");
    list5.add("bbb5");
    list5.add("ccc5");
    list5.add("ddd5");


    // 목록에서 값 꺼내기
    // 1) ArrayList 에서 값 꺼내기
    System.out.println("[ ArrayList ]");
    for (int i = 0; i < list1.size(); i++) {
      System.out.println(list1.get(i));
    }
    System.out.println("---------------------------");

    // 2) LinkedList 에서 값 꺼내
    System.out.println("[ LinkedList ]");
    for (int i = 0; i < list2.size(); i++) {
      System.out.println(list2.get(i));
    }
    System.out.println("---------------------------");

    // 3) Stack 에서 값 꺼내기
    System.out.println("[ Stack ]");
    while (!list3.empty()) {
      System.out.println(list3.pop());
    }
    System.out.println("---------------------------");

    // 4) Queue 에서 값 꺼내기 
    System.out.println("[ Queue ]");
    while(!list4.empty()) {
      System.out.println(list4.poll());
    }
    System.out.println("---------------------------");

    // 4) HashSet 에서 값 꺼내기 
    System.out.println("[ HashSet ]");
    String[] arr = list5.toArray(new String[0]);
    for (String s : arr) {
      System.out.println(s);
    }


    // 결론!
    // - 자료구조에 따라 데이터를 꺼내는 방식이 다르다!
    // - 데이터 조회에 일관성이 없다. 
    //
    // 해결책 
    // - 데이터 조회하는 일을 별도의 객체에 맡기자! 
    // - 단, 데이터 조회 방식을 통일하기 위해 인터페이스로 조회방식을 규격화한다.  
  }



}









