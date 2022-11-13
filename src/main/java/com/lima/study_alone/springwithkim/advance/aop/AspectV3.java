package com.lima.study_alone.springwithkim.advance.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 스프링 AOP 구현3 - 어드바이스 추가
 * 이번에는 조금 복잡한 예제를 만들어보자.
 * 앞서 로그를 출력하는 기능에 추가로 트랜잭션을 적용하는 코드도 추가해보자. 여기서는 진짜 트랜잭션을
 * 실행하는 것은 아니다. 기능이 동작한 것 처럼 로그만 남기겠다.
 * 트랜잭션 기능은 보통 다음과 같이 동작한다.
 * 핵심 로직 실행 직전에 트랜잭션을 시작
 * 핵심 로직 실행
 * 핵심 로직 실행에 문제가 없으면 커밋
 * 핵심 로직 실행에 예외가 발생하면 롤백
 *
 * @Around("allOrder() && allService()")
 * 포인트컷은 이렇게 조합할 수 있다. && (AND), || (OR), ! (NOT) 3가지 조합이 가능하다.
 * hello.aop.order 패키지와 하위 패키지 이면서 타입 이름 패턴이 *Service 인 것을 대상으로 한다.
 * 결과적으로 doTransaction() 어드바이스는 OrderService 에만 적용된다.
 * doLog() 어드바이스는 OrderService , OrderRepository 에 모두 적용된다.
 * 포인트것이 적용된 AOP 결과는 다음과 같다.
 * orderService : doLog() , doTransaction() 어드바이스 적용
 * orderRepository : doLog() 어드바이스 적용
 */
@Slf4j
@Aspect
public class AspectV3 {

  @Pointcut("execution(* com.lima.study_alone.springwithkim.advance.aop.order..*(..))") //pointcut expression
  private void allOrder(){} //pointcut signature

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  private void allService(){}


  @Around("allOrder()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
    return joinPoint.proceed();
  }

  // com.lima.study_alone.springwithkim.advance.aop.order 패키지와 하위 패키지ㅣ 이면서 클래스 이름 패턴이 *Service
  @Around("allOrder() && allService()")
  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
      Object result = joinPoint.proceed();
      log.info("[트랜잭션 컴밋] {}", joinPoint.getSignature());
      return result;
    } catch (Exception e) {
      log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
      throw e;
    } finally { // 리소스를 정리할때 finally를 씀
      log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
    }
  }
}
