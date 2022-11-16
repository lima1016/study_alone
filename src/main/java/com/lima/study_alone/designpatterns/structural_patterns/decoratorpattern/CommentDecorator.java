package com.lima.study_alone.designpatterns.structural_patterns.decoratorpattern;

public class CommentDecorator implements CommentServiceV2{
  private CommentServiceV2 commentService;

  public CommentDecorator(CommentServiceV2 commentService) {
    this.commentService = commentService;
  }

  @Override
  public void addComment(String comment) {
    commentService.addComment(comment);
  }
}
