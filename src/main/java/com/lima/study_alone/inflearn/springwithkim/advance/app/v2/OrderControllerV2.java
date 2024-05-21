package com.lima.study_alone.inflearn.springwithkim.advance.app.v2;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.hellotrace.HelloTraceV2;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

  private final OrderServiceV2 orderService;
  private final HelloTraceV2 trace;

  // http://localhost:8080/v1/request?itemId=ff
  @GetMapping("/v2/request")
  public String request(String itemId) {

    TraceStatus status = null;
    try {
      status = trace.begin("OrderController.request()");
      orderService.orderItem(status.getTraceId(), itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
