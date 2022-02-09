package com.lima.study_alone.thejava.optional;

import java.util.Optional;

public class OnlineClass {

  private Integer id;
  private String title;
  private boolean closed;

  public Progress progress;

  public OnlineClass(Integer id, String title, boolean closed) {
    this.id = id;
    this.title = title;
    this.closed = closed;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  // **두번째 예제**
//  public Progress getProgress() {
  // null 체크 하는 방법 1: 좋은 습관은 아님!
//    if (this.progress == null) {
//      throw new IllegalStateException();
//    }
//    return progress;
//  }

  // **세번째 예제**
  //   ● 리턴값으로만 쓰기를 권장한다. (메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드타입으로 쓰지 말자.)
  public Optional<Progress> getProgress() {
    // .of 를 쓸떄는 뒤에오는 값이 무조건 null이 아닐꺼라는 것을 가정하는 것.
    //   ● Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
    // return null;
    // 대신 Optional.empty(); 가 있음.
    return Optional.ofNullable(progress);
  }

  // **네번째 예제**
  // 메소드 매개변수 타입으로 optional 을 쓰지므르~
//  public void setProgress(Progress progress) {
//    this.progress = progress;
//  }
  // 추천하지 않아. null 체크 또해야함
  //    if (progress != null) {
//      progress.ifPresent(p -> this.progress = p);
//    }
  public void setProgress(Optional<Progress> progress) {
    // 위험해 메소드 호출할때 null 을 호출할 수도 있음.
    // spring_boot.setProgress(null); 일 경우
    progress.ifPresent(p -> this.progress = p);
  }
}
