package com.lima.study_alone.inflearn.thejava.annotation;

import java.lang.annotation.*;

// https://dahye-jeong.gitbook.io/java/java/advanced/meta-annotation/2021-06-12-retention-annotation
// 어노테이션의 라이프 사이클을 설정하는 것으로, 즉, 어노테이션이 언제까지 살아 있을지를 정하는 것이다.
// RetentionPolicy.SOURCE : 소스코드(.java)까지 유지(컴파일 과정에서 어노테이션 정보 사라짐)
// RetentionPolicy.CLASS : 클래스파일(.class)까지 유지(런타임시 유지안됨)
// RetentionPolicy.RUNTIME : 런타임시점까지 유지 (Reflection API로 어노테이션 정보 조회 가능)
@Retention(RetentionPolicy.RUNTIME)
// https://seeminglyjs.tistory.com/249
// @Target 은 Java compiler 가 annotation 이 어디에 적용될지 결정하기 위해 사용합니다.
// ElementType.PACKAGE : 패키지 선언
// ElementType.TYPE : 타입 선언
// ElementType.ANNOTATION_TYPE : 어노테이션 타입 선언
// ElementType.CONSTRUCTOR : 생성자 선언
// ElementType.FIELD : 멤버 변수 선언
// ElementType.LOCAL_VARIABLE : 지역 변수 선언
// ElementType.METHOD : 메서드 선언
// ElementType.PARAMETER : 전달인자 선언
// ElementType.TYPE_PARAMETER : 전달인자 타입 선언
// ElementType.TYPE_USE : 타입 선언

// ● TYPE_PARAMETER: 타입 변수에만 사용할 수 있다.
// ● TYPE_USE: 타입 변수를 포함해서 모든 타입 선언부에 사용할 수 있다.
//@Target(ElementType.TYPE_PARAMETER)
@Target(ElementType.TYPE_USE)

@Repeatable(ChickenContainer.class)
public @interface Chicken {
  String value();
}
