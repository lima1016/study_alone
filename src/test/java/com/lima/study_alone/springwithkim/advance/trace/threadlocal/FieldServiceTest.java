package com.lima.study_alone.springwithkim.advance.trace.threadlocal;

import com.lima.study_alone.springwithkim.advance.trace.threadlocal.code.FieldService;
import com.lima.study_alone.springwithkim.advance.trace.threadlocal.code.ThreadLocalFieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
  /** > 참고
   > 이런 동시성 문제는 지역 변수에서는 발생하지 않는다. 지역 변수는 쓰레드마다 각각 다른 메모리 영역이할당된다.
   > 동시성 문제가 발생하는 곳은 같은 인스턴스의 필드(주로 싱글톤에서 자주 발생), 또는 static 같은 공용필드에 접근할 때 발생한다.
   > 동시성 문제는 값을 읽기만 하면 발생하지 않는다. 어디선가 값을 변경하기 때문에 발생한다.
   */

@Slf4j
public class FieldServiceTest {

  private FieldService fieldService = new FieldService();
  private ThreadLocalFieldService threadLocalFieldService = new ThreadLocalFieldService();

    @Test
    void threadField() {
      log.info("main start");
      Runnable userA = () -> {
        threadLocalFieldService.logic("userA");
      };
      Runnable userB = () -> {
        threadLocalFieldService.logic("userB");
      };

      Thread threadA = new Thread(userA);
      threadA.setName("thread-A");
      Thread threadB = new Thread(userB);
      threadB.setName("thread-B");

      threadA.start();
    sleep(2000); // 동시성 문제 X
//      sleep(100); // 동시성 문제 O
      threadB.start();
      sleep(3000); // 메인 쓰레드 종료 대기
      log.info("main exit");
    }

  @Test
  void field() {
    log.info("main start");
    Runnable userA = () -> {
      fieldService.logic("userA");
    };
    Runnable userB = () -> {
      fieldService.logic("userB");
    };

    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");
    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
//    sleep(2000); // 동시성 문제 X
    sleep(100); // 동시성 문제 O
    threadB.start();
    sleep(2000); // 메인 쓰레드 종료 대기
//    sleep(3000); // 메인 쓰레드 종료 대기
    log.info("main exit");

    /*
    동시성 문제
    결과적으로 Thread-A 입장에서는 저장한 데이터와 조회한 데이터가 다른 문제가 발생한다. 이처럼 여러
    쓰레드가 동시에 같은 인스턴스의 필드 값을 변경하면서 발생하는 문제를 동시성 문제라 한다. 이런 동시성
    문제는 여러 쓰레드가 같은 인스턴스의 필드에 접근해야 하기 때문에 트래픽이 적은 상황에서는 확률상 잘
    나타나지 않고, 트래픽이 점점 많아질 수 록 자주 발생한다.
    특히 스프링 빈 처럼 싱글톤 객체의 필드를 변경하며 사용할 때 이러한 동시성 문제를 조심해야 한다.
     */
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
