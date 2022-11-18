package com.lima.study_alone.designpatterns.behavioral_patterns.templatemethod;

/**
 * 템플릿 메소드 (Template method) 패턴
 * 알고리듬 구조를 서브 클래스가 확장할 수 있도록 템플릿으로 제공하는 방법.
 * • 추상 클래스는 템플릿을 제공하고 하위 클래스는 구체적인 알고리듬을 제공한다
 * 콜백으로 상속 대신 위임을 사용하는 템플릿 패턴.
 * • 상속 대신 익명 내부 클래스 또는 람다 표현식을 활용할 수 있다.
 * • 장점
 * • 템플릿 코드를 재사용하고 중복 코드를 줄일 수 있다.
 * • 템플릿 코드를 변경하지 않고 상속을 받아서 구체적인 알고리듬만 변경할 수 있다.
 * • 단점
 * • 리스코프 치환 원칙을 위반할 수도 있다.
 * • 알고리듬 구조가 복잡할 수록 템플릿을 유지하기 어려워진다.
 * • 자바
 * • HttpServlet
 * • 스프링
 * • 템플릿 메소드 패턴
 * • Configuration
 * • 템플릿 콜백 패턴
 * • JdbcTemplate
 * • RestTemplate
 */
public class TemplateMethod {
}
