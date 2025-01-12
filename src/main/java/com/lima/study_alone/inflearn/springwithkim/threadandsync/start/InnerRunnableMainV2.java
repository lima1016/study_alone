package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

import static com.lima.study_alone.inflearn.springwithkim.threadandsync.util.MyLogger.log;

public class InnerRunnableMainV2 {
  public static void main(String[] args) {
    log("main() start");
    // 오리지날
//    Runnable runnable = new Runnable() {
//      @Override
//      public void run() {
//        log("run()");
//      }
//    };
    Runnable runnable = () -> log("run()");
    Thread thread = new Thread(runnable);
    thread.start();
    log("main() end");
  }
}
