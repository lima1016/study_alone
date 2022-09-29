package com.lima.study_alone.designpatterns.decoratorpattern;

public class DefaultCommentServiceV2 implements CommentServiceV2{
  @Override
  public void addComment(String comment) {
    System.out.println(comment);
  }
}
