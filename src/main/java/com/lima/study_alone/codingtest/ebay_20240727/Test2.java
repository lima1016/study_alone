package com.lima.study_alone.codingtest.ebay_20240727;

import java.util.LinkedList;
import java.util.Queue;

public class Test2 {

  // 방향벡터 상, 하, 좌, 우
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) {
    int N = 5;
    int[] board = {
        9, 3, 9, 9, 9,
        5, 2, 7, 8, 9,
        8, 1, 5, 8, 9,
        6, 1, 8, 7, 9,
        9, 9, 9, 8, 9
    };
    System.out.println(solution(N, board));
  }

  public static int solution(int N, int[] board) {
    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      System.arraycopy(board, i * N, grid[i], 0, N);
    }

    int startX = N / 2;
    int startY = N / 2;

    return bfs(grid, startX, startY, N);
  }

  public static int bfs(int[][] grid, int startX, int startY, int N) {
    boolean[][] visited = new boolean[N][N];
    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(startX, startY, grid[startX][startY]));
    visited[startX][startY] = true;

    int minCost = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      if (isEdge(current.x, current.y, N)) {
        minCost = Math.min(minCost, current.cost);
      }

      for (int i = 0; i < 4; i++) {
        int nx = current.x + dx[i];
        int ny = current.y + dy[i];

        if (isInBounds(nx, ny, N) && !visited[nx][ny]) {
          visited[nx][ny] = true;
          queue.add(new Node(nx, ny, current.cost + grid[nx][ny]));
        }
      }
    }

    return minCost;
  }

  public static boolean isEdge(int x, int y, int N) {
    return x == 0 || y == 0 || x == N - 1 || y == N - 1;
  }

  public static boolean isInBounds(int x, int y, int N) {
    return x >= 0 && y >= 0 && x < N && y < N;
  }

  static class Node {
    int x, y, cost;

    Node(int x, int y, int cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }
  }
}
