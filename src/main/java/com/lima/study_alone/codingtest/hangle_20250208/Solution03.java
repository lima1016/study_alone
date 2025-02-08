package com.lima.study_alone.codingtest.hangle_20250208;

import java.util.Arrays;

public class Solution03 {

  public int[] solution(int n, int m, int[] p, int[][] sels) {
    int[] answer = new int[n];

    return answer;
  }


  public static void main(String[] args) {
    Solution03 solution03 = new Solution03();
//    int n = 4;
//    int m = 3;
//    int[] p = {30, 50, 20};
//    int[][] sels = {{30, -10, 20}, {-50, 10, -20}, {10, -20, 40}, {-30, -30, -30}};
//    System.out.println("[10, 20, 9, 17]" + Arrays.toString(solution03.solution(n, m, p, sels)));

    int n = 3;
    int m = 2;
    int[] p = {5, 5};
    int[][] sels = {{1, 2}, {-2, 4}, {3, -3}};
    System.out.println("[2, 2, 4]" + Arrays.toString(solution03.solution(n, m, p, sels)));
  }
}
