package com.lima.study_alone.algorithm.inflearn;

import java.util.Scanner;

/**
 * 7. 회문 문자열
 * 설명
 * 앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열을 회문 문자열이라고 합니다.
 * 문자열이 입력되면 해당 문자열이 회문 문자열이면 "YES", 회문 문자열이 아니면 “NO"를 출력하는 프로그램을 작성하세요.
 * 단 회문을 검사할 때 대소문자를 구분하지 않습니다.
 * 입력
 * 첫 줄에 길이 100을 넘지 않는 공백이 없는 문자열이 주어집니다.
 * 출력
 * 첫 번째 줄에 회문 문자열인지의 결과를 YES 또는 NO로 출력합니다.
 * goG => YES
 */
public class Inflearn20221001 {

  public String solution(String input) {
    // 첫번째 방법
//    int half = input.length() / 2;
//    String back = input.substring(half);
//    String reverseBack = new StringBuilder(back).reverse().toString();
//    String front = input.length() % 2 == 0 ? input.substring(0, half) : input.substring(0, half+1);
//    if (reverseBack.equalsIgnoreCase(front)) {
//      return "YES";
//    }
    // 두번째 방법 - 이거로... 하자...! ㅎㅎㅎ
    String reverse = new StringBuilder(input).reverse().toString();
    if (reverse.equalsIgnoreCase(input)) {
      return "YES";
    }
    return "NO";
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    Inflearn20221001 main = new Inflearn20221001();
    System.out.println(main.solution(input));
  }
}
