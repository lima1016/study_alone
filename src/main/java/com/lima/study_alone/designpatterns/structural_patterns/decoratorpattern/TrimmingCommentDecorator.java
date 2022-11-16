package com.lima.study_alone.designpatterns.structural_patterns.decoratorpattern;

public class TrimmingCommentDecorator extends CommentDecorator {

  public TrimmingCommentDecorator(CommentServiceV2 commentService) {
    super(commentService);
  }

  @Override
  public void addComment(String comment) {
    super.addComment(trim(comment));
  }

  private String trim(String comment) {
    return comment.replace("...", "");
  }
}
