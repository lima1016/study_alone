package com.lima.study_alone.thejavatest.junit01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
// JUnit 5: 테스트 이름 표시하기

/**
 * @DisplayNameGeneration
 * ● Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정.
 * ● 기본 구현체로 ReplaceUnderscores 제공
 * @DisplayName
 * ● 어떤 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션.
 * ● @DisplayNameGeneration 보다 우선 순위가 높다.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // "_"를 공백으로 바꿔줌 create new study
class StudyTest03 {

  @Test
  @DisplayName("😊😊😊")
  void create_new_study() {
    Study study02 = new Study();
    assertNotNull(study02);
    System.out.println("create");
  }

  @Test
  // 이 테스트는 실행하고싶지 않다!
  void create_new_study_again() {
    System.out.println("create1");
  }

  // 모든 테스트를 실행하기 전에 한번만 호출 반드시 static method 사용 private 안됨 default 됨 return type 있으면 안됨
  @BeforeAll
  static void beforeAll() {
    System.out.println("Before all");
  }

  // 모든 테스트가 실행된 후 한번만 실행
  @AfterAll
  static void afterAll() {
    System.out.println("After all");
  }

  // 각각 테스트하기 전에 한번 호출 static 일 필요는 없다.
  @BeforeEach
  void beforeEach() {
    System.out.println("before each");
  }

  // 메소드 테스트 후에 한번 호출 static 일 필요는 없다.
  @AfterEach
  void afterEach() {
    System.out.println("after each");
  }

}