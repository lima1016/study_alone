package com.lima.study_alone.inflearn.springwithkim.advance.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Around 애노테이션의 값인 execution(* hello.aop.order..*(..)) 는 포인트컷이 된다.
 * @Around 애노테이션의 메서드인 doLog 는 어드바이스( Advice )가 된다.
 * execution(* hello.aop.order..*(..)) 는 hello.aop.order 패키지와 그 하위 패키지( .. )를
 * 지정하는 AspectJ 포인트컷 표현식이다. 앞으로는 간단히 포인트컷 표현식이라 하겠다. 참고로 표인트컷
 * 표현식은 뒤에서 자세히 설명하겠다.
 * 이제 OrderService , OrderRepository 의 모든 메서드는 AOP 적용의 대상이 된다. 참고로 스프링은
 * 프록시 방식의 AOP를 사용하므로 프록시를 통하는 메서드만 적용 대상이 된다.
 *
 * > @Aspect 를 포함한 org.aspectj 패키지 관련 기능은 aspectjweaver.jar 라이브러리가 제공하는
 * 기능이다. 앞서 build.gradle 에 spring-boot-starter-aop 를 포함했는데, 이렇게 하면 스프링의
 * AOP 관련 기능과 함께 aspectjweaver.jar 도 함께 사용할 수 있게 의존 관계에 포함된다.
 * > 그런데 스프링에서는 AspectJ가 제공하는 애노테이션이나 관련 인터페이스만 사용하는 것이고, 실제
 * AspectJ가 제공하는 컴파일, 로드타임 위버 등을 사용하는 것은 아니다. 스프링은 지금까지 우리가 학습한
 * 것 처럼 프록시 방식의 AOP를 사용한다.
 */

@Slf4j
@Aspect
public class AspectV1 {
  // com.lima.study_alone.springwithkim.advance.aop.order 패키지와 하위 패키지
  @Around("execution(* com.lima.study_alone.springwithkim.advance.aop.order..*(..))")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
    return joinPoint.proceed();
  }
}