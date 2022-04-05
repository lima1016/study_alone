package com.lima.study_alone.thejavatest.junit01;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

  @Test
  void create() {
    Study study = new Study();
    assertNotNull(study);
    System.out.println("create");
  }

  @Test
  // 이 테스트는 실행하고싶지 않다!
  @Disabled
  void create1() {
    System.out.println("create1");
  }

  // 모든 테스트를 실행하기 전에 한번만 호출 반드시 static method 사용 default 안됨 return type 있으면 안됨
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

  @AfterEach
  void afterEach() {
    System.out.println("after each");
  }

}