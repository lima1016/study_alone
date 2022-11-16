package com.lima.study_alone.designpatterns.structural_patterns.decoratorpattern;

public class SpamFilteringCommentServiceV1 extends CommentServiceV1{
  @Override
  public void addComment(String comment) {
    boolean isSpam = isSpam(comment);
    if (!isSpam) {
      super.addComment(comment);
    }
  }

  private boolean isSpam(String comment) {
    return comment.contains("졸");
  }
}
