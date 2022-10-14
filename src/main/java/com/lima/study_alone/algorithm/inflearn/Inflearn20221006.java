package com.lima.study_alone.algorithm.inflearn;

import java.util.Scanner;

/**
 * 11. 문자열 압축
 * 설명
 * 알파벳 대문자로 이루어진 문자열을 입력받아 같은 문자가 연속으로 반복되는 경우 반복되는
 * 문자 바로 오른쪽에 반복 횟수를 표기하는 방법으로 문자열을 압축하는 프로그램을 작성하시오.
 * 단 반복횟수가 1인 경우 생략합니다.
 * 입력
 * 첫 줄에 문자열이 주어진다. 문자열의 길이는 100을 넘지 않는다.
 * 출력
 * 첫 줄에 압축된 문자열을 출력한다
 * KKHSSSSSSSE => K2HS7E
 * KSTTTSEEKFKKKDJJGG => KST3SE2KFK3DJ2G2
 */
public class Inflearn20221006 {

  public String solution(String input) {
    String result = "";
    char[] chars = input.toCharArray();
    int index = 0;
    for (int i = 0; i < chars.length; i++) {
      int count = 0;
      for (int l = i; l < chars.length; l++) {
        if (chars[i] == chars[l]) {
          count++;
          index = l;
        } else
          break;
      }
      result += count != 0 && count != 1 ? chars[i] + "" + count : chars[i];
      i = index;
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    Inflearn20221006 main = new Inflearn20221006();
    System.out.println(main.solution(input));
  }
}
