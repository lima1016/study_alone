package com.lima.study_alone.inflearn.springwithkim.advance.app.v2;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceId;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.hellotrace.HelloTraceV2;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
  private final HelloTraceV2 trace;
  public void save(TraceId traceId, String itemId) {
    TraceStatus status = null;
    try {
      status = trace.beginSync(traceId, "OrderRepository.save()");
      //저장 로직
      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생!");
      }
      sleep(1000);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
