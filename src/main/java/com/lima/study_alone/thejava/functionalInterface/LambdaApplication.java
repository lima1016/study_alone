package com.lima.study_alone.thejava.functionalInterface;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaApplication {
  public static void main(String[] args) {
    LambdaApplication lambda = new LambdaApplication();
    lambda.run();
  }


  private void run() {
    int baseNumber = 10;

    // 로컬 클래스
    // 스콥(로컬 클래스) 안에있는 변수를 참조함.
    class LocalClass {
      void printBaseNumber() {
        int baseNumber = 11;
        System.out.println(baseNumber); // 11이 찍찎
      }
    }

    // 익명 클래스
    // 익명클래스안에 선언한 baseNumber 는 익명클래스를 감싸고있는 run()메소드의 baseNumber 를 참조하지않음
    // 스콥(익명클래스) 안에있는 baseNumber 의 파라미터를 참조 하게됨
    Consumer<Integer> integerConsumer = new Consumer<Integer>() {
      @Override
      public void accept(Integer baseNumber) {
        System.out.println(baseNumber);
      }
    };

    // 람다
    // 람다는 run()과 같은 스콥이다 => 같은 스콥안에 똑같은 변수를 정의할 수 없다.(중복이라고 뜸)
    // baseNumber 가 바뀔 경우 즉 effective final 이 아닐 경우에도 참조할 수 없다.
    // final 로 선언된 변수만 참조함
    IntConsumer printInt = (i) -> System.out.println(i + baseNumber);

    // effective final 이었던 baseNumber 의 값이 변경된 순간.
    // baseNumber++;
    printInt.accept(100);
  }
}
