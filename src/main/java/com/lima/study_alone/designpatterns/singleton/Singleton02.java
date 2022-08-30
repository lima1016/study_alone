package com.lima.study_alone.designpatterns.singleton;

/**
 *싱글톤 (Singleton) 패턴 구현 방법 2
 * 동기화(synchronized)를 사용해 멀티쓰레드 환경에 안전하게 만드는 방법
 * synchronized - 메소드에 한번에 한 스레드만 들어오게 만들기
 * 단점:
 * - getInstance 호출할때마다 동기화 처리 작업때문에 성능에 문제가 있을수있다.
 * - 키(lock)를 갖고있는 스레드만 접근할 수 있게 해주기때문에
 * 1. 자바의 동기화 블럭 처리 방법은?
 * - 하지만 이 방법은 thread-safe 함을 유지하려는 목적에 비해 동기화에 대한 오버헤드가 심하다.
 * - 멀티스레드에서 메서드 호출마다 lock을 걸게 되기 때문에 성능 저하 발생.
 * 2. getInstance() 메소드 동기화시 사용하는 락(lock)은 인스턴스의 락인가 클래스의 락인가? 그 이유는?
 * - static 메서드에 synchronized 블록이 있다면 class 단위로 lock을 거는 것.
 * - 클래스의 락, 만약 락이 인스턴스의 락이라면, 동기화시 하나의 객체를 보장할 수 없게 되기 때문이다.
 */
public class Singleton02 {

  private static Singleton02 instance;
  private Singleton02() {
  }

  public static synchronized Singleton02 getInstance() {
    if (instance == null) {
      instance = new Singleton02();
    }
    return instance;
  }
}
