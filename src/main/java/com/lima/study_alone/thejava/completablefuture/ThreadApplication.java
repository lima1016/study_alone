package com.lima.study_alone.thejava.completablefuture;

/*
Concurrent 소프트웨어
  ● 동시에 여러 작업을 할 수 있는 소프트웨어
  ● 예) 웹 브라우저로 유튜브를 보면서 키보드로 문서에 타이핑을 할 수 있다.
  ● 예) 녹화를 하면서 인텔리J로 코딩을 하고 워드에 적어둔 문서를 보거나 수정할 수 있다.
자바 멀티쓰레드 프로그래밍
  ● Thread / Runnable
 */
public class ThreadApplication {
  public static void main(String[] args) throws InterruptedException {

    // **첫번째 예제**
    // 자바에서 지원하는 컨커런트 프로그래밍
    //    ● 멀티프로세싱 (ProcessBuilder)
    //    ● 멀티쓰레드
//    MyThread myThread = new MyThread();
//    myThread.start();
    // 스레드의 순서는 보장을 못해 그래서 run() 먼저 실행될수도 있고 main 스레드가 먼저 실행될수도있음.

    // **두번째 예제**
//    Thread thread = new Thread(() -> {
//      try {
    // sleep() 을 만나면 다른 스레드가 먼저 일을함.
    //   ● 현재 쓰레드 멈춰두기 (sleep): 다른 쓰레드가 처리할 수 있도록 기회를 주지만 그렇다고
    //  락을 놔주진 않는다. (잘못하면 데드락 걸릴 수 있겠죠.)
//        Thread.sleep(1000L);
//      } catch (InterruptedException e) { // 자는 동안에 이 스레드를 깨우면 catch 로 들어옴.
//        e.printStackTrace();
//      }
//      System.out.println("Thread: " + Thread.currentThread().getName());

//    });
//    thread.start();
//    System.out.println("Hello: " + Thread.currentThread().getName());

    // **세번째 예제**
    //   ● 다른 쓰레드 깨우기 (interupt): 다른 쓰레드를 깨워서 interruptedExeption을 발생 시킨다.
    //  그 에러가 발생했을 때 할 일은 코딩하기 나름. 종료 시킬 수도 있고 계속 하던 일 할 수도있고.
    Thread thread = new Thread(() -> {
      // **다섯번째 예제**
      // while 문 주석처리함
//      while (true) {
        System.out.println("Thread: " + Thread.currentThread().getName());
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          System.out.println("Exit!");
          // ** 네번째 예제**
          // return; 을 없애면 에러를 던지거나!
          throw new IllegalStateException(e);
//          return;
        }
//      }
    });
    thread.start();
    System.out.println("Hello: " + Thread.currentThread().getName());
    // **다섯번째 예제**
    //   ● 다른 쓰레드 기다리기 (join): 다른 쓰레드가 끝날 때까지 기다린다.
    thread.join();
    // 기다린 후 밑에 문구 출력
    System.out.println(thread + " is finished.");
    // **세번째 예제**
    // 3초 정도 있다가 깨워보자 interupt
//    Thread.sleep(3000L);
//    thread.interrupt();
  }

  // **첫번째 예제**
//  static class MyThread extends Thread {
//    @Override
//    public void run() {
//      System.out.println("Hello: " + Thread.currentThread().getName());
//    }
//  }
}
