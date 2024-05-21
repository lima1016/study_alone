package com.lima.study_alone.inflearn.thejava.completablefutire;

import java.util.concurrent.*;

/*
자바에서 비동기(Asynchronous) 프로그래밍을 가능케하는 인터페이스.
    ● Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많았다.
 */
public class CompletableFutureApp {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // **첫번째 예제**
    // Future로는 하기 어렵던 작업들
    //    ● Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
    //    ● 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
    //    ● 여러 Future를 조합할 수 없다, 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록가져오기
    //    ● 예외 처리용 API를 제공하지 않는다.
//    ExecutorService executorService = Executors.newFixedThreadPool(4);
//    Future<String> future = executorService.submit(() -> "hello");
    // future 를 가지고 get() 을 하기 전까지는 그 어떤 것도 할수 없다.
    // future 에서 나온 결과 값을 가지고 어떤 것을 하던 get() 뒤로 와야함
    // blocking call
//    future.get();

    // **두번째 예제**
    // 명시적으로 Executor 를 쓸 필요가 없다.
    // CF 만 가지고 비동기적으로 작업들을 실행할 수 있다.
//    CompletableFuture<Object> future1 = new CompletableFuture<>();
    // 기본 값을 정해줬고 future1의 작업 자체를 끝냄
//    future1.complete("lima");
    // get()은 해야함
//    System.out.println(future1.get());

    // **세번째 예제**
    // 비동기로 작업 실행하기
    //    ● 리턴값이 없는 경우: runAsync()
    //    ● 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다. (기본은 ForkJoinPool.commonPool())
//    CompletableFuture<String> future = CompletableFuture.completedFuture("lima");
//    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//      System.out.println("Hello " + Thread.currentThread().getName());
//    });
//    future.get();

    //    ● 리턴값이 있는 경우: supplyAsync()
//    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//      System.out.println("Hello " + Thread.currentThread().getName());
//      return "Hello";
//    });
//    System.out.println(future.get());

    // **콜백 제공하기**
    //    ● thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
//    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//      System.out.println("Hello " + Thread.currentThread().getName());
//      return "Hello";
//    }).thenApply(s -> {
//      System.out.println(Thread.currentThread().getName());
//      return s.toUpperCase();
//    });
    // get() 이 없으면 아무일도 일어나지 않는다.
//    System.out.println(future.get());


    //    ● thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이)
//    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//      System.out.println("Hello " + Thread.currentThread().getName());
//      return "Hello";
//    }).thenAccept(s -> {
//      System.out.println(Thread.currentThread().getName());
//      System.out.println(s.toUpperCase());
//    });
    // get() 이 없으면 아무일도 일어나지 않는다.
//    future.get();


    //    ● thenRun(Runnable): 리턴값 받지 다른 작업을 처리하는 콜백
//    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//      System.out.println("Hello " + Thread.currentThread().getName());
//      return "Hello";
//    }).thenRun(() -> {
//      System.out.println(Thread.currentThread().getName());
//    });
    // === 결과 값 ===
    // Hello ForkJoinPool.commonPool-worker-1
    //ForkJoinPool.commonPool-worker-1


    // get() 이 없으면 아무일도 일어나지 않는다.
//    future.get();


    // 그런데 지금 까지 우리가 비동기 작업을 했는데 thread pool을 만들지 않고 별도의 스레드에서 동작을 한걸까?
    // ForkJoinPool 때문에 그럼. executor 를 구현한 구현체 중 하나임.
    // DQue (맨마지막에 들어온것이 먼저 나감) 자기 스레드가 할일이 없으면 스레드가 직접 DQue 에서 할일을 가져와서 처리하는 방식의 프레임워크
    // 작업 단위를 자기가 파생시키는 세부적인 서브 테스크가 있다면 잘게 쪼개서 다른 스레드에 분산시켜서 작업을 처리함.

    // 별다른 executor 를 사용하지 않아도 내부적으로 ForkJoinPool 은 commonPool 를 사용함
    //    ● 콜백 자체를 또 다른 쓰레드에서 실행할 수 있다
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello " + Thread.currentThread().getName());
      return "Hello";
      // 두번째 인자로 줄 수 있음. thenAcceptAsync() 등등 사용 가능.
    }, executorService).thenRun(() -> {
      System.out.println(Thread.currentThread().getName());
    });

    // === 결과 값 ===
    // Hello pool-1-thread-1
    // pool-1-thread-1
    future.get();


  }
}
