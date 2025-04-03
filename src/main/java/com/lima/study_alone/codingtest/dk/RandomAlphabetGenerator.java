package com.lima.study_alone.codingtest.dk;

import java.util.Random;

class RandomAlphabetGenerator implements StringGenerator {
  private final Random random = new Random();

  @Override
  public String generate(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int randomType = random.nextInt(52);
      char c;

      if (randomType < 26) {
        c = (char) (97 + randomType);
      } else {
        c = (char) (65 + (randomType - 26));
      }

      sb.append(c);
    }
    return sb.toString();
  }
}