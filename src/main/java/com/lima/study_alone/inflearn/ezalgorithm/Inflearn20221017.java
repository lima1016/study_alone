package com.lima.study_alone.inflearn.ezalgorithm;

import java.util.Scanner;

/**
 * 4. 피보나치 수열
 * 설명
 * 1) 피보나키 수열을 출력한다. 피보나치 수열이란 앞의 2개의 수를 합하여 다음 숫자가 되는 수열이다.
 * 2) 입력은 피보나치 수열의 총 항의 수 이다. 만약 7이 입력되면 1 1 2 3 5 8 13을 출력하면 된다.
 * 입력
 * 첫 줄에 총 항수 N(3<=N<=45)이 입력된다.
 * 출력
 * 첫 줄에 피보나치 수열을 출력합니다.
 * 10 => 1 1 2 3 5 8 13 21 34 55
 */
public class Inflearn20221017 {
  public void solution(int input) {
    int[] nums = new int[input];
    nums[0] = 1;
    nums[1] = 1;

    for (int i = 2; i < nums.length; i++) {
      nums[i] = nums[i-1] + nums[i-2];
    }
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + " ");
    }
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int input = scanner.nextInt();

    Inflearn20221017 main = new Inflearn20221017();
    main.solution(input);
  }
}
