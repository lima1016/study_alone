package com.lima.study_alone.designpatterns.mementopattern;

/**
 * 메멘토 (Memento) 패턴
 * 캡슐화를 유지하면서 객체 내부 상태를 외부에 저장하는 방법.
 * • 객체 상태를 외부에 저장했다가 해당 상태로 다시 복구할 수 있다.
 * • 장점
 * • 캡슐화를 지키면서 상태 객체 상태 스냅샷을 만들 수 있다.
 * • 객체 상태 저장하고 또는 복원하는 역할을 CareTaker에게 위임할 수 있다.
 * • 객체 상태가 바뀌어도 클라이언트 코드는 변경되지 않는다.
 * • 단점
 * • 많은 정보를 저장하는 Mementor를 자주 생성하는 경우 메모리 사용량에 많은 영향을 줄 수 있다.
 */
public class Memento {
}
