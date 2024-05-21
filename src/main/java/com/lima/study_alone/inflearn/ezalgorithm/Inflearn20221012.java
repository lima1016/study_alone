package com.lima.study_alone.inflearn.ezalgorithm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1. 큰 수 출력하기
 * 설명
 * N개의 정수를 입력받아, 자신의 바로 앞 수보다 큰 수만 출력하는 프로그램을 작성하세요.
 * (첫 번째 수는 무조건 출력한다)
 * 입력
 * 첫 줄에 자연수 N(1<=N<=100)이 주어지고, 그 다음 줄에 N개의 정수가 입력된다.
 * 출력
 * 자신의 바로 앞 수보다 큰 수만 한 줄로 출력한다.
 * 6
 * 7 3 9 5 6 12 => 7 9 6 12
 */
public class Inflearn20221012 {
  public ArrayList<Integer> solution(int number, int[] numbers) {
    ArrayList<Integer> answer = new ArrayList<>();
    answer.add(numbers[0]);

    for (int i = 1; i < number; i++) {
      if (numbers[i] > numbers[i - 1]) {
        answer.add(numbers[i]);
      }
    }
    return answer;
  }

  public static void main(String[] args) {
    Inflearn20221012 main = new Inflearn20221012();
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = in.nextInt();
    }

    for (Integer integer : main.solution(n, array)) {
      System.out.print(integer + " ");
    }
  }
}
