package com.lima.study_alone.algorithm.codingtest;

import java.util.Scanner;

/**
 * 4. 단어 뒤집기
 * 설명
 * N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 줄에 자연수 N(3<=N<=20)이 주어집니다.
 * 두 번째 줄부터 N개의 단어가 각 줄에 하나씩 주어집니다. 단어는 영어 알파벳으로만 구성되어 있습니다.
 * 출력
 * N개의 단어를 입력된 순서대로 한 줄에 하나씩 뒤집어서 출력합니다.
 * 3
 * good
 * Time
 * Big
 * =>
 * doog
 * emiT
 * giB
 */
public class Inflearn20220926 {
  public String solution(String word) {
    StringBuilder str = new StringBuilder();
    StringBuilder reverse = str.append(word).reverse();
    return reverse.toString();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num = in.nextInt();

    Inflearn20220926 main = new Inflearn20220926();
    for (int i = 0; i < num; i++) {
      String input1 = in.next();
      System.out.println(main.solution(input1));
    }
  }
}
