package com.lima.study_alone.inflearn.designpatterns.structural_patterns.decoratorpattern;

public class ClientV1 {
  // 상속으로만 해결하려고할때 문제점
  private CommentServiceV1 commentService;
  private boolean enableSpamFilter;
  private boolean isEnableTrimming;

  public ClientV1(CommentServiceV1 commentService) {
    this.commentService = commentService;
  }

  private void writeComment(String comment) {
    commentService.addComment(comment);
  }

  public static void main(String[] args) {
//    ClientV1 client = new ClientV1(new CommentServiceV1());
//    ClientV1 client = new ClientV1(new TrimmingCommentServiceV1());
    ClientV1 client = new ClientV1(new SpamFilteringCommentServiceV1());
    client.writeComment("오징어게임...");
    client.writeComment("졸잼");
  }
}
