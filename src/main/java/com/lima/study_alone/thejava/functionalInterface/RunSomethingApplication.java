package com.lima.study_alone.thejava.functionalInterface;

public class RunSomethingApplication {
  public static void main(String[] args) {

    // **첫번째 예제**
    // 익명 내부 클래스 => 자바8 이전에는 이렇게 썼음!
//    RunSomething runSomething = new RunSomething() {
//      @Override
//      public void doIt() {
//
//      }
//    };

    // 자바 8 이후 람다 사용 가능!
    // **첫번째 예제 1**
//    RunSomething runSomething = () -> {
//      System.out.println("Hello");
//      System.out.println("LimLima!!!!!!!");
//    };

    // **두번째 예제**
//    RunSomething runSomething = num -> num + 10;

    // **두번째 예제**
    // 입력 받은 값이 동일한 경우 결과는 같아야함.
    // 여러번 호출 해도 항상 값은 같아야함
    // 결과 값이 달라지는 경우가 있을 경우 혹은
    // 그럴 여지(결과값이 달라지는)를 줄 경우 함수형 프로그래밍이라고 부를 수 없다.
//    System.out.println(runSomething.doIt(1));
//    System.out.println(runSomething.doIt(1));
//    System.out.println(runSomething.doIt(1));

    // **세번째 예제**
    // 함수의 밖에서 값을 가져와야하는 경우 퓨어한 함수라고 부를 수 없음.
    // 외부에 있는 값을 변경하려고 하는경우 ++ 같은것(문법적으로는 가능) 퓨어한 함수X
    RunSomething runSomething = new RunSomething() {
      // final로 가정하고 써야함 보통 final생략 되도 final임
      final int number = 10;
      @Override
      public int doIt(int num) {
        return num + number;
      }
      // 불가능
//      number++;
    };
  }
}
