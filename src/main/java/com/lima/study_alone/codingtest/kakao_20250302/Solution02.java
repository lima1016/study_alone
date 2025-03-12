package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution02 {
  static int N;
  static char[][] grid;
  static boolean[][] visited;
  static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine().trim());
    grid = new char[N][N];

    // 시작 위치 및 텔레포트 저장
    int startX = -1, startY = -1;
    Map<Character, int[]> teleportDest = new HashMap<>();
    Map<Character, int[]> teleportSrc = new HashMap<>();

    for (int i = 0; i < N; i++) {
      String[] tokens = br.readLine().trim().split(" ");
      for (int j = 0; j < N; j++) {
        grid[i][j] = tokens[j].charAt(0);

        if (grid[i][j] == 'S') {
          startX = i;
          startY = j;
        } else if (grid[i][j] == 'a' || grid[i][j] == 'b') {
          teleportDest.put(grid[i][j], new int[]{i, j});
        } else if (grid[i][j] == 'A' || grid[i][j] == 'B') {
          teleportSrc.put(grid[i][j], new int[]{i, j});
        }
      }
    }

    int result = findShortestPath(startX, startY, teleportDest, teleportSrc);
    System.out.println(result);
  }

  static int findShortestPath(int startX, int startY, Map<Character, int[]> teleportDest, Map<Character, int[]> teleportSrc) {
    Queue<int[]> queue = new LinkedList<>();
    visited = new boolean[N][N]; // BFS 방문 배열

    queue.offer(new int[]{startX, startY, 1});
    visited[startX][startY] = true;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int x = current[0];
      int y = current[1];
      int steps = current[2];

      // 도착점에 도달한 경우
      if (grid[x][y] == 'E') {
        return steps;
      }

      // 텔레포트: 대문자에서 소문자로 이동 (예: A -> a, B -> b)
      if (grid[x][y] == 'A' || grid[x][y] == 'B') {
        char dest = Character.toLowerCase(grid[x][y]);
        if (teleportDest.containsKey(dest)) {
          int[] pos = teleportDest.get(dest);
          int nx = pos[0], ny = pos[1];
          if (!visited[nx][ny]) {
            visited[nx][ny] = true;
            queue.offer(new int[]{nx, ny, steps + 1});
          }
        }
      }

      // 텔레포트: 소문자에서 대문자로 이동 (예: a -> A, b -> B)
      if (grid[x][y] == 'a' || grid[x][y] == 'b') {
        char src = Character.toUpperCase(grid[x][y]);
        if (teleportSrc.containsKey(src)) {
          int[] pos = teleportSrc.get(src);
          int nx = pos[0], ny = pos[1];
          if (!visited[nx][ny]) {
            visited[nx][ny] = true;
            queue.offer(new int[]{nx, ny, steps + 1});
          }
        }
      }

      // 네 방향 탐색
      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        // 격자 범위 내이며 아직 방문하지 않은 경우
        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
          char nextCell = grid[nx][ny];
          // '1'은 장애물로 취급 (이동 불가)
          if (nextCell == '0' || nextCell == 'A' || nextCell == 'B' ||
              nextCell == 'a' || nextCell == 'b' || nextCell == 'E') {
            visited[nx][ny] = true;
            queue.offer(new int[]{nx, ny, steps + 1});
          }
        }
      }
    }

    // 도착할 수 없으면 -1 반환
    return -1;
  }
}
