package com.lima.study_alone.algorithm.inflearn;

import java.util.*;

/**
 * 6. 중복문자제거
 * 설명
 * 소문자로 된 한개의 문자열이 입력되면 중복된 문자를 제거하고 출력하는 프로그램을 작성하세요.
 * 중복이 제거된 문자열의 각 문자는 원래 문자열의 순서를 유지합니다.
 * 입력
 * 첫 줄에 문자열이 입력됩니다. 문자열의 길이는 100을 넘지 않는다.
 * 출력
 * 첫 줄에 중복문자가 제거된 문자열을 출력합니다.
 * ksekkset => kset
 */
public class Inflearn20220929 {
  public String otherSolution(String input) {
    String result = "";
    for (int i = 0; i < input.length(); i++) {
      if (input.indexOf(input.charAt(i)) == i) {
        result += input.charAt(i);
      }
    }
    return result;
  }
  public String solution(String input) {
    // indexOf() 로도 풀수있음...
    String result = "";
    Set<Character> set = new LinkedHashSet();
    char[] chars = input.toCharArray();
    for (char aChar : chars) {
      set.add(Character.valueOf(aChar));
    }

    // 결과
    for (Character character : set) {
      result += character;
    }

    return result;
  }
  public static void main(String[] args) {
    Scanner in=new Scanner(System.in);
    String input = in.nextLine();
    Inflearn20220929 main = new Inflearn20220929();
//    System.out.println(main.solution(input));
    System.out.println(main.otherSolution(input));
  }
}
