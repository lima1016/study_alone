package com.lima.study_alone.codingtest.naver_20250104;

/*
  0에서 k-1 까지 번호가 매겨진 K개역이 있는 지하철 노선이 있습니다.
  역 번호가 1씩 다를 경우 역 간에 직접 연결된다.
  승객은 지하철 노선을 양방향으로 이용할 수 있다.
  기차 탑승 요금은 1이다. 승객이 인접한 두 역 사이를 이동할 때마다 요금은 2씩 증가한다.
  지하철 시스템은 매일 승객의 그날 여행 내역에 따라 하루가 끝날 때 요금을 청구한다.
  길이가 N인 배열 start 및 dest가 주어지며, 이는 승객이 그날에 이용한 모든 지하철 승차를 설명한다.
  X 번째 승차 중에 승객은 start[x] 역에서 기차를 타고 dest[x]역에서 기차를 내렸다.
  길이가 K인 배열 limit가 주어진다 승객이 그낭에 방문한 가장 큰 역 번호가 Y인 경우 해당 승객의 해당 날짜 요금 한도는 limit[Y] 이다
  길이가 N인 배열 start와 dest, 그리고 길이가 K인 배열 limit가 주어졌을때, 승객에게 하루가 끝날 때 청구되는 금액을 반환한다.

  start = [1, 0, 2, 4]
  dest = [2, 2, 0, 5]
  limit = [3, 17, 7, 4, 5, 17]인 경우 함수 16을 반환해야한다.

  1번 스테이션에서 2번 스테이션 까지 비용 3
  0번 스테이션에서 2번 스테이션 까지 비용 5
  2번 스테이션에서 0번 스테이션 까지 비용 5
  4번 스테이션에서 5번 스테이션 까지 비용 3
  총 비용은 16이다 가장 많이 방문한 역 번호는 5이다 역 5의 요금 한도는 17이므로 이경우에는 적용되지 않는다.

 */
public class Test01 {

  public int solution(int[] start, int[] dest, int[] limit) {
    int totalCost = 0;
    int lgtVisited = 0;

    for (int i = 0; i < start.length; i++) {
      int s = start[i];
      int d = dest[i];
      int distance = Math.abs(d - s);
      //  기본요금 1에 요금은 2씩 증가
      int fare = distance * 2 + 1; // 기본 요금 1 추가
      totalCost += fare;

      lgtVisited = Math.max(lgtVisited, Math.max(s, d));
    }

    // 요금 한도 확인
    if (totalCost > limit[lgtVisited]) {
      return limit[lgtVisited];
    }

    return totalCost;
  }

  public static void main(String[] args) {
    Test01 test = new Test01();
    System.out.println(test.solution(new int[]{1, 2, 0, 2, 3}, new int[]{2, 1, 2, 1, 2}, new int[]{4, 8, 18, 16, 20}));
  }
}
