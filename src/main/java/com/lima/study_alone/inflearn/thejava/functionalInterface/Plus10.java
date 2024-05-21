package com.lima.study_alone.inflearn.thejava.functionalInterface;

import org.springframework.cglib.core.internal.Function;

//                                      입력값    리턴값
public class Plus10 implements Function<Integer, Integer> {

  @Override
  public Integer apply(Integer integer) {
    return integer + 10;
  }
}
