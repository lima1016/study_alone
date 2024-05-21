package com.lima.study_alone.springwithkim.advance.trace.logtrace;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.logtrace.FieldLogTrace;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

  FieldLogTrace trace = new FieldLogTrace();
  @Test
  void begin_end_level2() {
    TraceStatus status1 = trace.begin("hello1");
    TraceStatus status2 = trace.begin("hello2");
    trace.end(status2);
    trace.end(status1);
  }

  @Test
  void begin_exception_level2() {
    TraceStatus status1 = trace.begin("hello1");
    TraceStatus status2 = trace.begin("hello2");
    TraceStatus status3 = trace.begin("hello3");
    trace.exception(status3, new IllegalStateException());
    trace.exception(status2, new IllegalStateException());
    trace.exception(status1, new IllegalStateException());
  }
}