package com.lima.study_alone.inflearn.thejava.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIApplication {
  public static void main(String[] args) {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(2, "spring data jpa", true));
    springClasses.add(new OnlineClass(3, "spring mvc", false));
    springClasses.add(new OnlineClass(4, "spring core", false));
    springClasses.add(new OnlineClass(5, "rest api development", false));


    /*
    걸러내기
      ● Filter(Predicate)
      ● 예) 이름이 3글자 이상인 데이터만 새로운 스트림으로
     */
    System.out.println("======spring 으로 시작하는 수업======");
    // TODO: 내풀이
    springClasses.stream()
        .map(OnlineClass::getTitle)
        .filter(s -> s.startsWith("spring"))
        .forEach(System.out::println);
    System.out.println("=================");
    // 강사님 풀이
    // forEach 는 stream 을 return 하지 않기 때문에 종료 operator 임.
    springClasses.stream()
        .filter(oc -> oc.getTitle().startsWith("spring"))
        .forEach(oc -> System.out.println(oc.getId()));

    System.out.println("======spring classes 에서 close 되지 않은 수업======");
    // TODO
    springClasses.stream()
        .filter(oc -> !oc.isClosed())
        .forEach(oc -> System.out.println(oc.getId()));
    System.out.println("=================");
    //강사님 풀이
    springClasses.stream()
        .filter(oc -> !oc.isClosed())
        .forEach(oc -> System.out.println(oc.getId()));

    // 강사님 다른 풀이 메소드 레퍼런스를 사용해서
    // 임의의 객체에 인스턴스 참조 하는 방법이 메소드 레퍼런스
    springClasses.stream()
        .filter(Predicate.not(OnlineClass::isClosed)) // !OnlineClass::isClosed
        .forEach(oc -> System.out.println(oc.getId()));


    /*
    변경하기
      ● Map(Function) 또는 FlatMap(Function)
      ● 예) 각각의 Post 인스턴스에서 String title 만 새로운 스트림으로
      ● 예) List<Stream<String>>을 String 의 스트림으로
     */
    System.out.println("======spring classes 에서 수업 이름만 모아서 스트림 만들기======");
    // TODO
    springClasses.stream()
        .map(OnlineClass::getTitle)
        .forEach(System.out::println);
    System.out.println("=================");
    // 강사님 풀이
    // map 이라는 중개형(stream 을 return 하는 오퍼레이터) 오퍼레이터를 써서 만들어야함
    // 메서드 레퍼런스로 변경함 OnlineClass::getTitle
    springClasses.stream()
        .map(OnlineClass::getTitle)
        .forEach(System.out::println);

    List<OnlineClass> javaClasses = new ArrayList<>();
    javaClasses.add(new OnlineClass(6, "The Java Test", true));
    javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
    javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

    // List 두개를 갖고있는 List 임
    List<List<OnlineClass>> keesunEvents = new ArrayList<>();
    keesunEvents.add(springClasses);
    keesunEvents.add(javaClasses);

    System.out.println("======두 수업 목록에 들어있는 모든 수업 아이디 출력======");
    // TODO
    keesunEvents.stream()
        .flatMap(List::stream)
        .map(OnlineClass::getId)
        .forEach(System.out::println);
    System.out.println("=================");
    // 강사님 풀이
    // flatMap => 각각의 list 로 들어간 것을 평평하게 다 꺼내는 작업 다 풀어내는 것.
    keesunEvents.stream()
        .flatMap(Collection::stream)
        .forEach(oc -> System.out.println(oc.getId()));

    /*
    생성하기
      ● generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
      ● 예) 10부터 1씩 증가하는 무제한 숫자 스트림
      ● 예) 랜덤 int 무제한 스트림
    제한하기
      ● limit(long) 또는 skip(long)
      ● 예) 최대 5개의 요소가 담긴 스트림을 리턴한다.
      ● 예) 앞에서 3개를 뺀 나머지 스트림을 리턴한다.
     */
    System.out.println("======10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만======");
    // TODO
    // 무제한 스트림 :: 이부분 생성은 강사님 도움 받음...
    Stream.iterate(10, i -> i + 1)
        .skip(10)
        .limit(10)
        .forEach(System.out::println);

    /*
    스트림에 있는 데이터가 특정 조건을 만족하는지 확인
      ● anyMatch(), allMatch(), nonMatch()
      ● 예) k로 시작하는 문자열이 있는지 확인한다. (true 또는 false를 리턴한다.)
      ● 예) 스트림에 있는 모든 값이 10보다 작은지 확인한다.
     */
    System.out.println("======자바 수업 중에 Test가 들어있는 수업이 있는지 확인======");
    // TODO
    boolean test = javaClasses.stream()
        .map(OnlineClass::getTitle)
        .anyMatch(s -> s.contains("Test"));
    System.out.println(test);

    //강사님 풀이
    boolean test1 = javaClasses.stream()
        .anyMatch(oc -> oc.getTitle().contains("Test"));
    System.out.println(test1);

    /*
    개수 세기
      ● count()
      ● 예) 10보다 큰 수의 개수를 센다.
  스트림을 데이터 하나로 뭉치기
      ● reduce(identity, BiFunction), collect(), sum(), max()
      ● 예) 모든 숫자 합 구하기
      ● 예) 모든 데이터를 하나의 List 또는 Set에 옮겨 담기
     */
    System.out.println("======스프링 수업 중에 제목에 spring 이 들어간 것만 제목만 모아서 List 만들기======");
    // TODO
    springClasses.stream()
        .map(OnlineClass::getTitle)
        .filter(t -> t.contains("spring"))
        .collect(Collectors.toList())
        .forEach(System.out::println);
  }
}
