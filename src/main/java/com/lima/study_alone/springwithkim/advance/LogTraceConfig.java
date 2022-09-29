package com.lima.study_alone.springwithkim.advance;

import com.lima.study_alone.springwithkim.advance.trace.logtrace.FieldLogTrace;
import com.lima.study_alone.springwithkim.advance.trace.logtrace.LogTrace;
import com.lima.study_alone.springwithkim.advance.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
//    return new FieldLogTrace();
    return new ThreadLocalLogTrace();
  }
}
