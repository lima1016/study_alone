package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

/*
스레드는 사용자(user) 스레드와 데몬(daemon) 스레드 2가지 종류로 구분할 수 있다.
사용자 스레드(non-daemon 스레드)
- 프로그램의 주요 작업을 수행한다.
- 작업이 완료될 때까지 실행된다.
- 모든 user 스레드가 종료되면 JVM도 종료된다.
데몬 스레드
- 백그라운드에서 보조적인 작업을 수행한다.
- 모든 user 스레드가 종료되면 데몬 스레드는 자동으로 종료된다.

JVM은 데몬 스레드의 실행 완료를 기다리지 않고 종료된다. 데몬 스레드가 아닌 모든 스레드가 종료되면, 자바 프로그
램도 종료된다
 */
public class DaemonThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + " : main() start");
    DaemonThread daemonThread = new DaemonThread();
    // true: 10초 기다리라고 햇는데 main이 종료 되어서 sleep이 안먹힌거임.
//    daemonThread.setDaemon(true);
    // false: 10초 기다림
    daemonThread.setDaemon(false);
    daemonThread.start();
    System.out.println(Thread.currentThread().getName() + " : main() end");
  }

  static class DaemonThread extends Thread{
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " : run()");
      try {
        Thread.sleep(10000); // 10 초간 실행
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println(Thread.currentThread().getName() + " : end");
    }
  }
}
