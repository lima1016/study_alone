package com.lima.study_alone.inflearn.springwithkim.advance.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.util.StopWatch;

/**
 * 스프링 AOP 구현6 - 어드바이스 종류
 * 어드바이스 종류
 *
 * @Around : 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환,
 * 예외 변환 등이 가능
 * @Before : 조인 포인트 실행 이전에 실행
 * @AfterReturning : 조인 포인트가 정상 완료후 실행
 * @AfterThrowing : 메서드가 예외를 던지는 경우 실행
 * @After : 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)
 */
@Slf4j
@Aspect
public class AspectV6Advice {

  // com.lima.study_alone.springwithkim.advance.aop.order 패키지와 하위 패키지ㅣ 이면서 클래스 이름 패턴이 *Service
  @Around("com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.orderAndService()")
  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      // @Before
      log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
      Object result = joinPoint.proceed();
      // @AfterReturning
      log.info("[트랜잭션 컴밋] {}", joinPoint.getSignature());
      return result;
    } catch (Exception e) {
      // @AfterThrowing
      log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
      throw e;
    } finally { // 리소스를 정리할때 finally를 씀
      // @Afte
      log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
    }
  }

  /**
   * @Before
   * 조인 포인트 실행 전
   * @Around 와 다르게 작업 흐름을 변경할 수는 없다.
   * @Around 는 ProceedingJoinPoint.proceed() 를 호출해야 다음 대상이 호출된다. 만약 호출하지
   * 않으면 다음 대상이 호출되지 않는다. 반면에 @Before 는 ProceedingJoinPoint.proceed() 자체를
   * 사용하지 않는다. 메서드 종료시 자동으로 다음 타켓이 호출된다. 물론 예외가 발생하면 다음 코드가
   * 호출되지는 않는다
   */
  @Before("com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.orderAndService()")
  public void doBefore(JoinPoint joinPoint) {
    log.info("[before] {}", joinPoint.getSignature());
  }

  /**
   * @AfterReturning
   * 메서드 실행이 정상적으로 반환될 때 실행
   * returning 속성에 사용된 이름은 어드바이스 메서드의 매개변수 이름과 일치해야 한다.
   * returning 절에 지정된 타입의 값을 반환하는 메서드만 대상으로 실행한다. (부모 타입을 지정하면 모든
   * 자식 타입은 인정된다.)
   * @Around 와 다르게 반환되는 객체를 변경할 수는 없다. 반환 객체를 변경하려면 @Around 를 사용해야
   * 한다. 참고로 반환 객체를 조작할 수 는 있다.
   */
  @AfterReturning(value = "com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.orderAndService()", returning = "result")
  public void doReturn(JoinPoint joinPoint, Object result) {
    log.info("[return] {} return={}", joinPoint.getSignature(), result);
  }

  /**
   * @AfterThrowing
   * 메서드 실행이 예외를 던져서 종료될 때 실행
   * throwing 속성에 사용된 이름은 어드바이스 메서드의 매개변수 이름과 일치해야 한다.
   * throwing 절에 지정된 타입과 맞은 예외를 대상으로 실행한다. (부모 타입을 지정하면 모든 자식 타입은
   * 인정된다.)
   */
  @AfterThrowing(value = "com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.orderAndService()", throwing = "ex")
  public void doThrowing(JoinPoint joinPoint, Exception ex) {
    log.info("[ex] {} message={}", joinPoint.getSignature(), ex.getMessage());
  }

  /**
   * @After
   * 메서드 실행이 종료되면 실행된다. (finally를 생각하면 된다.)
   * 정상 및 예외 반환 조건을 모두 처리한다.
   * 일반적으로 리소스를 해제하는 데 사용한다.
   */
  @After(value = "com.lima.study_alone.inflearn.springwithkim.advance.aop.Pointcuts.orderAndService()")
  public void doAfter(JoinPoint joinPoint) {
    log.info("[after] {}", joinPoint.getSignature());
  }

  /**
   * @Around
   * 메서드의 실행의 주변에서 실행된다. 메서드 실행 전후에 작업을 수행한다.
   * 가장 강력한 어드바이스
   * 조인 포인트 실행 여부 선택 joinPoint.proceed() 호출 여부 선택
   * 전달 값 변환: joinPoint.proceed(args[])
   * 반환 값 변환
   * 예외 변환
   * 트랜잭션 처럼 try ~ catch~ finally 모두 들어가는 구문 처리 가능
   * 어드바이스의 첫 번째 파라미터는 ProceedingJoinPoint 를 사용해야 한다.
   * proceed() 를 통해 대상을 실행한다.
   * proceed() 를 여러번 실행할 수도 있음(재시도)
   */

  /**
   * 참고 정보 획득
   * 모든 어드바이스는 org.aspectj.lang.JoinPoint 를 첫번째 파라미터에 사용할 수 있다. (생략해도
   * 된다.)
   * 단 @Around 는 ProceedingJoinPoint 을 사용해야 한다.
   * 참고로 ProceedingJoinPoint 는 org.aspectj.lang.JoinPoint 의 하위 타입이다
   *
   * JoinPoint 인터페이스의 주요 기능
   * getArgs() : 메서드 인수를 반환합니다.
   * getThis() : 프록시 객체를 반환합니다.
   * getTarget() : 대상 객체를 반환합니다.
   * getSignature() : 조언되는 메서드에 대한 설명을 반환합니다.
   * toString() : 조언되는 방법에 대한 유용한 설명을 인쇄합니다.
   * ProceedingJoinPoint 인터페이스의 주요 기능
   * proceed() : 다음 어드바이스나 타켓을 호출한다
   */
}
