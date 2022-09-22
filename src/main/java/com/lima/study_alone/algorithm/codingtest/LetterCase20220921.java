package com.lima.study_alone.algorithm.codingtest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 2. 대소문자 변환
 * 설명
 * 대문자와 소문자가 같이 존재하는 문자열을 입력받아 대문자는 소문자로 소문자는 대문자로 변환하여 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 줄에 문자열이 입력된다. 문자열의 길이는 100을 넘지 않습니다.
 * 문자열은 영어 알파벳으로만 구성되어 있습니다.
 * 출력
 * 첫 줄에 대문자는 소문자로, 소문자는 대문자로 변환된 문자열을 출력합니다.
 * StuDY ==> sTUdy
 */
public class LetterCase20220921 {
  public String solution(String input) {
    // input을  input.toCharArray() 로 바꾼 후 Character.isLowerCase('') 하는 방법도 있음.
    String[] split = input.split("");
    String result = "";
    for (String s : split) {
      result = result.concat(s.equals(s.toLowerCase()) ? s.toUpperCase() : s.toLowerCase());
    }

    return result;
  }
  public static void main(String[] args){
    LetterCase20220921 main = new LetterCase20220921();
    Scanner in =new Scanner(System.in);
    String input1 = in.next();
    System.out.println(main.solution(input1));
  }
}
