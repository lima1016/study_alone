package com.lima.study_alone.designpatterns.abstractpattern;

import com.lima.study_alone.designpatterns.factorypattern.Factory02Ship;
import com.lima.study_alone.designpatterns.factorypattern.Factory02ShipFactory;
import com.lima.study_alone.designpatterns.factorypattern.Factory02YellowShip;

/**
 * 추상 팩토리 (Abstract factory) 패턴
 * 서로 관련있는 여러 객체를 만들어주는 인터페이스.
 * • 구체적으로 어떤 클래스의 인스턴스를(concrete product)를 사용하는지 감출 수 있다.
 */
public class Abstract01 implements Factory02ShipFactory {
  @Override
  public Factory02Ship createShip() {
    Factory02Ship ship = new Factory02YellowShip();
    // 제품이 많아지만 new 인스턴스가 계속 바껴야한다 매우 귀찮음! 패턴을 써서 줄여보자!
    ship.setAnchor(new YellowAnchor());
    ship.setWheel(new YellowWheel());
    return null;
  }
}
