package com.lima.study_alone.designpatterns.singleton;

/**
 * https://happy-coding-day.tistory.com/entry/%EC%8B%B1%EA%B8%80%ED%86%A4Singleton-%ED%8C%A8%ED%84%B4-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0
 * https://crazy-horse.tistory.com/119
 * 싱글톤(Singleton)
 * 인스턴스를 오직 한개만 제공하는 클래스
 * 시스템 런타임, 환경 세팅에 대한 정보 등, 인스턴스가 여러개 일 때 문제가 생길 수 있는 경우가 있다.
 * 인스턴스를 오직 한개만 만들어 제공하는 클래스가 필요하다.
 * instance: Singleton
 * getInstance(): Singleton
 *
 * 1. 생성자를 private으로 만든 이유?
 * -> 오직 한개의 인스턴스만 접근하기 위해 생성자의 노출을 막음.
 * 2. getInstance() 메소드를 static으로 선언한 이유?
 * -> 처음 만들어진 이후엔 null이 아니기 때문에 하나의 instance만 리턴함
 * -> 글로벌하게 접근할 수 있도록 만들기 위해 static으로 선언하고, 이는 JVM에 클래스 영역에 생성되어 글로벌하게 사용할 수 있음.
 * 3. getInstance()가 멀티쓰레드 환경에서 안전하지 않은 이유?
 * -> 두개의 스레드가 있을때 첫번째 스레드 접근시 null 이기때문에 new가 실행됨,
 * 동시에 두번째 스레드가 접근할 때 첫번째 스레드가 아직 if문 안에 있다면 두번째 스레드도 new를 실행하게됨 그래서 두개의 인스턴스가 생성됨.
 * -> 멀티 스레드 환경에서는 오직 한 개의 인스턴스가 아니게 된다.
 * 새롭게 생성된 인스턴스에서도  instance가 null인지 여부를 판단하게 되는데,
 * 이때 새로운 스레드에는 생성된 instance 가 없기 때문에 한 개의 인스턴스를 보장할 수 없다.
 */
public class Singleton_01 {

  private static Singleton_01 instance;
  private Singleton_01() {
  }

  public static Singleton_01 getInstance() {
    if (instance == null) {
      instance = new Singleton_01();
    }
    return instance;
  }
}
