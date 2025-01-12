package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

public class HelloThreadMain {

  public static void main(String[] args) {
    // java 를 처음 시작하면 main 이라는 이름으로 쓰레드 시작.
    System.out.println(Thread.currentThread().getName() + " : main() start");

    HelloThread helloThread = new HelloThread();
    System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
    // Thread 시작시 꼭 start()로 시작해야함.
    helloThread.start();
    System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

    System.out.println(Thread.currentThread().getName() + " : main() end");

    // main : main() start
    // main: start() 호출 전
    // main: start() 호출 후
    // main : main() end
    // Thread-0 : run() // 이 부분의 실행 순서는 보장 되지 않는다.
  }
}
