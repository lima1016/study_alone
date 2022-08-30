package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 3
 * 이른 초기화 (eager initialization)을 사용하는 방법
 * 1. 이른 초기화가 단점이 될 수도 있는 이유?
 * - 인스턴스를 사용하지 않더라도 생성을 하기때문에 효율성이 떨어지는 문제가 있다.
 * - 클래스가 메모리에 할당되는 시점에 생성되므로
 * 2. 만약에 생성자에서 checked 예외를 던진다면 이 코드를 어떻게 변경해야 할까요?
 * - try-catch를 사용하여 unchecked 예외로 변환시켜야힌디.
 * - 생성자에서 예외를 던진다면, 그안에서 try-catch 으로 예외 핸들링을 해야한다. 그렇지 않다면 이른 초기화를 사용할 수 없다.
 */
public class Singleton03 {

  // 이른 초기화(eager initialization) 사용
  // thead safe함 - 여러 스레드가 들어오더라도 미리 만들어 놓은 INSTANCE를 리턴해주기 때문에
  // 안쓰는데도 미리 만들어놓기 때문에 성능에 문제가 있을 수 있다.
  private static final Singleton03 INSTANCE = new Singleton03();
  private Singleton03() {
  }

  public static Singleton03 getInstance() {

    return INSTANCE;
  }
}
