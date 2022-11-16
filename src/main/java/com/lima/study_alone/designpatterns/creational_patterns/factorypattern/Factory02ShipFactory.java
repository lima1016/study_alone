package com.lima.study_alone.designpatterns.creational_patterns.factorypattern;

public interface Factory02ShipFactory {

  default Factory02Ship orderShip(String name, String email) {
    validate(name, email);
    prepareFor(name);
    Factory02Ship ship = createShip();
    // notify
    sendEmailTo(email, ship);
    return ship;
  }

  // 반듯이 하위클래스가 정리해줘야하는 메소드로 정의.
  Factory02Ship createShip();

  private void validate(String name, String email) {
    // validate
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("배이름을 지어주세요");
    }
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("연락처를 남겨주세요");
    }
  }

  private void prepareFor(String name) {
    System.out.println(name + "만들 준비 중");
  }

  private void sendEmailTo(String email, Factory02Ship ship) {
    System.out.println(ship + "다 만들었습니다. " + email + "로 알림을 보냈습니다.");
  }

}
