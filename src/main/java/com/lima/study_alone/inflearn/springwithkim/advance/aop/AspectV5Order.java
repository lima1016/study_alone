package com.lima.study_alone.inflearn.springwithkim.advance.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/**
 * 스프링 AOP 구현5 - 어드바이스 순서
 * 어드바이스는 기본적으로 순서를 보장하지 않는다. 순서를 지정하고 싶으면 @Aspect 적용 단위로
 * org.springframework.core.annotation.@Order 애노테이션을 적용해야 한다. 문제는 이것을
 * 어드바이스 단위가 아니라 클래스 단위로 적용할 수 있다는 점이다. 그래서 지금처럼 하나의 애스펙트에
 * 여러 어드바이스가 있으면 순서를 보장 받을 수 없다. 따라서 애스펙트를 별도의 클래스로 분리해야 한다.
 * 현재 로그를 남기는 순서가 아마도 [ doLog() doTransaction() ] 이 순서로 남을 것이다.
 * (참고로 이 순서로 실행되지 않는 분도 있을 수 있다. JVM이나 실행 환경에 따라 달라질 수도 있다.)
 * 로그를 남기는 순서를 바꾸어서 [ doTransaction() doLog() ] 트랜잭션이 먼저 처리되고, 이후에
 * 로그가 남도록 변경해보자.
 *
 * 하나의 애스펙트 안에 있던 어드바이스를 LogAspect , TxAspect 애스펙트로 각각 분리했다. 그리고 각
 * 애스펙트에 @Order 애노테이션을 통해 실행 순서를 적용했다. 참고로 숫자가 작을 수록 먼저 실행된다.
 */

@Slf4j
public class AspectV5Order {

  @Aspect
  @Order(2)
  public static class LogAspect {
    @Around("com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
      return joinPoint.proceed();
    }
  }

  @Aspect
  @Order(1)
  public static class TxAspect {
    // com.lima.study_alone.springwithkim.advance.aop.order 패키지와 하위 패키지ㅣ 이면서 클래스 이름 패턴이 *Service
    @Around("com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.orderAndService()")
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
}
