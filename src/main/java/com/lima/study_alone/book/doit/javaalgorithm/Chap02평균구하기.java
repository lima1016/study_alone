package com.lima.study_alone.book.doit.javaalgorithm;

import java.util.Scanner;

public class Chap02평균구하기 {

  /*
  버블 정렬(Bubble Sort)은 배열을 정렬하는 가장 기본적이고 단순한 알고리즘 중 하나야.
  인접한 두 요소를 비교해서, 필요하다면 서로 자리를 바꾸는 방식으로 정렬해.
  마치 물방울이 아래에서 위로 올라가는 것처럼 큰 값이 뒤로 밀려가는 방식이기 때문에 버블 정렬이라고 불려.

  버블 정렬의 동작 과정
  배열의 첫 번째 요소와 두 번째 요소를 비교한다.
  만약 첫 번째 요소가 두 번째 요소보다 크면, 두 요소의 자리를 바꾼다.
  다음으로 두 번째 요소와 세 번째 요소를 비교하고, 필요하다면 자리를 바꾼다.
  배열의 끝까지 이 과정을 반복한다.
  한 번의 전체 반복이 끝나면, 가장 큰 값이 배열의 맨 끝에 자리잡는다.
  이 과정을 배열의 크기만큼 반복하면서 정렬이 완료될 때까지 계속한다.

  자기점수 중 최댓값을 골라서 최댓값을 M이라 할 때 모든 점수를 점수/M*100 으로 고쳤다.
  예를들어 최고점이 70점, 수학 점수가 50점이라면 수학 점수는 50/70*100 이므로 71.43점이다.
  1번째 줄에 시험을 본 과목의 개수 N이 주어진다. 해당 값은 1,000 보다 작거나 같다. 2번째 줄에 세준이의 현재 성적이 주어진다. 해당 값은 100보다 작거나 같은, 음이 아닌 정수이고, 적어도 1개의 값을 0보다 크다.
  1번째 줄에 새로운 평균을 출력한다. 실제 정답과 출력값의 절대 오차 또는 상대 오차가 10^-2 이하이면 정답이다.
  3 // 시험을 본 과목의 개수 40 80 60 // 각 과목의 시험 성적 => 출력: 75.0
  3 // 10 20 30 => 출력: 66.666667
  4 // 1 100 100 100 => 75.25
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double count = scanner.nextInt();
    double sum = 0;
    double max = 0;

    for (int i = 0; i < count; i++) {
      double temp = scanner.nextInt();
      sum += temp;
      if (temp > max) {
        max = temp;
      }
    }
    double avg = (sum / max * 100) / count;

    System.out.println(avg);
  }
}
