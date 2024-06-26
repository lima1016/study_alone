package com.lima.study_alone.inflearn.springwithkim.advance.trace.hellotrace;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceId;
import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {
  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  // 처음 호출
  public TraceStatus begin(String message) {
    TraceId traceId = new TraceId();
    Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
        traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  // 두번째 부터 호출
  public TraceStatus beginSync(TraceId beforeTraceId, String message) {
    // trace id는 유지하면서 level 만 증가
    TraceId nextId = beforeTraceId.createNextId();
    Long startTimeMs = System.currentTimeMillis();

    log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX,
        nextId.getLevel()), message);
    return new TraceStatus(nextId, startTimeMs, message);
  }

  public void end(TraceStatus status) {
    complete(status, null);
  }

  public void exception(TraceStatus status, Exception e) {
    complete(status, e);
  }

  private void complete(TraceStatus status, Exception e) {
    Long stopTimeMs = System.currentTimeMillis();
    long resultTimeMs = stopTimeMs - status.getStartTimeMs();
    TraceId traceId = status.getTraceId();
    if (e == null) {
      log.info("[" + traceId.getId() + "] " + addSpace(COMPLETE_PREFIX, traceId.getLevel()) + status.getMessage() + " time=" + resultTimeMs + "ms");
    } else {
      log.info("[" + traceId.getId() + "] " + addSpace(EX_PREFIX, traceId.getLevel()) + status.getMessage() + " time=" + resultTimeMs + "ms" + " ex=" + e);
    }
  }

  private static String addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
      sb.append( (i == level - 1) ? "|" + prefix : "| ");
    }
    return sb.toString();
  }
}
