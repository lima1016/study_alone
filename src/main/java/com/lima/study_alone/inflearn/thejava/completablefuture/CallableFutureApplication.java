package com.lima.study_alone.inflearn.thejava.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureApplication {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // // Hello 가 뙇 나와서 강사님도 깜짝 놀람
//    ExecutorService executorService = Executors.newSingleThreadExecutor();
    // 드디어 Lima 가 나옴
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    Callable<String> hello = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };

    Callable<String> java = () -> {
      Thread.sleep(3000L);
      return "Java";
    };

    Callable<String> lima = () -> {
      Thread.sleep(1000L);
      return "Lima";
    };

    // 여러 작업 동시에 실행하기 invokeAll()
    //  ● 동시에 실행한 작업 중에 제일 오래 걸리는 작업 만큼 시간이 걸린다. 모두 끝날때까지 기다림.
//    List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, lima));
//    for (Future<String> f : futures) {
//      System.out.println(f.get());
//    }
//    System.out.println("=========================");
    // 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기 invokeAny()
    //   ● 동시에 실행한 작업 중에 제일 짧게 걸리는 작업 만큼 시간이 걸린다.
    //   ● 블록킹 콜이다.
    String s = executorService.invokeAny(Arrays.asList(hello, java, lima));
    // 강사님이 아주 자신있게 제일 짧은 Lima 가 찍힐껍니다! 하셨는데
    // Hello 가 뙇 나와서 강사님도 깜짝 놀람
    // newSingleThreadExecutor 때문에 그런거같다고 그러심
    System.out.println(s);

    executorService.shutdown();
  }
}
