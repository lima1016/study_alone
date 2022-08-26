package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 4
 * double checked locking으로 효율적인 동기화 블럭 만들기
 * 1. double check locking이라고 부르는 이유?
 * 2. instacne 변수는 어떻게 정의해야 하는가?
 * 그 이유는?
 */
public class Singleton_04 {

  // volatile을 써줘야만 자바 1.5 이상부터 동작하는 double check  locking이 동작함
  private static volatile Singleton_04 instance;
  private Singleton_04() {
  }

  // double check locking
  // 인스턴스를 필요로하는 시점에 만들 수 있다.
  // 메소드에 synchronized를 쓰는것 보다 성능이 나음.
  public static Singleton_04 getInstance() {
    // 첫번째 체크
    if (instance == null) {
      // Singleton_04.class 를 lock으로 쓰게 해줌
      synchronized (Singleton_04.class) {
        // 두번째 체크
        if (instance == null) {
          instance = new Singleton_04();
        }
      }
    }
    return instance;
  }
}
