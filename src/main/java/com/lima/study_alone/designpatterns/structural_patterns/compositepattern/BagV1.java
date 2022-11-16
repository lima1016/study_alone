package com.lima.study_alone.designpatterns.structural_patterns.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class BagV1 {

  private List<ItemV1> items = new ArrayList<>();
  public void add(ItemV1 item) {
    items.add(item);
  }
  public List<ItemV1> getItems() {
    return items;
  }
}
