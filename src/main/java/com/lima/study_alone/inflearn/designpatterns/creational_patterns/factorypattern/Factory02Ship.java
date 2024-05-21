package com.lima.study_alone.inflearn.designpatterns.creational_patterns.factorypattern;

import com.lima.study_alone.inflearn.designpatterns.creational_patterns.abstractpattern.YellowAnchor;
import com.lima.study_alone.inflearn.designpatterns.creational_patterns.abstractpattern.YellowWheel;
import lombok.Data;

@Data
public class Factory02Ship {

  private String name;
  private String logo;
  private String color;

  public void setAnchor(YellowAnchor yellowAnchor) {

  }

  public void setWheel(YellowWheel yellowWheel) {

  }
}
