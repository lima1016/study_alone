package com.lima.study_alone.inflearn.springwithkim.advance.trace.logtrace;

import com.lima.study_alone.inflearn.springwithkim.advance.trace.TraceStatus;

// traceIdHolder 필드를 사용한 덕분에 파라미터 추가 없는 깔끔한 로그 추적기를 완성했다. 이제 실제
// 서비스에 배포한다고 가정해보자. => 문제점은?
// 나의 답 => 동시 접속시 traceId가 null 일때만 new traceId 했기때문에 if 로직을 끝나지도 않았을때 호출하면 두 호출 다 traceId가 null이기때문에 같은 id가 나올듯?
// 답 => 동시성 문제
//  사실 이 문제는 동시성 문제이다.
//  FieldLogTrace 는 싱글톤으로 등록된 스프링 빈이다. 이 객체의 인스턴스가 애플리케이션에 딱 1
//  존재한다는 뜻이다. 이렇게 하나만 있는 인스턴스의 FieldLogTrace.traceIdHolder 필드를 여러
//  쓰레드가 동시에 접근하기 때문에 문제가 발생한다.
public interface LogTrace {
  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);
}
