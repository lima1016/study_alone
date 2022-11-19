package com.lima.study_alone.designpatterns.behavioral_patterns.visitorpattern;

/**
 * 방문자 (Visitor) 패턴
 * 기존 코드를 변경하지 않고 새로운 기능을 추가하는 방법.
 * • 더블 디스패치 (Double Dispatch)를 활용할 수 있다.
 * • 장점
 * • 기존 코드를 변경하지 않고 새로운 코드를 추가할 수 있다.
 * • 추가 기능을 한 곳에 모아둘 수 있다.
 * • 단점
 * • 복잡하다.
 * • 새로운 Element를 추가하거나 제거할 때 모든 Visitor 코드를 변경해야 한다.
 * • 자바
 * • FileVisitor, SimpleFileVisitor
 * • AnnotationValueVisitor
 * • ElementVisitor
 * • 스프링
 * • BeanDefinitionVisitor
 */
public class Visitor {
}
