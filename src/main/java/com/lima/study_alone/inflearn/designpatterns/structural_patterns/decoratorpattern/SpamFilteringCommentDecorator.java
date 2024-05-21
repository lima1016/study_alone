package com.lima.study_alone.inflearn.designpatterns.structural_patterns.decoratorpattern;

public class SpamFilteringCommentDecorator extends CommentDecorator{
  public SpamFilteringCommentDecorator(CommentServiceV2 commentService) {
    super(commentService);
  }

  @Override
  public void addComment(String comment) {
    if (isNotSpam(comment)) {
      super.addComment(comment);
    }
  }

  private boolean isNotSpam(String comment) {
    return !comment.contains("졸");
  }
}
