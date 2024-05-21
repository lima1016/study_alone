package com.lima.study_alone.inflearn.designpatterns.creational_patterns.singletonpattern;

/**
 * 싱글톤 (Singleton) 패턴 구현 방법 6
 * enum을 사용하는 방법
 * 1. enum 타입의 인스턴스를 리플렉션을 통해 만들 수 있는가?
 * - 없다. enum 타입의 클래스는 리플랙션을 통해 만들 수 없도록 제한한다.
 * 2. enum으로 싱글톤 타입을 구현할 때의 단점은?
 * - 이른 초기화와 같이 미리 만들어진다. 그리고 상속을 사용할 수 없다.
 * 3. 직렬화 & 역직렬화 시에 별도로 구현해야 하는 메소드가 있는가?
 * - 별다른 장치가 없어도 Enum 클래스는 직렬화 & 역직렬화가 된다. 다만, getResolves()을 구현하면 역직렬화시 변경할 수 없다.
 * - enum은 기본적으로 enum이라는 클래스를 상속받고 있고 이클래스는 이미 Serializeble을 상속하고 있기 때문에 enum도 별다른 안전장치를 마련하지 않아도
 * 한전한 직렬화, 역직렬화가 가능하다.
 */
public enum Singleton07 {
  // enum은 미리 인스턴스가 만들어짐
  INSTANCE;
}
