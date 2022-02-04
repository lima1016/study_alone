package com.lima.study_alone.thejava.functionalInterface;

import jdk.incubator.vector.VectorOperators;
import org.apache.tomcat.util.modeler.ParameterInfo;

import java.util.function.*;

public class Plus10Application {
  public static void main(String[] args) {

    // **첫번째 예제**
//    Plus10 plus10 = new Plus10();
//    System.out.println(plus10.apply(1));

    // **두번째 예제**
//    Function<Integer, Integer> plus10 = (i) -> {
//      return 1 + 10;
//    };
//    Function<Integer, Integer> plus10 = (i) -> i + 10;
//    Function<Integer, Integer> multiply2 = (i) -> i * 2;

//    System.out.println(plus10.apply(1));
//    System.out.println(multiply2.apply(10));

    // **세번째 예제**
    // 두 함수를 조합할 수 있음
    // 10을 더하기 전에 먼저 곱하겠다.
    // compose() 가 입력값을 가지고 뒤에오는 함수를 먼저 적용함.
    // compose() 에 대한 결과값을 입력값으로 사용.
//    System.out.println(plus10.compose(multiply2).apply(2));

    // **네번째 예제**
    // andThen()을 하면 앞에있는 펑션 끝나고 뒤에꺼 실행
//    System.out.println(plus10.andThen(multiply2).apply(2));

    // **다섯번째 예제**
    // 받은거 걍 바로 주는 함수
    //Consumer<Integer> printT = System.out::println;
//    Consumer<Integer> printT = (i) -> System.out.println(i);
//    printT.accept(10);

    // **여섯번째 예제**
    // 값을 가져오는 함수 인터페이스 받아올 값의 타입을 정의함.
    // 입력해주는 값이 없기때문에 () 인자를 줄 필요가 없다. 무조건 10 리턴~!!
//    Supplier<Integer> get10 = () -> 10;
//    System.out.println(get10.get());

    // **일곱번째 예제**
    // 어떠한 인자값을 하나 받아서 true false 를 리턴해주는 함수형 인터페이스
//    Predicate<String> startWithLima = (s) -> s.startsWith("lim");

    // 홀수인지 아닌지
//    Predicate<Integer> isOdd = (i) -> i%2 == 1;

//    System.out.println(startWithLima.test("lim"));
//    System.out.println(isOdd.test(4));

    // **여덟번째**
    // 입력 값과, 리턴 값의 타입이 같을 경우 UnaryOperator 를 사용할 수 있다.
//    Function<Integer, Integer> plus10 = (i) -> i + 10;
    UnaryOperator<Integer> plus10 = (i) -> i + 10;

    // BiFunction<T, U, R>
    // 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
    // R apply(T t, U u)
    
    // BinaryOperator<T>
    // BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입렵값 두개를 받아 리턴하는 함수

  }
}
