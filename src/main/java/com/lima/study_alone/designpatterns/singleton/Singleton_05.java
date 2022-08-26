package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 5
 * static inner 클래스를 사용하는 방법
 * 1. 이 방법은 static final를 썼는데도 왜 지연 초기화 (lazy initialization)라고 볼 수 있는가?
 */
public class Singleton_05 {

  // 멀티 스레드 환경에서도 안전하고, getInstance가 호출 될때 인스턴스가 만들어지고 lazy 로딩도 가능한 코드가 됨.
  private static class SingletonHolder {
    private static final Singleton_05 SINGLETON_05 = new Singleton_05();
  }

  public static Singleton_05 getInstance() {
    return SingletonHolder.SINGLETON_05;
  }
}
