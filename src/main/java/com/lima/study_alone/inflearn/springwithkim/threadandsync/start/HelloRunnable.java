package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

/*
Thread 상속 vs Runnable 구현
스레드 사용할 때는 Thread 를 상속 받는 방법보다 Runnable 인터페이스를 구현하는 방식을 사용하자.
두 방식이 서로 장단점이 있지만, 스레드를 생성할 때는
Thread 클래스를 상속하는 방식보다 Runnable 인터페이스를 구현하는 방식이 더 나은 선택이다
 */
public class HelloRunnable implements Runnable {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " : run()");
  }

}
