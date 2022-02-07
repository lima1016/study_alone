package com.lima.study_alone.thejava.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApplication {

  /*
  Stream API
    collection 같은 연속된 data 를 처리하는 operation 들의 모음.
    ● sequence of elements supporting sequential and parallel aggregate operations
    ● 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
    ● Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
    ● 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
    ● 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
    ● 손쉽게 병렬 처리할 수 있다.
   */
  public static void main(String[] args) {
    List<String> names = new ArrayList<>();
    names.add("Kim");
    names.add("you");
    names.add("lim");
    names.add("code");

    // **첫번째 예제**
    // 이렇게하면 또 다른 스트림이 생성됨
//    Stream<String> stringStream = names.stream().map(String::toUpperCase);
    // 그래서 결과 값은 Upper case 로 변경되지 않음
//    names.forEach(System.out::println);
    // 대문자로 바뀜
//    names.stream().map(String::toUpperCase).forEach(System.out::println);

    // **두번째 예제**
    // ● 중개 오퍼레이션은 근본적으로 lazy 하다.
    //    =>
    // 중개 오퍼레이션
    //    ● Stream 을 리턴한다. => 종료 오퍼레이션과의 큰 차이점
    //    ● Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다.
    //    (대부분은 Stateless 지만 distinct 나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
    //    ● filter, map, limit, skip, sorted, ..
    //

    // 현재 리턴값은 Stream => 중개 오퍼레이션
//    Stream<String> stringStream2 = names.stream().map(String::toUpperCase);

    // 출력 안됨
    // Stream 으로 끝나기때문에 중개형 오퍼레이터임
//    names.stream().map(s -> {
//      System.out.println(s);
//      return s.toUpperCase();
//    });
//    System.out.println("======================");

    // 종료형으로 변경
    // 종료 오퍼레이션
    //  ● Stream을 리턴하지 않는다.
    //  ● collect, allMatch, count, forEach, min, max, ...

    // 스트림 파이프라인
    //  ● 0 또는 다수의 중개 오퍼레이션 (intermediate operation)과 한개의 종료 오퍼레이션
    //  (terminal operation)으로 구성한다.
    //● 스트림의 데이터 소스는 오직 터미널 오퍼네이션을 실행할 때에만 처리한다.
    // 대문자 X
//    List<String> collect = names.stream().map(s -> {
//      System.out.println(s);
//      return s.toUpperCase();
//    }).collect(Collectors.toList());
//    System.out.println("======================");
    // 대문자 처리
//    collect.forEach(System.out::println);
//    System.out.println("======================");
//    names.forEach(System.out::println);

    // **세번째 예제**
    // 병렬처리가 어려움움
//   for (String name : names) {
//      if (name.startsWith("k")) {
//        System.out.println(name.toUpperCase());
//      }
//    }

   // 병렬처리 가능
//    List<String> collect = names.stream().map(String::toUpperCase)
    List<String> collect = names.parallelStream().map(String::toUpperCase)
        .collect(Collectors.toList());
    collect.forEach(System.out::println);







  }

}
