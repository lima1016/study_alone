package com.lima.study_alone.inflearn.thejava.completablefuture;

import org.apache.catalina.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 고수준 (High-Level) Concurrency 프로그래밍
//    ● 쓰레드를 만들고 관리하는 작업을 애
// Executors가 하는 일
//    ● 쓰레드 만들기: 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
//    ● 쓰레드 관리: 쓰레드 생명 주기를 관리한다.
//    ● 작업 처리 및 실행: 쓰레드로 실행할 작업을 제공할 수 있는 API를 제공한다.
//주요 인터페이스
//    ● Executor: execute(Runnable)
//    ● ExecutorService: Executor 상속 받은 인터페이스로, Callable도 실행할 수 있으며,
//Executor를 종료 시키거나, 여러 Callable을 동시에 실행하는 등의 기능을 제공한다.
//    ● ScheduledExecutorService: ExecutorService를 상속 받은 인터페이스로 특정 시간이후에 또는 주기적으로 작업을 실행할 수 있다.
// Fork/Join 프레임워크
//    ● ExecutorService의 구현체로 손쉽게 멀티 프로세서를 활용할 수 있게끔 도와준다.
public class ExecutorsApplication {
  public static void main(String[] args) {
    // **첫번째 예쩨**
//    ExecutorService  executorService = Executors.newSingleThreadExecutor();
    // 가장 고전적인 방법
    // ExecutorService 는 작업을 실행 후 다음 작업이 들어올때 까지 계속 시다림
//    executorService.execute(() -> {
//      System.out.println("Thread " + Thread.currentThread().getName());
//    });
    // 그래서 명시적으로 shutdown 을 해줘야함
    // graceful shutdown - 현재 진행중인 작업은 마치고 끝낸다.
//    executorService.shutdown();
    // 아니? 뭐가 진행중이던 말던 나는 바로 끝낼꺼야!
//    executorService.shutdownNow();

    // **두번째 방법**
    // thread 를 2개 갖고있는 서비스를 만듬
//   ExecutorService  executorService2 = Executors.newFixedThreadPool(2);
//    executorService2.submit(getRunnable("Hello "));
//    executorService2.submit(getRunnable("Lima "));
//    executorService2.submit(getRunnable("Kim "));
//    executorService2.submit(getRunnable("Youlim "));
//    executorService2.submit(getRunnable("Java "));

   // **세번째 예제**
   ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
   // 3초 정도 있다가 실행을 해라
//   executorService2.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
    // 1초 기다렸다가 2초에 한번씩 찍어
   executorService2.scheduleAtFixedRate(getRunnable("Hello "), 1, 2, TimeUnit.SECONDS);
  }

  private static Runnable getRunnable(String message) {
    return () -> System.out.println(message + Thread.currentThread().getName());
  }
}
