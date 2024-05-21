package com.lima.study_alone.springwithkim.advance.trace.hellotrace;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.hellotrace.HelloTraceV1;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

  @Test
  void begin_end() {
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");
    trace.end(status);
  }

  @Test
  void begin_exception() {
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");
    trace.exception(status, new NullPointerException());
  }
}