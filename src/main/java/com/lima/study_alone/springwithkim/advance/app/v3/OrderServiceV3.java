package com.lima.study_alone.springwithkim.advance.app.v3;

import com.lima.study_alone.springwithkim.advance.trace.TraceId;
import com.lima.study_alone.springwithkim.advance.trace.TraceStatus;
import com.lima.study_alone.springwithkim.advance.trace.hellotrace.HelloTraceV2;
import com.lima.study_alone.springwithkim.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
  private final OrderRepositoryV3 orderRepository;
  private final LogTrace trace;

  public void orderItem(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin( "OrderService.orderItem()");
      orderRepository.save(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
