package com.lima.study_alone.inflearn.springwithkim.threadandsync.start;

/*
스택 영역은 더 정확히는 각 스레드별로 하나의 실행 스택이 생성된다. 따라서 스레드 수 만큼 스택이 생성
된다. 지금은 스레드를 1개만 사용하므로 스택도 하나이다. 이후 스레드를 추가할 것인데, 그러면 스택도 스레드
수 만큼 증가한다
 */
public class HelloThread extends Thread{

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " : run()");
  }

}
