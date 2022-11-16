package com.lima.study_alone.designpatterns.structural_patterns.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class BagV2 implements Component {

  private List<Component> components = new ArrayList<>();
  public void add(Component item) {
    components.add(item);
  }
  public List<Component> getComponents() {
    return components;
  }

  @Override
  public int getPrice() {
    //                                  c -> c.getPrice()
    return components.stream().mapToInt(Component::getPrice).sum();
  }
}
