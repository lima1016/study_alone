package com.lima.study_alone.codingtest.elevenstreet20230128;

public class Test02 {

  public static boolean solution(int[] A, int K) {
    int n = A.length;
    for (int i = 0; i < n-1; i++) {
      if (A[i] + 1 < A[i + 1])
        return false;
    }
    if (A[0] != 1 || A[n - 1] != K)
      return false;
    else
      return true;
  }
  public static void main(String[] args) {
    int[] arry = {1, 1, 2, 3, 3};
    int K = 3;

    System.out.println(Test02.solution(arry, K));;
  }
}
