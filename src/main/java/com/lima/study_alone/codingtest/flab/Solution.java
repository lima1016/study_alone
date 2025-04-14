package com.lima.study_alone.codingtest.flab;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 수빈이 0 <= X <= 100000
// X-1, X+1, 2 * X
// X, K
// 멘토링
// 1. 문제를 어떤식으로 풀건지 설명.
// 내가 작성한 코드에 대한 시간, 공간 복잡도 최적화 다른 접근법들에 대한 장단점 자료구조 사용 이유 등 고민

public class Solution {

  public static int[] bfs(int X, int K) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[] visited = new boolean[100001];
    int[] prev = new int[100001];

    queue.offer(new int[]{X, 0});
    visited[X] = true;
    prev[X] = -1;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int N = current[0];
      int S = current[1];

      if (N == K) {
        int[] result = new int[100002];
        result[0] = S;
        for (int i = 0; i < 100001; i++) {
          result[1 + i] = prev[i];
        }
        return result;
      }

      int[] moves = {N - 1, N + 2, 2 * N};
      for (int move : moves) {
        if (move >= 0 && move <= 100000 && !visited[move]) {
          queue.offer(new int[]{move, S + 1});
          visited[N] = true;
          prev[move] = N;
        }
      }
    }
    return null; //
  }

  // 문제를 발견하고, 문제 해결 과정도 말씀드리면 좋음.
  public static void main(String[] args) {
    int X = 5;
    int K = 17;

    int[] bfs = bfs(X, K);
    List<Integer> path = new LinkedList<>();
    int step = bfs[0];
    for (int i = 0; i < K; i++) {
      if (bfs[i] == -1) break;
      path.add(i);
    }

    System.out.println(step);
    for (Integer i : path) {
      System.out.print(i + " ");
    }
  }
}
