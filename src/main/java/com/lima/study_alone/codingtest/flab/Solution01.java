package com.lima.study_alone.codingtest.flab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/13913
public class Solution01 {

  public static int[] test(int N, int K) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[] visited = new boolean[100001];
    int[] prev = new int[100001];

    queue.offer(new int[]{N, 0});
    visited[N] = true;
    prev[N] = -1;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int X = current[0];
      int steps = current[1];

      if (X == K) {
        // 100,001: 위치 0부터 100,000까지의 정보를 담는 데 필요한 크기.
        // 100,002: 여기에 시간까지 포함하기 위해 1을 더한 값.
        int[] result = new int[100002];
        result[0] = steps;
        for (int i = 0; i <= 100000; i++) {
          result[1 + i] = prev[i];
        }
        return result;
      }

      int[] moves = {X - 1, X + 1, 2 * X};
      for (int next : moves) {
        if (next >= 0 && next <= 100000 && !visited[next]) {
          queue.offer(new int[]{next, steps + 1});
          visited[next] = true;
          prev[next] = X;
        }
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int N = 5;
    int K = 17;

    // 수빈이가 찾을 수 있는 가장 빠른 시간이 몇초 후인지 구해라
    int[] result = test(N, K);
    int steps = result[0];

    ArrayList<Integer> path = new ArrayList<>();
    int pos = K;
    while (pos != -1) {  // 시작점(N)까지 역추적
      path.add(0, pos);  // 경로 앞에 추가
      pos = result[1 + pos];  // 이전 위치로 이동
    }

    // 출력
    System.out.println(steps);  // 최소 시간
    for (int p : path) {
      System.out.print(p + " ");  // 경로
    }
  }
}
