package com.lima.study_alone.designpatterns.singleton;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 6
 * enum을 사용하는 방법
 * 1. enum 타입의 인스턴스를 리팩토링을 만들 수 있는가?
 * 2. enum으로 싱글톤 타입을 구현할 때의 단점은?
 * 3. 직렬화 & 역직렬화 시에 별도로 구현해야 하는 메소드가 있는가?
 */
public enum Singleton_07 {
  // enum은 미리 인스턴스가 만들어짐
  INSTANCE;
}
