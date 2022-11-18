package com.lima.study_alone.designpatterns.creational_patterns.factorypattern;

/**
 * 팩토리 메소드 (Factory method) 패턴
 * 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
 * 다양한 구현체 (Product)가 있고, 그중에서 특정한 구현체를 만들 수 있는 다양한 팩토리 (Creator)를 제공할 수 있다
 * 확장에 열려있고 변경에 닫혀있는 구조로 만들어보자
 * • 단순한 팩토리 패턴
 * • 매개변수의 값에 따라 또는 메소드에 따라 각기 다른 인스턴스를 리턴하는 단순한 버전
 * 의 팩토리 패턴
 * • java.lang.Calendar 또는 java.lang.NumberFormat
 * • 스프링 BeanFactory
 * • Object 타입의 Product를 만드는 BeanFacotry라는 Creator!
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
