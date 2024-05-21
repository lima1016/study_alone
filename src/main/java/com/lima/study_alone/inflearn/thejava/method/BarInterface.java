package com.lima.study_alone.inflearn.thejava.method;

// BarInterface 에서는 FooInterface 에서 제공하는 기본구현체
// (printNameUpperCase) 들을 제공하고 싶어하지 않는 상태
//  ● 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
public interface BarInterface extends FooInterface{
  // FooInterface 에서 구현했던 메소드를 추상 메소드로 선언해주면 됨.
  // 근데 이렇게하면 BarInterface 를 구현하는 클래스들이 다 이 메소드 들을 재정의 해야함...
//  void printNameUpperCase();

  // **두번째 예제**
  // Bar 에서도 FooInterface 에서 제공하는 구현체처럼 똑같은 구현메소드가 있는 상태임.
  default void printNameUpperCase() {
    System.out.println(getName().toUpperCase());
  }
}
