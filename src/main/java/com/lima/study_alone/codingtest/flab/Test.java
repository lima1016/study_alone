package com.lima.study_alone.codingtest.flab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test {

  public static int[] test(int N, int K) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[] visited = new boolean[100001];
    int[] prev = new int[100001];

    queue.offer(new int[]{N, 0});
    visited[N] = true;
    prev[N] = -1; // 시작점은 이전 위치가 없음으로 -1

    while(!queue.isEmpty()) {
      int[] current = queue.poll();
      int X = current[0]; // 현재 위치
      int steps = current[1];

      if (X == K) {
        int[] result = new int[100002];
        result[0] = steps; // 걸린 시간
        for (int i = 0; i < 100000; i++) {
          result[1 + i] = prev[i]; // 경로 저장
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
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();  // 수빈이 위치
    int K = sc.nextInt();  // 동생 위치

    // BFS 실행
    int[] result = test(N, K);
    int time = result[0];
  }
}
