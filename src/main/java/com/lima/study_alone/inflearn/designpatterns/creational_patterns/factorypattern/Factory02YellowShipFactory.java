package com.lima.study_alone.inflearn.designpatterns.creational_patterns.factorypattern;

public class Factory02YellowShipFactory implements Factory02ShipFactory{


  @Override
  public Factory02Ship createShip() {
    return new Factory02YellowShip();
  }
}
