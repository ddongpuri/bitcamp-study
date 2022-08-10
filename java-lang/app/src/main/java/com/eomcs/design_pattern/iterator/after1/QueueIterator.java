package com.eomcs.design_pattern.iterator.after1;

// LinkedList에서 데이터를 꺼내줄 객체 
public class QueueIterator<E> implements Iterator<E> {

  Queue<E> list;

  public QueueIterator(Queue<E> list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    return !list.empty();
  }

  @Override
  public E next() {
    return list.poll();  
  }


}
