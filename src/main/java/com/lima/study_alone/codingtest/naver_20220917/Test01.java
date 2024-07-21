package com.lima.study_alone.codingtest.naver_20220917;

import java.util.*;
import java.util.stream.IntStream;

public class Test01 {
  public int solution(int[] T) {
    Integer[] ever = IntStream.of(T).boxed().toArray( Integer[]::new );
    int num = T.length / 2;
    int count = 0;
    Set<Integer> set = new HashSet<>(Arrays.asList(ever));

    if (num <= set.size()) {
      count = num;
    } else {
      count = set.size();
    }
    return count;
  }

  public static void main(String[] args) {
    int[] ary = {3, 4, 7, 7, 6, 6};
    Test01 test = new Test01();
    System.out.println(test.solution(ary));
  }
}
