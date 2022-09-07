package com.lima.study_alone.designpatterns.factory;

/**
 * 팩토리 메소드 (Factory method) 패턴
 * 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
 * 다양한 구현체 (Product)가 있고, 그중에서 특정한 구현체를 만들 수 있는 다양한 팩토리 (Creator)를 제공할 수 있다
 * 확장에 열려있고 변경에 닫혀있는 구조로 만들어보자
 */
public class Factory02Client {
  public static void main(String[] args) {
    Factory02Client client = new Factory02Client();
    client.print(new Factory02YellowShipFactory(), "yellowShip", "limlim@mail.com");
    client.print(new Factory02YellowShipFactory(), "blackShip", "hello@mail.com");

//    Factory02Ship pinkShip = new Factory02YellowShipFactory().orderShip("Yellowship", "lim@mail.com");
//    System.out.println(pinkShip);
//
//    Factory02Ship blackShip = new Factory02BlackShipFactory().orderShip("Blackship", "hello@email.com");
//    System.out.println(blackShip);
  }

  // 팩토리 메소드 (Factory method) 패턴 복습
  //구체적으로 어떤 것을 만들지는 서브 클래스가 정한다.
  // - 팩토리 메소드 패턴을 적용했을 때의 장점은? 단점은?
  // 장점 = 기존 코드를 건드리지 않고 새로운 인스턴스를 다른 방법으로 확장 가능하다
  // = product 와 느슨한 결합 (loosely coupling)
  // 단점 = 패턴 적용시 클래스가 늘어나는 단점이 있다.
  // - “확장에 열려있고 변경에 닫혀있는 객체 지향 원칙”을 설명하세요. (ocp)
  // = 변경에 닫혀있다 -> 기존 코드를 변경하지 않고 새롭게 확장 가능
  // - 자바 8에 추가된 default 메소드에 대해 설명하세요.
  // = 인터페이스에서는 추상메소드만 적용할수 있었는데 인터페이스에서 구현할 수 있는 키워드 이다.
  private void print(Factory02ShipFactory shipFactory, String name, String email) {
    System.out.println(shipFactory.orderShip(name, email));
  }
}
