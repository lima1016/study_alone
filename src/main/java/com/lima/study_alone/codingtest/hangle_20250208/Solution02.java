package com.lima.study_alone.codingtest.hangle_20250208;

import java.util.Arrays;
import java.util.Comparator;

public class Solution02 {

  public int solution(int[][] customer) {
    int answer = 0;
    if (customer == null || customer.length == 0) {
      return 1;
    }

    Arrays.sort(customer, Comparator.comparingInt(a -> a[0]));

    int start = customer[0][0];
    int end = customer[0][1];

    for (int i = 1; i < customer.length; i++) {
      int curStart = customer[i][0];
      int curEnd = customer[i][1];

      if (curStart <= end) {
        end = Math.max(end, curEnd);
      } else {
        answer += end - start;
        start = curStart;
        end = curEnd;
      }
    }
    answer += end - start;
    return answer;
  }

  public static void main(String[] args) {
    int[][] customer = {{1, 4}, {3, 5}, {8, 10}};
//    int[][] customer = {{1, 2}, {3, 4}};
//    int[][] customer = {{1, 10}, {2, 8}, {3, 5}};

    Solution02 solution02 = new Solution02();
    System.out.println("expected: 2, result: " + solution02.solution(customer));
  }
}
