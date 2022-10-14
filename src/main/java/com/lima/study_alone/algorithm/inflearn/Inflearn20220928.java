package com.lima.study_alone.algorithm.inflearn;

import java.util.Scanner;

/**
 * 5. 특정 문자 뒤집기
 * 설명
 * 영어 알파벳과 특수문자로 구성된 문자열이 주어지면 영어 알파벳만 뒤집고,
 * 특수문자는 자기 자리에 그대로 있는 문자열을 만들어 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 줄에 길이가 100을 넘지 않는 문자열이 주어집니다.
 * 출력
 * 첫 줄에 알파벳만 뒤집힌 문자열을 출력합니다.
 * a#b!GE*T@S => S#T!EG*b@a
 * kdj#@kdjg%$#kdjgk@kd$dk => kdd#@kkgj%$#dkgjd@kj$dk
 * kqQdj#@kd#g%$#kdj&&gk@kd$dQGk => kGQdd#@kk#g%$#jdk&&gd@kj$dQqk
 * kHSHHS#qQ!DGSG#@Sdj#@kd#g%$#kdj&&gk@kd$d#%&DGS@!DH%SQGk# => kGQSHD#SG!Dddk#@kgj#@dk#g%$#dkj&&dS@GS$G#%&DQq@!SH%HSHk#
 */
public class Inflearn20220928 {
  public String solution(String input) {
    // 입력 받은 input 을 쪼개기
    char[] inputs = input.toCharArray();
    String tmp = "";
    // 일단 문자만 골라내기
    for (int i = 0; i < inputs.length; i++) {
      if (Character.isAlphabetic(inputs[i])) {
        tmp += inputs[i];
      }
    }

    // 결과값
    String reverse = new StringBuilder(tmp).reverse().toString();
    char[] chars = reverse.toCharArray();
    int increase = 0;
    for (int i = 0; i < inputs.length; i++) {
      // 대문자 65 ~ 90 소문자 97 ~ 122
      if ((65 <= inputs[i] && inputs[i] <= 90) || 97 <= inputs[i] && inputs[i] <= 122) {
        inputs[i] = chars[increase++];
      }
    }
    return String.valueOf(inputs);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String input1 = in.nextLine();
    Inflearn20220928 main = new Inflearn20220928();
    System.out.println(main.solution(input1));
  }
}
