package com.lima.study_alone.designpatterns.behavioral_patterns.observerpattern;

/**
 * 옵저버 (Observer) 패턴
 * 다수의 객체가 특정 객체 상태 변화를 감지하고 알림을 받는 패턴.
 * • 발행(publish)-구독(subscribe) 패턴을 구현할 수 있다.
 * • 장점
 * • 상태를 변경하는 객체(publisher)와 변경을 감지하는 객체(subsriber)의 관계를 느슨하게 유지할 수 있다.
 * • Subject의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다.
 * • 런타임에 옵저버를 추가하거나 제거할 수 있다.
 * • 단점
 * • 복잡도가 증가한다.
 * • 다수의 Observer 객체를 등록 이후 해지 않는다면 memory leak이 발생할 수도 있다
 */
public class Observer {
}
