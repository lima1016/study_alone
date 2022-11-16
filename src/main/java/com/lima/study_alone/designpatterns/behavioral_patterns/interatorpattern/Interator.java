package com.lima.study_alone.designpatterns.behavioral_patterns.interatorpattern;

import java.util.Iterator;

/**
 * 이터레이터 (Interator) 패턴
 * 집합 객체 내부 구조를 노출시키지 않고 순회 하는 방법을 제공하는 패턴.
 * • 집합 객체를 순회하는 클라이언트 코드를 변경하지 않고 다양한 순회 방법을 제공할 수 있다
 * • 장점
 * • 집합 객체가 가지고 있는 객체들에 손쉽게 접근할 수 있다.
 * • 일관된 인터페이스를 사용해 여러 형태의 집합 구조를 순회할 수 있다.
 * • 단점
 * • 클래스가 늘어나고 복잡도가 증가한다.
 */
public class Interator {
  public static void main(String[] args) {
    Board board = new Board();
    board.addPost("테스트");
    board.addPost("테스트");
    board.addPost("테스트");

    Iterator<String> iterator = board.getPost().iterator();
    Iterator<String> iterator2 = board.getDefaultIterator();

    board.getPost().iterator().forEachRemaining(System.out::println);

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}
