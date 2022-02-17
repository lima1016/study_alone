package com.lima.study_alone.thejava.annotation;

import java.util.Arrays;
import java.util.List;

/*
애노테이션 관련 두가지 큰 변화
  ● 자바 8부터 애노테이션을 타입 선언부에도 사용할 수 있게 됨.
  ● 자바 8부터 애노테이션을 중복해서 사용할 수 있게 됨.
타입 선언 부
  ● 제네릭 타입
  ● 변수 타입
  ● 매개변수 타입
  ● 예외 타입
타입에 사용할 수 있으려면
  ● TYPE_PARAMETER: 타입 변수에만 사용할 수 있다.
  ● TYPE_USE: 타입 변수를 포함해서 모든 타입 선언부에 사용할 수 있다.
 */
// ** TYPE_USE **
@Chicken("양념")
@Chicken("간장")
public class AnnotaionApplication {
  // ** TYPE_USE **
//  public static void main(@Chicken String[] args) throws @Chicken RuntimeException{
  public static void main(String[] args) throws RuntimeException{
    // ** TYPE_USE **
//    List<@Chicken String> names = Arrays.asList("Lima");

    // **두번째 예제**
    Chicken[] chickens = AnnotaionApplication.class.getAnnotationsByType(Chicken.class);
    Arrays.stream(chickens).forEach(c -> System.out.println(c.value())); // 양념, 간장

    ChickenContainer chickenContainer = AnnotaionApplication.class.getAnnotation(ChickenContainer.class);
    Arrays.stream(chickenContainer.value()).forEach(c -> {
      System.out.println(c.value());
    });
  }
  // ** TYPE_PARAMETER **
//  static class FeelsLikeChicken<@Chicken T> {
    // ** TYPE_PARAMETER **               // ** TYPE_USE **
//    public static <@Chicken C> void print (@Chicken C c) {
//      System.out.println(c);
//    }
//  }
}
