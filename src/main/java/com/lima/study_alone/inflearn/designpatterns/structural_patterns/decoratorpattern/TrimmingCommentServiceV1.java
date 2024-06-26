package com.lima.study_alone.inflearn.designpatterns.structural_patterns.decoratorpattern;

public class TrimmingCommentServiceV1 extends CommentServiceV1 {

  @Override
  public void addComment(String comment) {
    super.addComment(trim(comment));
  }

  private String trim(String comment) {
    return comment.replace("...", "");
  }

}
