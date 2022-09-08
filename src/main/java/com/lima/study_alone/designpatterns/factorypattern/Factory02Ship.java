package com.lima.study_alone.designpatterns.factorypattern;

import com.lima.study_alone.designpatterns.abstractpattern.YellowAnchor;
import com.lima.study_alone.designpatterns.abstractpattern.YellowWheel;
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
