package com.lima.study_alone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyAloneApplicationTests {

  @Test
  void contextLoads() {
    int a = 1;
    int b = 2;

    int sum = a + b;

    System.out.println(sum);
  }

}
