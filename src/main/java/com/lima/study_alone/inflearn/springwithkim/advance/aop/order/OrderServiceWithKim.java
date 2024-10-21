package com.lima.study_alone.inflearn.springwithkim.advance.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class OrderServiceWithKim {
  private final OrderRepositoryKim orderRepositoryKim;
  public OrderServiceWithKim(OrderRepositoryKim orderRepositoryKim) {
    this.orderRepositoryKim = orderRepositoryKim;
  }
  public void orderItem(String itemId) {
    log.info("[orderService] 실행");
    orderRepositoryKim.save(itemId);
  }
}
