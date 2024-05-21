package com.lima.study_alone.inflearn.ezalgorithm;

import java.util.Scanner;

/**
 * 5. 소수(에라토스테네스 체)
 * 설명
 * 자연수 N이 입력되면 1부터 N까지의 소수의 개수를 출력하는 프로그램을 작성하세요.
 * 만약 20이 입력되면 1부터 20까지의 소수는 2, 3, 5, 7, 11, 13, 17, 19로 총 8개입니다.
 * 입력
 * 첫 줄에 자연수의 개수 N(2<=N<=200,000)이 주어집니다.
 * 출력
 * 첫 줄에 소수의 개수를 출력합니다.
 * 20 => 8
 */
public class Inflearn20221019 {

  public int solution(int num) {
    int result = 0;
    int[] ch = new int[num + 1];
    for (int i = 2; i <= num; i++) {
      if (ch[i] == 0) {
        result++;
        for (int l = i; l <= num; l=l+i) {
          ch[l] = 1;
        }
      }
    }
    return result;
  }
  // time out...
//  public int solution(int num) {
//    int result = 1;
//    for (int i = 3; i <= num; i++) {
//      for (int l = 2; l < i; l++) {
//        if (i % l == 0) {
//          // 자기 자신 이외에 나누어 떨어지면 소수가 아님
//          result--;
//          break;
//        }
//      }
//      result++;
//    }
//    return result;
//  }


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();

    Inflearn20221019 main = new Inflearn20221019();
    System.out.println(main.solution(num));
  }
}
