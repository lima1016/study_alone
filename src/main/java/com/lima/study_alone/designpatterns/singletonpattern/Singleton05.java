package com.lima.study_alone.designpatterns.singletonpattern;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 5
 * static inner 클래스를 사용하는 방법
 * 지연 초기화 (lazy initialization)린??
 * - 필드의 초기화 시점을 그 값이 처음 필요할 때까지 늦추는 기법.
 * 1. 이 방법은 static final를 썼는데도 왜 지연 초기화 (lazy initialization)라고 볼 수 있는가?
 * - static 필드는 클래스가 처음 로딩될 때 정적인 메모리 공간에 만들어지는데, holder가 가지고있는 클래스가 로딩되는 시점은 getInstance()를 호출할 때
 * 호딩되기 때문에 lazy-initialization 이라 한다.
 */
public class Singleton05 {

  // 멀티 스레드 환경에서도 안전하고, getInstance가 호출 될때 인스턴스가 만들어지고 lazy 로딩도 가능한 코드가 됨.
  private static class SingletonHolder {
    private static final Singleton05 SINGLETON_05 = new Singleton05();
  }

  public static Singleton05 getInstance() {
    return SingletonHolder.SINGLETON_05;
  }
}
