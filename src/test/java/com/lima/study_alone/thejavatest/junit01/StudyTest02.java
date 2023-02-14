package com.lima.study_alone.thejavatest.junit01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
// JUnit 5: 시작하기
class StudyTest02 {

  @Test
  void create() {
    Study study02 = new Study();
    assertNotNull(study02);
    System.out.println("create");
  }

  @Test
  // 이 테스트는 실행하고싶지 않다!
//  @Disabled
  void create1() {
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