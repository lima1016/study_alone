package com.lima.study_alone.codingtest.flab;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;

public class Main01 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();  // 수빈이 위치
    int K = sc.nextInt();  // 동생 위치

    // BFS 실행
    int[] result = bfs(N, K);
    int time = result[0];

    // 경로 추적
    ArrayList<Integer> path = new ArrayList<>();
    int pos = K;
    while (pos != -1) {
      path.add(0, pos);
      pos = result[1 + pos];
    }

    // 출력
    System.out.println(time);
    for (int i = 0; i < path.size(); i++) {
      System.out.print(path.get(i));
      if (i < path.size() - 1) System.out.print(" ");  // 마지막 숫자 뒤에는 공백 없음
    }
  }

  public static int[] bfs(int N, int K) {
    Queue<int[]> queue = new LinkedList<>();  // {위치, 시간}
    boolean[] visited = new boolean[100001];  // 방문 체크
    int[] prev = new int[100001];             // 이전 위치 저장

    queue.offer(new int[]{N, 0});
    visited[N] = true;
    prev[N] = -1;  // 시작점 표시

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int X = current[0];
      int T = current[1];

      if (X == K) {  // 동생 찾음
        int[] result = new int[100002];
        result[0] = T;
        for (int i = 0; i <= 100000; i++) {
          result[1 + i] = prev[i];
        }
        return result;
      }

      // 3가지 이동
      int[] moves = {X - 1, X + 1, 2 * X};
      for (int next : moves) {
        if (next >= 0 && next <= 100000 && !visited[next]) {
          queue.offer(new int[]{next, T + 1});
          visited[next] = true;
          prev[next] = X;
        }
      }
    }
    return null;  // 도달 불가능 (문제 조건상 항상 도달 가능)
  }
}