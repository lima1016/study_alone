package com.lima.study_alone.inflearn.springwithkim.advance.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 스프링 AOP 구현4 - 포인트컷 참조
 * 다음과 같이 포인트컷을 공용으로 사용하기 위해 별도의 외부 클래스에 모아두어도 된다. 참고로 외부에서
 * 호출할 때는 포인트컷의 접근 제어자를 public 으로 열어두어야 한다.
 */
public class Pointcuts {
  // com.lima.study_alone.springwithkim.advance.aop.order 패키지와 하위 패키지
  @Pointcut("execution(* com.lima.study_alone.springwithkim.advance.aop.order..*(..))")
  public void allOrder(){}

  // 타입 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  public void allService(){}

  // allOrder && allService
  @Pointcut("allOrder() && allService()")
  public void orderAndService(){}
}
