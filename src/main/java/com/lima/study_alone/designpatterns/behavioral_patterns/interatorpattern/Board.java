package com.lima.study_alone.designpatterns.behavioral_patterns.interatorpattern;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {
  private List<String> post = new ArrayList<>();

  public List<String> addPost(String content) {
    post.add(content);
    return post;
  }

  // 이런 방법도 있고
  public List<String> getPost() {
    return post;
  }

  // 아예 리턴 값을 Iterator로 주는 방법도 있음
  public Iterator<String> getDefaultIterator() {
    return post.iterator();
  }
}
