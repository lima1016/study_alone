package com.lima.study_alone.thejava.method;

// **두번째 예제**
// FooInterface 와 BarInterface 에서 똑같은 구현 메소드들이 있는 상태에 두 Interface를 impl 받은 상태
// 그렇다면 printNameUpperCase 충돌로 컴파일 에러가 발생
//public class DefaultFoo implements FooInterface, BarInterface {
public class DefaultFoo implements FooInterface{

  String name;

  public DefaultFoo(String name) {
    this.name = name;
  }

  @Override
  public void printName() {
    System.out.println(this.name);
  }

  @Override
  public String getName() {
    return this.name;
  }

  // **첫번째 예제**
  // interface 에 선언했던 default 재정의 가능
  // **두번째 예제**
  // 위 충돌로인한 컴파일 에러를 해결 하려면 printNameUpperCase 을 여기서 직접 구현을 해야함
  // ● 인터페이스 구현체가 재정의 할 수도 있다
  @Override
  public void printNameUpperCase() {
    System.out.println(getName().toUpperCase());
  }


}
