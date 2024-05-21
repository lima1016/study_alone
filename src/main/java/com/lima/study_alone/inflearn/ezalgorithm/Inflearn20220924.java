package com.lima.study_alone.inflearn.ezalgorithm;

import java.util.Scanner;

/**
 * 3. 문장 속 단어
 * 설명
 * 한 개의 문장이 주어지면 그 문장 속에서 가장 긴 단어를 출력하는 프로그램을 작성하세요.
 * 문장속의 각 단어는 공백으로 구분됩니다.
 * 입력
 * 첫 줄에 길이가 100을 넘지 않는 한 개의 문장이 주어집니다. 문장은 영어 알파벳으로만 구성되어 있습니다.
 * 출력
 * 첫 줄에 가장 긴 단어를 출력한다. 가장 길이가 긴 단어가 여러개일 경우 문장속에서 가장 앞쪽에 위치한
 * it is time to study => study
 */
public class Inflearn20220924 {
  public String solution(String word) {
    String result = "";
    // 빈칸을 기준으로 단어 나누기
    String[] strings = word.split(" ");
    int tmp = 0;
    // 나눈 단어들의 길이 체크 및 최대 값 구하기
    for (String string : strings) {
      if (string.length() >= tmp) {
        tmp = string.length();
        result = string;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String input1 = in.nextLine();
    Inflearn20220924 main = new Inflearn20220924();
    String solution = main.solution(input1);
    System.out.println(solution);
  }
}
