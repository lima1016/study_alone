package com.lima.study_alone.inflearn.designpatterns.structural_patterns.decoratorpattern;

public class ClientV2 {
  // 상속으로만 해결하려고할때 문제점
  private CommentServiceV2 commentService;

  public ClientV2(CommentServiceV2 commentService) {
    this.commentService = commentService;
  }

  public void writeComment(String comment) {
    commentService.addComment(comment);
  }

  // App 이라고 가정하고
  private static boolean isEnabledSpamFilter = false;
  private static boolean isEnabledTrimming = true;

  public static void main(String[] args) {
    CommentServiceV2 commentService = new DefaultCommentServiceV2();

    if (isEnabledSpamFilter) {
      commentService = new SpamFilteringCommentDecorator(commentService);
    }

    if (isEnabledTrimming) {
      commentService = new TrimmingCommentDecorator(commentService);
    }
    ClientV2 client = new ClientV2(commentService);
    client.writeComment("오징어게임...");
    client.writeComment("졸잼");
  }
}
