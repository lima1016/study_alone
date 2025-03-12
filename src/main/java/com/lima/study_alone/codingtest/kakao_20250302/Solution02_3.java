package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution02_3 {
  static int N;
  static char[][] grid;
  static boolean[][] visited;
  static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine().trim());
    grid = new char[N][N];

    int startX = -1, startY = -1;
    int[] teleportA = null, teleportB = null; // 'A', 'a', 'B', 'b' 위치 저장

    // 입력 처리 및 텔레포트 위치 저장
    for (int i = 0; i < N; i++) {
      String[] tokens = br.readLine().trim().split(" ");
      for (int j = 0; j < N; j++) {
        grid[i][j] = tokens[j].charAt(0);
        if (grid[i][j] == 'S') {
          startX = i;
          startY = j;
        } else if (grid[i][j] == 'A') teleportA = new int[]{i, j};
        else if (grid[i][j] == 'a') teleportA = new int[]{i, j};
        else if (grid[i][j] == 'B') teleportB = new int[]{i, j};
        else if (grid[i][j] == 'b') teleportB = new int[]{i, j};
      }
    }

    int result = findShortestPath(startX, startY, teleportA, teleportB);
    System.out.println(result);
  }

  static int findShortestPath(int startX, int startY, int[] teleportA, int[] teleportB) {
    Queue<int[]> queue = new LinkedList<>();
    visited = new boolean[N][N];
    queue.offer(new int[]{startX, startY, 1}); // 시작 위치는 1칸으로 카운트
    visited[startX][startY] = true;

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int x = curr[0], y = curr[1], steps = curr[2];

      if (grid[x][y] == 'E') return steps; // 도착지 도달

      // 네 방향 이동 (장애물 '1'은 이동 불가)
      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d], ny = y + dy[d];
        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && grid[nx][ny] != '1') {
          visited[nx][ny] = true;
          queue.offer(new int[]{nx, ny, steps + 1});
        }
      }

      // 텔레포트 처리 (A ↔ a, B ↔ b)
      if (teleportA != null) {
        if ((grid[x][y] == 'A' || grid[x][y] == 'a') && !visited[teleportA[0]][teleportA[1]]) {
          visited[teleportA[0]][teleportA[1]] = true;
          queue.offer(new int[]{teleportA[0], teleportA[1], steps + 1});
        }
      }
      if (teleportB != null) {
        if ((grid[x][y] == 'B' || grid[x][y] == 'b') && !visited[teleportB[0]][teleportB[1]]) {
          visited[teleportB[0]][teleportB[1]] = true;
          queue.offer(new int[]{teleportB[0], teleportB[1], steps + 1});
        }
      }
    }
    return -1; // 도착 불가능
  }
}
