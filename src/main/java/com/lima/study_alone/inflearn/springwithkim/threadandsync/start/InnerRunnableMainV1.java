package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

import static com.lima.study_alone.inflearn.springwithkim.threadandsync.util.MyLogger.log;

public class InnerRunnableMainV1 {
  public static void main(String[] args) {
    log("main() start");
    Runnable runnable = new MyRunnable();
    Thread thread = new Thread(runnable);
    thread.start();
    log("main() end");
  }
  static class MyRunnable implements Runnable {
    @Override
    public void run() {
      log("run()");
    }
  }
}
