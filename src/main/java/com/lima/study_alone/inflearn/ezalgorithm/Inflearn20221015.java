package com.lima.study_alone.inflearn.ezalgorithm;

import java.util.Scanner;

/**
 * 3. 가위 바위 보
 * 설명
 * A, B 두 사람이 가위바위보 게임을 합니다. 총 N번의 게임을 하여 A가 이기면 A를 출력하고, B가 이기면 B를 출력합니다. 비길 경우에는 D를 출력합니다.
 * 가위, 바위, 보의 정보는 1:가위, 2:바위, 3:보로 정하겠습니다.
 * 예를 들어 N=5이면
 * 횟수     1 2 3 4 5
 * A의 정보 2 3 3 1 3
 * B의 정보 1 1 2 2 3
 * 승자    A B A B D
 * 두 사람의 각 회의 가위, 바위, 보 정보가 주어지면 각 회를 누가 이겼는지 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 번째 줄에 게임 횟수인 자연수 N(1<=N<=100)이 주어집니다.
 * 두 번째 줄에는 A가 낸 가위, 바위, 보 정보가 N개 주어집니다.
 * 세 번째 줄에는 B가 낸 가위, 바위, 보 정보가 N개 주어집니다.
 * 출력
 * 각 줄에 각 회의 승자를 출력합니다. 비겼을 경우는 D를 출력합니다.
 * 5
 * 2 3 3 1 3
 * 1 1 2 2 3
 * =>
 * A
 * B
 * A
 * B
 * D
 */
public class Inflearn20221015 {
  public void solution(int count, int[] a, int[] b) {
    for (int i = 0; i < count; i++) {
      System.out.println(answer(a[i], b[i]));
    }
  }

  public String answer(int a, int b) {
//    1:가위, 2:바위, 3:보
    if (a == b) {
      return "D";
    } else {
      switch (a) {
        case 1:
          return b == 2 ? "B" : "A";
        case 2:
          return b == 1 ? "A" : "B";
        case 3:
          return b == 1 ? "B" : "A";
        default:
          return "";
      }
    }
  }

  public static void main(String[] args) {
    Inflearn20221015 main = new Inflearn20221015();
    Scanner scanner = new Scanner(System.in);
    int count = scanner.nextInt();
    int[] a = new int[count];
    int[] b = new int[count];
    for (int i = 0; i < count; i++) {
      a[i] = scanner.nextInt();
    }
    for (int i = 0; i < count; i++) {
      b[i] = scanner.nextInt();
    }
    main.solution(count, a, b);
  }
}
