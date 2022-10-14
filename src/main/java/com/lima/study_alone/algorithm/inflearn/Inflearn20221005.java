package com.lima.study_alone.algorithm.inflearn;

import java.util.Scanner;

/**
 * 10. 가장 짧은 문자거리
 * 설명
 * 한 개의 문자열 s와 문자 t가 주어지면 문자열 s의 각 문자가 문자 t와 떨어진 최소거리를 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 번째 줄에 문자열 s와 문자 t가 주어진다. 문자열과 문자는 소문자로만 주어집니다.
 * 문자열의 길이는 100을 넘지 않는다.
 * 출력
 * 첫 번째 줄에 각 문자열 s의 각 문자가 문자 t와 떨어진 거리를 순서대로 출력한다.
 * teachermode e => 1 0 1 2 1 0 1 2 2 1 0
 * fkdgkjdflkgjljslgjkfldjlkfdg f => 0 1 2 3 3 2 1 0 1 2 3 4 5 6 5 4 3 2 1 0 1 2 3 2 1 0 1 2
 */
public class Inflearn20221005 {
  public String solution(String word, char t) {
    char[] chars = word.toCharArray();
    int[] result = new int[word.length()];
    int[] tIndex = new int[(int) word.chars().filter(c -> c == t).count()];
    int min = word.length();
    int count = 0;
    // t의 인덱스만 골라 담기
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == t) {
        tIndex[count++] = i;
      }
    }
    // 골라 담은 인덱스와 모든 인덱스를 빼서 미니멈만 담기
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == t) {
        result[i] = 0;
      } else {
        for (int l = 0; l < tIndex.length; l++) {
          int findMin = Math.abs(i - tIndex[l]);
          if (findMin <= min) {
            min = findMin;
            result[i] = min;
          }
        }
        min = word.length();
      }
    }
    String answer = "";
    // 결과
    for (int i : result) {
      answer += String.valueOf(i);
    }
    return answer.replace("", " ").trim();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String word = in.nextLine();
    Inflearn20221005 main = new Inflearn20221005();
    String[] split = word.split(" ");
    System.out.println(main.solution(split[0], split[1].charAt(0)));
  }
}
