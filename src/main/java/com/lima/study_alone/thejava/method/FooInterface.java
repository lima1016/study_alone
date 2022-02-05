package com.lima.study_alone.thejava.method;
/*
기본 메소드 (Default Methods)
    ● 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
    ● 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
    ● 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
        ○ 컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
        ○ 반드시 문서화 할 것. (@implSpec 자바독 태그 사용)
    ● Object 가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다.
        ○ 구현체가 재정의해야 한다.
    ● 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 제공할 수 있다.
 */
public interface FooInterface {

  // **첫번째 예제**
  // 기존 메소드
  void printName();
  // 추가된 메소드
  // 기존 FooInterface 를 임플했던 클래스는 모두 이 추상 메소드를 선언해줘야한다.
//  void printNameUpperCase();
  // 그래서 default 키워들 사용해서 이러한 상황을 최소화해줄 수 있음.\

  /**
   * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
   */
  default void printNameUpperCase() {
    System.out.println(getName().toUpperCase());
  }

  // **세번째 예제**
  // 스태틱 메소드
  //    ● 해당 타입 관련 헬터 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있다.
  static void printAnything() {
    System.out.println("FooInterface");
  }

  String getName();
}
