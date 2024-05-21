package com.lima.study_alone.inflearn.thejava.completablefuture;

import java.util.concurrent.*;

/*
Callable
  ● Runnable과 유사하지만 작업의 결과를 받을 수 있다.
Future
  ● 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있다.
 */
public class CallableApplication {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    // **첫번째 예제**
    Callable<String> hello = () -> {
      Thread.sleep(2800);
      return "Hello";
    };
    // callable 을 리턴하는 타입의 future 를 리턴할 수 있다.
    Future<String> helloFuture = executorService.submit(hello);

    // **두번째 예제**
    // 작업 상태 확인하기 isDone()
    //  ● 완료 했으면 true 아니면 false를 리턴한다.
    System.out.println(helloFuture.isDone());
    System.out.println("Started!");

    // **세번째 예제**
    // 작업 취소하기 cancel()
    //  ● 취소 했으면 true 못했으면 false 를 리턴한다. => 기다린다.
    //  ● parameter로 true를 전달하면 현재 진행중인 쓰레드를 interrupt하고 그러지 않으면 현재
    //진행중인 작업이 끝날때까지 기다린다.
    // isDone()때문이 아님 cancel 을 했기때문에 종료가 된거임.
    helloFuture.cancel(false);

    // **두번째 예제**
    // get을 만난 순간 멈춘다. 그리고 결과값을 가져올때 까지 기다림.
    // ● 블록킹 콜이다.
    // ● 타임아웃(최대한으로 기다릴 시간)을 설정할 수 있다
//    helloFuture.get();
    System.out.println(helloFuture.isDone());
    // **세번째 예제**
    // Exception in thread "main" java.util.concurrent.CancellationException
    // 취소했는데 왜또 가져오라고해!
//    helloFuture.get();

    System.out.println("End!");
    executorService.shutdown();
  }
}
