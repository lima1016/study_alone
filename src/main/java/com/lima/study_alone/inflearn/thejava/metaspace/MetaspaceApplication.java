package com.lima.study_alone.inflearn.thejava.metaspace;

/*
JVM의 여러 메모리 영역 중에 PermGen 메모리 영역이 없어지고 Metaspace 영역이 생겼다.
PermGen
  ● permanent generation, 클래스 메타데이터를 담는 곳.
  ● Heap 영역에 속함.
  ● 기본값으로 제한된 크기를 가지고 있음.
  ● -XX:PermSize=N, PermGen 초기 사이즈 설정
  ● -XX:MaxPermSize=N, PermGen 최대 사이즈 설정
Metaspace
  ● 클래스 메타데이터를 담는 곳.
  ● Heap 영역이 아니라, Native 메모리 영역이다.
  ● 기본값으로 제한된 크기를 가지고 있지 않다. (필요한 만큼 계속 늘어난다.)
  ● 자바 8부터는 PermGen 관련 java 옵션은 무시한다.
  ● -XX:MetaspaceSize=N, Metaspace 초기 사이즈 설정.
  ● -XX:MaxMetaspaceSize=N, Metaspace 최대 사이즈 설정.
 */
public class MetaspaceApplication {
}
