package com.lima.study_alone.inflearn.springwithkim.advance.app.v1;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

  private final OrderServiceV1 orderService;
  private final HelloTraceV1 trace;

  // http://localhost:8080/v1/request?itemId=ff
  @GetMapping("/v1/request")
  public String request(String itemId) {

    TraceStatus status = null;
    try {
      status = trace.begin("OrderController.request()");
      orderService.orderItem(itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
