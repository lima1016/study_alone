package com.lima.study_alone.designpatterns.factory;

/**
 * 팩토리 메소드 (Factory method) 패턴
 * 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
 * 다양한 구현체 (Product)가 있고, 그중에서 특정한 구현체를 만들 수 있는 다양한 팩토리 (Creator)를 제공할 수 있다
 * 확장에 열려있고 변경에 닫혀있는 구조로 만들어보자
 */
public class Factory01Client {
  public static void main(String[] args) {
    Factory01Client client = new Factory01Client();

    Factory01Ship pinkShip = Factory01ShipFactory.orderShip("Yellowship", "lim@mail.com");
    System.out.println(pinkShip);

    Factory01Ship blackShip = Factory01ShipFactory.orderShip("Blackship", "hello@email.com");
    System.out.println(blackShip);
  }
}
