package com.lima.study_alone.thejava.completablefutire;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class App {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // **여섯번째 예제**
    // **일곱번째 예제**
    // 예외처리
    //    ● exeptionally(Function)
    //    ● handle(BiFunction):
    boolean throwError = false;

//    CompletableFuture<String> testEx = CompletableFuture.supplyAsync(() -> {
//      if (throwError) {
//        throw new IllegalArgumentException();
//      }
//      System.out.println("Hello " + Thread.currentThread().getName());
//      return "Hello";
//    }).exceptionally(ex -> {
//      System.out.println(ex);
//      return "Error!";
//    });
//    System.out.println(testEx.get());

    // **일곱번째 예제**
    // 위의 여섯번째 보다 제너럴한 메소드가 있음 handle(정상적인 결과값, exception)
    CompletableFuture<String> testEx = CompletableFuture.supplyAsync(() -> {
      if (throwError) {
        throw new IllegalArgumentException();
      }
      System.out.println("Hello " + Thread.currentThread().getName());
      return "Hello";
    }).handle((result, ex) -> {
      if (ex != null) {
        System.out.println(ex);
        return "Error!";
      }
      return result;
    });
    System.out.println(testEx.get());


    CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello " + Thread.currentThread().getName());
      return "Hello";
    });

    CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
      System.out.println("World " + Thread.currentThread().getName());
      return "World";
    });

    // **첫번째 예제**
    // ● thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
    // 원래는~~~
    // hello.get(); 한 후 기다려야함
//    hello.get();
    // 그다음에 이어서 하려면world.get(); 해야함
//    world.get();
    // 이제는 바뀜~~~
//    CompletableFuture<String> future = hello.thenCompose(App::getWorld);
//    System.out.println(future.get());

    // **두번째 예제**
    // ● thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행
//    CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
//    System.out.println(future.get());

    // **세번째 예제**
    // ● allOf(): 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
    // 모든 테스크 들의 결과가 동일한 타입이라는 보장이 없음 hello-> string/world->Integer
//    CompletableFuture<Void> future = CompletableFuture.allOf(hello, world)
//        .thenAccept(System.out::println);
//    System.out.println(future.get());

    // **네번째 예제**
    // 나는 위 hello 와 world 테스크들의 결과 값을 collection 으로 만들어서 받고 싶음
//    List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
//    CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

    // thenApply 가 시작될 쯤에는 모든 future 의 작업이 끝났음.
    // get()을 쓰면 try catch 로 감싸줘야해서 이상해짐. 그래서 join()을 씀.
    /* 원래 코드
    CompletableFuture<List<Object>> result = CompletableFuture.allOf(futuresArray)
        .thenApply(v -> {
          return futures.stream()
              .map(CompletableFuture::join)
              .collect(Collectors.toList());
        });
     */
//    CompletableFuture<List<String>> result = CompletableFuture.allOf(futuresArray)
//        .thenApply(v -> futures.stream()
//            .map(CompletableFuture::join)
//            .collect(Collectors.toList()));

//    result.get().forEach(System.out::println);
//
    // **다섯번째 예제**
    // 조합하기
    //    ● anyOf(): 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행
    CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
    future.get();
  }

  // **첫번째 예제**
  // ● thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
  private static CompletableFuture<String> getWorld(String message) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("World " + Thread.currentThread().getName());
      return message + "World";
    });
  }
}
