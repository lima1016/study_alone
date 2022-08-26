package com.lima.study_alone.designpatterns.singleton;

/**
 *싱글톤 (Singleton) 패턴 구현 방법 2
 * 동기화(synchronized)를 사용해 멀티쓰레드 환경에 안전하게 만드는 방법
 * synchronized - 메소드에 한번에 한 스레드만 들어오게 만들기
 * 단점:
 * - getInstance 호출할때마다 동기화 처리 작업때문에 성능에 문제가 있을수있다.
 * - 키(lock)를 갖고있는 스레드만 접근할 수 있게 해주기때문에
 * 1. 자바의 동기화 블럭 처리 방법은?
 * 2. getInstance() 메소드 동기화시 사용하는 락(lock)은 인스턴스의 락인가 클래스의 락인가? 그 이유는?
 */
public class Singleton_02 {

  private static Singleton_02 instance;
  private Singleton_02() {
  }

  public static synchronized Singleton_02 getInstance() {
    if (instance == null) {
      instance = new Singleton_02();
    }
    return instance;
  }
}
