package com.lima.study_alone.designpatterns.compositepattern;

import lombok.Data;

public class ItemV2 implements Component{
  private String name;
  private int price;

  public ItemV2(String name, int price) {
    this.name = name;
    this.price = price;
  }
  @Override
  public int getPrice() {
    return this.price;
  }
}