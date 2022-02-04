package com.lima.study_alone.thejava.day1;

// 추상 메소드가 몇개있어??? => 1개면 함수형 인터페이스 (functionalInterface)
// 추상 메소드가 딱 하나만 있으면 함수형 인터페이스다.
// 다른 형태의 메소드가 있어도 추상 메소드가 한개만있다면 함수형 인터페이스다.
@FunctionalInterface
public interface RunSomething {
  // **첫번째 예제**
  // 추상 메소드
  // abstract 생략 가능
//  void doIt();

  // **두번째 예제 **
  int doIt(int num);

  // 두번째 추상 메소드 annotation 에서 에러뜸
  // void doIt2();

  // 인터페이스임에도 static method 를 정의할 수 있다.
  static void printName() {
    System.out.println("Lima");
  }

  default void printAge() {
    System.out.println("1");
  }
}
