package com.lima.study_alone.designpatterns.creational_patterns.factorypattern;

public class Factory02BlackShipFactory implements Factory02ShipFactory{


  @Override
  public Factory02Ship createShip() {
    return new Factory02BlackShip();
  }
}
