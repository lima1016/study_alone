package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 3
 * 이른 초기화 (eager initialization)을 사용하는 방법
 * 1. 이른 초기화가 단점이 될 수도 있는 이유?
 * 2. 만약에 생성자에서 checked 예외를 던진다면 이 코드를 어떻게 변경해야 할까요?
 */
public class Singleton_03 {

  // 이른 초기화(eager initialization) 사용
  // thead safe함 - 여러 스레드가 들어오더라도 미리 만들어 놓은 INSTANCE를 리턴해주기 때문에
  // 안쓰는데도 미리 만들어놓기 때문에 성능에 문제가 있을 수 있다.
  private static final Singleton_03 INSTANCE = new Singleton_03();
  private Singleton_03() {
  }

  public static Singleton_03 getInstance() {

    return INSTANCE;
  }
}
