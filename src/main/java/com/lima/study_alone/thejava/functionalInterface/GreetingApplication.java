package com.lima.study_alone.thejava.functionalInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class GreetingApplication {
  public static void main(String[] args) {
    // **첫번째 예제**
    // 이렇게 하면 Greeting 의 public static String hi(String name) 메소드와 똑같음
//    UnaryOperator<String> hi = (s) -> "hi " + s;

    // 그래서!!! 이렇게 사용함
    // :: 콜론이 두번 찍혀있으면 메소드 레퍼런스임
    UnaryOperator<String> hi = Greeting::hi;
    // 이렇게도 사용 쌉가능^^
    Greeting greeting = new Greeting();
    // 메소드를 참조하고있는 UnaryOperator 가 만들어진거임
    UnaryOperator<String> hello = greeting::hello;
    // apply()를 해야지 전달이됨.
    hello.apply("lima");

    // **두번째 예제**
    // 이 자체로는 인스턴스를 만드는게 아님 Supplier 임 Greeting이 아님
    Supplier<Greeting> newGreeting = Greeting::new;
    // 사용 방법은 get()을 해야지 인스턴스가 만들어짐
//    Greeting greeting1 = newGreeting.get();

    // **세번째 예제**
    // 입력값을 받는 생성자일 경우
    Function<String, Greeting> limaGreeting
         = Greeting::new;
    Greeting limlim = limaGreeting.apply("limlim");
    System.out.println(limlim.getName());
    // 레퍼런스는 같아보이지만 서로 다른 생성자를 쓰게됨.
//    Supplier<Greeting> newGreeting = Greeting::new;

    // **네번째 예제**
    String[] names = {"lima", "hello", "kim"};
    //  new Comparator<String>() 도 함수형 인터페이스여서 람다 가능
    // 두 문자열을 비교해서 int 값을 넘겨주는 메소드를 참조할 수 있음. (compareToIgnoreCase)
//    Arrays.sort(names, (o1, o2) -> 0);
    // 고로 메소드 레퍼런스 가능
    Arrays.sort(names, String::compareToIgnoreCase);
    System.out.println(Arrays.toString((names)));
  }
}
