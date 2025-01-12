package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

import static com.lima.study_alone.inflearn.springwithkim.threadandsync.util.MyLogger.log;

public class ManyThreadMainV1 {
  public static void main(String[] args) {
    log("main() start");
    HelloRunnable runnable = new HelloRunnable();
    Thread thread1 = new Thread(runnable);
    thread1.start();
    Thread thread2 = new Thread(runnable);
    thread2.start();
    Thread thread3 = new Thread(runnable);
    thread3.start();
    log("main() end");
    // 실행 결과는 다를 수 있다. 스레드의 실행 순서는 보장되지 않는다.
  }
}
