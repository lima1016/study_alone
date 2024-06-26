package com.lima.study_alone.inflearn.designpatterns.creational_patterns.abstractpattern;

import com.lima.study_alone.inflearn.designpatterns.creational_patterns.factorypattern.Factory02Ship;
import com.lima.study_alone.inflearn.designpatterns.creational_patterns.factorypattern.Factory02ShipFactory;
import com.lima.study_alone.inflearn.designpatterns.creational_patterns.factorypattern.Factory02YellowShip;

/**
 * 추상 팩토리 (Abstract factory) 패턴
 * 서로 관련있는 여러 객체를 만들어주는 인터페이스.
 * • 구체적으로 어떤 클래스의 인스턴스를(concrete product)를 사용하는지 감출 수 있다.
 * • 자바 라이브러리
 * • javax.xml.xpath.XPathFactory#newInstance()
 * • javax.xml.transform.TransformerFactory#newInstance()
 * • javax.xml.parsers.DocumentBuilderFactory#newInstance()
 * • 스프링
 * • FactoryBean과 그 구현
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
  /*
  추상 팩토리 (Abstract factory) 패턴 복습
팩토리 메소드 패턴과 굉장히 흡사한데 무엇이 다른건가.
• 모양과 효과는 비슷하지만…
  • 둘 다 구체적인 객체 생성 과정을 추상화한 인터페이스를 제공한다.
• 관점이 다르다.
  • 팩토리 메소드 패턴은 “팩토리를 구현하는 방법 (inheritance)”에 초점을 둔다.
  • 추상 팩토리 패턴은 “팩토리를 사용하는 방법 (composition)”에 초점을 둔다.
• 목적이 조금 다르다.
  • 팩토리 메소드 패턴은 구체적인 객체 생성 과정을 하위 또는 구체적인 클래스로 옮기는 것이 목적.
  • 추상 팩토리 패턴은 관련있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들 수 있게 해주는 것이 목적
   */
}
