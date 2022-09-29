package com.lima.study_alone.springwithkim.advance.trace;

import java.util.UUID;

public class TraceId {
  private String id;
  private int level;

  public TraceId() {
    this.id = createId();
    this.level = 0;  }

  private TraceId(String id, int level) {
    this.id = id;
    this.level = level;
  }

  private String createId() {
    return UUID.randomUUID().toString().substring(0, 8);
  }

  public TraceId createNextId() {
    // level 증가를 위함
    // [796bccd9] OrderController.request() //트랜잭션ID:796bccd9, level:0
    // [796bccd9] |-->OrderService.orderItem() //트랜잭션ID:796bccd9, level:1
    // [796bccd9] | |-->OrderRepository.save()//트랜잭션ID:796bccd9, level:2
    return new TraceId(id, level + 1);
  }

  public TraceId createPreviousId() {
    return new TraceId(id, level - 1);
  }

  public boolean isFirstLevel() {
    return level == 0;
  }

  public String getId() {
    return id;
  }

  public int getLevel() {
    return level;
  }
}
