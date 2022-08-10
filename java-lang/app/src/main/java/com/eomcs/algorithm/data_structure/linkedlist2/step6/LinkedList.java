package com.eomcs.algorithm.data_structure.linkedlist2.step6;

public class LinkedList {
  Node head;
  Node tail;
  int size;

  public void add(Object value) {
    Node node = new Node(value);

    if (head == null) { // 존재하는 노드가 없다
      tail = head = node; // 추가되는 노드가 head이자 tail
    } else {            // 기존재하는 노드가 있다면
      node.prev = tail; // 지금 추가되는 노드의 prev에 현재 tail의 주소를 저장
      tail.next = node; // 현재 tail의 next에, 추가되는 노드의 주소를 저장
      tail = node; // 지금 추가된 노드의 주소를 tail에 저장
    }

    size++; // 사이즈하나 업
  }

  public void add(int index, Object value) { // 특정 위치에 노드 삽입하기
    Node node = getNode(index);

    Node newNode = new Node(value);

    if (node.prev != null) {
      node.prev.next = newNode; // 앞 노드 뒤의 노드를 새 노드로 설정
    }
    newNode.prev = node.prev; // 새 노드의 앞 노드 설정

    node.prev = newNode; // 현재 노드의 앞 노드를 새 노드로 설정
    newNode.next = node; // 새 노드의 다음 노드를 현재 노드로 설정. 

    if (node == head) { // 첫 번째 노드라면
      head = newNode; // 새 노드를 첫 번째 노드로 만든다.
    }

    size++;
  }

  public int size() { // private인 size값을 반환해주는  size() 메서
    return size; 
  }

  public Object get(int index) {
    Node node = getNode(index);
    return node.value;
  }

  public Object remove(int index) {
    Node node = getNode(index);

    if (size == 1) {
      head = tail = null;
    } else if (node == head) {
      head = node.next;
    } else if (node == tail) {
      tail = node.prev;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    // 삭제된 노드는 다른 노드나 객체를 참조하지 않도록 초기화시킨다.
    // => 삭제된 노드끼리 참조하는 경우 가비지가 되지 않는 문제가 발생한다.
    // => 삭제된 노드가 값 객체의 주소를 갖고 있으면 값 객체가 가비지가 되지 않는 문제가 발생한다.
    // 
    node.prev = null;
    node.next = null;
    Object deleted = node.value;
    node.value = null;
    size--;
    return deleted; // 삭제되기 전의 값 리턴
  }

  public Object set(int index, Object value) {
    Node node = getNode(index);
    Object old = node.value;
    node.value = value;
    return old; // 변경되기 전의 값 리턴
  }

  private Node getNode(int index) {
    if (index < 0 || index >= size) { // 넘겨받은 인덱스 값이 유효범위를 벗어나면 
      throw new IndexOutOfBoundsException(); // RuntimeException계열의 예외를 던진다.
    }

    Node node = head; //첫번째 노드부터 시작해서 
    int count = 0;
    while (count < index) { // 지정한 인덱스까지 도달하게 for문 돌려 
      node = node.next;
      count++;
    }

    return node;
  }

  public Iterator iterator() {
    // Anonymous Class 활용 예 2
    // => 오직 한 개의 인스턴스만 생성할 경우
    // => return 문, 할당문, 파라미터 전달하는 놓기 
    //
    return new Iterator() {
      int cursor;
      @Override
      public boolean hasNext() {
        return cursor < LinkedList.this.size();
      }
      @Override
      public Object next() {
        return LinkedList.this.get(cursor++);
      }
    };
  }

  // Static Nested Class 활용 예
  // => 특정 클래스 안에서만 사용되는 클래스일 때
  // => 바깥 클래스의 인스턴스 멤버를 사용하지 않을 때
  //
  private static class Node {
    Node prev; // 이전 노드의 주소를 저장하는 참조변수
    Object value; // 어떤 값의 주소를 저장하는 참조변수
    Node next; // 다음 노드의 주소를 저장하는 참조변수 

    public Node(Object value) {
      this.value = value; // 값을 받아서 값의 주소를 value에 저장
    }
  }
}








