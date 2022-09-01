package com.lima.study_alone.algorithm;

import java.util.Scanner;

// 문자열의 길이 계산
// if the string is empty return 0;
// else return 1 plus the length of the string that excludes the first character;

/**
 * 모든 순환함수는 반복문(iteration)으로 변경 가능하다.
 * 그 역도 성립한다. 즉 모든 반복문은  recursion으로 표현 가능하다.
 * 순환함수는 복잡한 알고리즘을 단순하고 알기쉽게 표현하는 것을 가능하게 한다.
 * 하지만 함수 호출에 따른 호버해드가 있다 (매개변수 전달, 액티베이션 프레임 생성 등)
 */
public class Recursion02 {

  public static int length(String str) {
    if (str.equals(""))
      return 0;
    else return 1 + length(str.substring(1));
  }

  // 문자열의 프린트
  public static void printChars(String str) {
    if (str.length() == 0)
      return;
    else {
      System.out.println(str.charAt(0));
      printChars(str.substring(1));
    }
  }

  // 무자열을 뒤집어 프린트
  public static void printCharsReverse(String str) {
    if (str.length() == 0)
      return;
    else {
      printCharsReverse(str.substring(1));
      System.out.println(str.charAt(0));
    }
  }

  // 2진수로 변환하여 출력
  // 음이 아닌 정수 n을 이진수로 변환하여 인쇄한다.
  public void printInBinary(int n) {
    if (n < 2)
      System.out.println(n);
    else {
      // n을 2로 나눈 몫을 먼저 2진수로 변환하여 인쇄한 후
      printInBinary(n/2);
      // n을 2로 나눈 나머지를 인쇄한다.
      System.out.println(n%2);
    }
  }

  // 배열의 합 구하기
  // data[0]에서 data[n-1]까지의 합을 구하여 반환한다.
  public static int sum(int n, int[] data) {
    if (n <=0)
      return 0;
    else
      return sum(n-1, data) + data[n-1];
  }

  // 데이터파일로 부터 n개의 정수 읽어오기
  // Scanner in이 참조하는 파일로 부터 n개의 정수를 입력받아 배열 data의 data[0], ..., data[n-1]에 저장한다.
  public void readFrom(int n, int[] data, Scanner in) {
    if (n == 0)
      return;
    else {
      readFrom(n-1, data, in);
      data[n-1] = in.nextInt();
    }
  }
}
