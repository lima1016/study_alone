package com.lima.study_alone.designpatterns.compositepattern;

import lombok.Data;

public class ItemV1 {
  private String name;
  private int price;
  public ItemV1(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public int getPrice() {
    return this.price;
  }
}
