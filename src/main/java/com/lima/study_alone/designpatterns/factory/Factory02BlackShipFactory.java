package com.lima.study_alone.designpatterns.factory;

public class Factory02BlackShipFactory implements Factory02ShipFactory{


  @Override
  public Factory02Ship createShip() {
    return new Factory02BlackShip();
  }
}
