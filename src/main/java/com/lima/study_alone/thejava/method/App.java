package com.lima.study_alone.thejava.method;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
  public static void main(String[] args) {
    List<String> names = new ArrayList<>();
    names.add("Kim");
    names.add("you");
    names.add("lim");
    names.add("code");

    // **메소드 레퍼런스스스스스스스**
//    name.forEach(System.out::println);
//    for (String n:names) {
//      System.out.println(n);
//    }

    // **쪼갤수 있는 기능을 갖고있는 iterator**
//    Spliterator<String> spliterator = names.spliterator();
//    Spliterator<String> stringSpliterator = spliterator.trySplit();
//    while (spliterator.tryAdvance(System.out::println));
//    System.out.println("===================================");
//    while(stringSpliterator.tryAdvance(System.out::println));

    // **Collection의 기본 메소드**
    // stream
//    long k = names.stream()
//        .map(String::toUpperCase)
//        .filter(s -> s.startsWith("K"))
//        .count();
//    System.out.println(k);
    // removeIf
//    names.removeIf(s -> s.startsWith("k"));
//    names.forEach(System.out::println);


    // **Comparator 의 기본 메소드 및 스태틱 메소드**
//    names.sort(String::compareToIgnoreCase);
    Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
    // thenComparing 으로 comparing 가능
//    names.sort(compareToIgnoreCase.reversed().thenComparing(~~));
    names.sort(compareToIgnoreCase.reversed());
    names.forEach(System.out::println);

  }
}
