package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 복습
 * 직접 말하며 설명하고 코딩해 보세요.
 * • 자바에서 enum을 사용하지 않고 싱글톤 패턴을 구현하는 방법은?
 * • private 생성자와 static 메소드를 사용하는 방법의 단점은?
 * • enum을 사용해 싱글톤 패턴을 구현하는 방법의 장점과 단점은?
 * • static inner 클래스를 사용해 싱글톤 패턴을 구현하라.
 */
public class SingletonTest {
  // [1] 자바에서 enum을 사용하지 않고 싱글톤 패턴을 구현하는 방법은?
  // 첫번째 방법
  private static SingletonTest instance01;

  private SingletonTest() {
  }

  public static SingletonTest getInstance01() {
    if (instance01 == null) {
      instance01 = new SingletonTest();
    }
    return instance01;
  }

  // 두번째 방법
  private static SingletonTest instance02;

  public static synchronized SingletonTest getInstance02() {
    if (instance02 == null) {
      instance02 = new SingletonTest();
    }
    return instance02;
  }

  // 세번째 방법
  private static final SingletonTest INSTANCE01 = new SingletonTest();

  public static SingletonTest getInstance03() {
    return INSTANCE01;
  }

  // 네번째 방법
  private static volatile SingletonTest instance;

  public static SingletonTest getInstance04() {
    if (instance == null) {
      synchronized (SingletonTest.class) {
        if (instance == null) {
          instance = new SingletonTest();
        }
      }
    }
    return instance;
  }

  // 다섯번째 방법
  private static class Singleton {
    private static final SingletonTest INSTANCE02 = new SingletonTest();
  }

  public static SingletonTest getInstance05() {
    return Singleton.INSTANCE02;
  }

  // [2] private 생성자와 static 메소드를 사용하는 방법의 단점은?
  // -
  // [3] enum을 사용해 싱글톤 패턴을 구현하는 방법의 장점과 단점은?
  // - 장점:
  // - 단점
  // = enum 을 통해서 싱글톤 패턴을 구현할 시 미리 인스턴스를 선언해야 한다.
  // = 상속을 사용할 수 없다.

  // [4] static inner 클래스를 사용해 싱글톤 패턴을 구현하라.

  private static class StaticInnerClass {
    private static final SingletonTest INSTANCE03 = new SingletonTest();
  }

  public static SingletonTest getInstance06() {
    return StaticInnerClass.INSTANCE03;
  }
}
