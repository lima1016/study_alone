package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 4
 * double checked locking으로 효율적인 동기화 블럭 만들기
 * 1. double check locking이라고 부르는 이유?
 * - 2번에 걸쳐서 단 하나의 객체임을 체크하기때문이다. 첫번째는 instance 가 null일 경우, 동기화 클래스 synchronized 에서 한번 더 체크함.
 * - 첫번째 체크를 통과한 쓰레드들만 동기화 블럭에 적용된다.
 * 2. instance 변수는 어떻게 정의해야 하는가? 그 이유는?
 * - 변수는 volatile 키워드로 선언하여 여러 스레드가 동시에 접근하게 되는 경우 메모리 장벽 특성으로 인한 싱글톤 패턴에 대해서
 * - double checked locking(DCL) 패턴을 사용할 수 있다.
 * - 인스턴스를 메인 메모리에 저장하고 읽기 때문에 값 불일치 문제를 해결할 수 있다.
 * - 접근 성능은 느리지만, 변수의 접근(read, write)에 대한 정합성을 보장한다.
 */
public class Singleton04 {

  // volatile을 써줘야만 자바 1.5 이상부터 동작하는 double check  locking이 동작함
  private static volatile Singleton04 instance;
  private Singleton04() {
  }

  // double check locking
  // 인스턴스를 필요로하는 시점에 만들 수 있다.
  // 메소드에 synchronized를 쓰는것 보다 성능이 나음.
  public static Singleton04 getInstance() {
    // 첫번째 체크
    if (instance == null) {
      // Singleton_04.class 를 lock으로 쓰게 해줌
      synchronized (Singleton04.class) {
        // 두번째 체크
        if (instance == null) {
          instance = new Singleton04();
        }
      }
    }
    return instance;
  }
}
