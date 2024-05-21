package com.lima.study_alone.inflearn.springwithkim.advance.aop;

/**
 * 컴파일 시점: 실제 대상 코드에 애스팩트를 통한 부가 기능 호출 코드가 포함된다. AspectJ를 직접
 * 사용해야 한다.
 * 클래스 로딩 시점: 실제 대상 코드에 애스팩트를 통한 부가 기능 호출 코드가 포함된다. AspectJ를 직접
 * 사용해야 한다.
 * 런타임 시점: 실제 대상 코드는 그대로 유지된다. 대신에 프록시를 통해 부가 기능이 적용된다. 따라서 항상
 * 프록시를 통해야 부가 기능을 사용할 수 있다. 스프링 AOP는 이 방식을 사용한다.
 *
 * AOP 적용 위치
 * AOP는 지금까지 학습한 메서드 실행 위치 뿐만 아니라 다음과 같은 다양한 위치에 적용할 수 있다.
 * 적용 가능 지점(조인 포인트): 생성자, 필드 값 접근, static 메서드 접근, 메서드 실행
 * 이렇게 AOP를 적용할 수 있는 지점을 조인 포인트(Join point)라 한다.
 * AspectJ를 사용해서 컴파일 시점과 클래스 로딩 시점에 적용하는 AOP는 바이트코드를 실제 조작하기
 * 때문에 해당 기능을 모든 지점에 다 적용할 수 있다.
 * 프록시 방식을 사용하는 스프링 AOP는 메서드 실행 지점에만 AOP를 적용할 수 있다.
 * 프록시는 메서드 오버라이딩 개념으로 동작한다. 따라서 생성자나 static 메서드, 필드 값 접근에는
 * 프록시 개념이 적용될 수 없다.
 * 프록시를 사용하는 스프링 AOP의 조인 포인트는 메서드 실행으로 제한된다.
 * 프록시 방식을 사용하는 스프링 AOP는 스프링 컨테이너가 관리할 수 있는 스프링 빈에만 AOP를 적용할
 * 수 있다
 *
 * 조인 포인트(Join point)
 * 어드바이스가 적용될 수 있는 위치, 메소드 실행, 생성자 호출, 필드 값 접근, static 메서드 접근 같은
 * 프로그램 실행 중 지점
 * 조인 포인트는 추상적인 개념이다. AOP를 적용할 수 있는 모든 지점이라 생각하면 된다.
 * 스프링 AOP는 프록시 방식을 사용하므로 조인 포인트는 항상 메소드 실행 지점으로 제한된다.
 * 포인트컷(Pointcut)
 * 조인 포인트 중에서 어드바이스가 적용될 위치를 선별하는 기능
 * 주로 AspectJ 표현식을 사용해서 지정
 * 프록시를 사용하는 스프링 AOP는 메서드 실행 지점만 포인트컷으로 선별 가능
 * 타켓(Target)
 * 어드바이스를 받는 객체, 포인트컷으로 결정
 * 어드바이스(Advice)
 * 부가 기능
 * 특정 조인 포인트에서 Aspect에 의해 취해지는 조치
 * Around(주변), Before(전), After(후)와 같은 다양한 종류의 어드바이스가 있음
 * 애스펙트(Aspect)
 * 어드바이스 + 포인트컷을 모듈화 한 것
 * @Aspect 를 생각하면 됨
 * 여러 어드바이스와 포인트 컷이 함께 존재
 * 어드바이저(Advisor)
 * 하나의 어드바이스와 하나의 포인트 컷으로 구성
 * 스프링 AOP에서만 사용되는 특별한 용어
 * 위빙(Weaving)
 * 포인트컷으로 결정한 타켓의 조인 포인트에 어드바이스를 적용하는 것
 * 위빙을 통해 핵심 기능 코드에 영향을 주지 않고 부가 기능을 추가 할 수 있음
 * AOP 적용을 위해 애스펙트를 객체에 연결한 상태
 * 컴파일 타임(AspectJ compiler)
 * 로드 타임
 * 런타임, 스프링 AOP는 런타임, 프록시 방식
 * AOP 프록시
 * AOP 기능을 구현하기 위해 만든 프록시 객체, 스프링에서 AOP 프록시는 JDK 동적 프록시 또는
 * CGLIB 프록시이다.
 */
public class Aop {
  // 메소드, 클래스 시간 측정 참고 블로그
  // https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=idtong&logNo=130088240447
  // https://velog.io/@dhk22/Spring-AOP-%EA%B0%84%EB%8B%A8%ED%95%9C-AOP-%EC%A0%81%EC%9A%A9-%EC%98%88%EC%A0%9C-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%8B%A4%ED%96%89%EC%8B%9C%EA%B0%84-%EC%B8%A1%EC%A0%95

  /**
   * AOP 적용 전
   * 클라이언트 orderService.orderItem() orderRepository.save()
   * AOP 적용 후
   * 클라이언트 [ doLog() doTransaction() ] orderService.orderItem()
   * [ doLog() ] orderRepository.save()
   * orderService 에는 doLog() , doTransaction() 두가지 어드바이스가 적용되어 있고,
   * orderRepository 에는 doLog() 하나의 어드바이스만 적용된 것을 확인할 수 있다.
   */
}
