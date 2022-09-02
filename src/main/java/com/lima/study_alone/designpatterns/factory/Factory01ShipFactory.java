package com.lima.study_alone.designpatterns.factory;

public class Factory01ShipFactory {
  public static Factory01Ship orderShip(String name, String email) {
    // validate
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("배이름을 지어주세요");
    }
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("연락처를 남겨주세요");
    }
    prepareFor(name);

    Factory01Ship ship = new Factory01Ship();
    ship.setName(name);

    // Customizing for specific name
    if (name.equalsIgnoreCase("yellowship")) {
      ship.setLogo("\uD83D\uDEE5");
    } else if (name.equalsIgnoreCase("blackship")) {
      ship.setLogo("\uD83D\uDEE5");
    }

    // coloring
    if (name.equalsIgnoreCase("yellowship")) {
      ship.setColor("yellow");
    } else if (name.equalsIgnoreCase("blackship")) {
      ship.setColor("black");
    }

    // notify
    sendEmailTo(email, ship);
    return ship;
  }

  private static void sendEmailTo(String email, Factory01Ship ship) {
    System.out.println(ship + "다 만들었습니다. " + email + "로 알림을 보냈습니다.");
  }

  private static void prepareFor(String name) {
    System.out.println(name + "만들 준비 중");
  }
}
