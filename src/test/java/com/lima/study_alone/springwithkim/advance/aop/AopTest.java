package com.lima.study_alone.springwithkim.advance.aop;

import com.lima.study_alone.inflearn.springwithkim.advance.aop.AspectV6Advice;
import com.lima.study_alone.inflearn.springwithkim.advance.aop.order.OrderRepository;
import com.lima.study_alone.inflearn.springwithkim.advance.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
@Slf4j
/**
 * @Aspect 는 애스펙트라는 표식이지 컴포넌트 스캔이 되는 것은 아니다. 따라서 AspectV1 를 AOP로
 * 사용하려면 스프링 빈으로 등록해야 한다.
 * 스프링 빈으로 등록하는 방법은 다음과 같다.
 * @Bean 을 사용해서 직접 등록
 * @Component 컴포넌트 스캔을 사용해서 자동 등록
 * @Import 주로 설정 파일을 추가할 때 사용( @Configuration )
 * @Import 는 주로 설정 파일을 추가할 때 사용하지만, 이 기능으로 스프링 빈도 등록할 수 있다.
 * 테스트에서는 버전을 올려가면서 변경할 예정이어서 간단하게 @Import 기능을 사용하자.
 */
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)

@SpringBootTest
public class AopTest {
  @Autowired
  OrderService orderService;
  @Autowired
  OrderRepository orderRepository;

  /**
   * AopUtils.isAopProxy(...) 을 통해서 AOP 프록시가 적용 되었는지 확인할 수 있다. 현재 AOP 관련
   * 코드를 작성하지 않았으므로 프록시가 적용되지 않고, 결과도 false 를 반환해야 정상이다.
   */
  @Test
  void aopInfo() {
    log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
    log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
  }
  @Test
  void success() {
    orderService.orderItem("itemA");
  }

  @Test
  void exception() {
    // 예외가 터져야 성공함
    assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
  }
}