package com.lima.study_alone.springwithkim.advance.app.v3;

import com.lima.study_alone.springwithkim.advance.trace.TraceId;
import com.lima.study_alone.springwithkim.advance.trace.TraceStatus;
import com.lima.study_alone.springwithkim.advance.trace.hellotrace.HelloTraceV2;
import com.lima.study_alone.springwithkim.advance.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
  private final LogTrace trace;
  public void save(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepository.save()");
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
