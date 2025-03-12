package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution02_2 {
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
    visited = new boolean[N][N];

    queue.offer(new int[]{startX, startY, 1});
    visited[startX][startY] = true;

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int x = curr[0], y = curr[1], steps = curr[2];

      if (grid[x][y] == 'E') return steps;

      // 텔레포트: 대문자 -> 소문자, 소문자 -> 대문자로 이동
      int[] target = getTeleportTarget(grid[x][y], teleportDest, teleportSrc);
      if (target != null) {
        int tx = target[0], ty = target[1];
        if (!visited[tx][ty]) {
          visited[tx][ty] = true;
          queue.offer(new int[]{tx, ty, steps + 1});
        }
      }

      // 네 방향 이동 (장애물 '1'은 이동 불가)
      for (int d = 0; d < 4; d++) {
        int nx = x + dx[d], ny = y + dy[d];
        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && grid[nx][ny] != '1') {
          visited[nx][ny] = true;
          queue.offer(new int[]{nx, ny, steps + 1});
        }
      }
    }
    return -1;
  }

  // 현재 셀이 텔레포트 대상이라면 반대 케이스의 좌표를 반환 (없으면 null)
  static int[] getTeleportTarget(char c, Map<Character, int[]> teleportDest, Map<Character, int[]> teleportSrc) {
    if (c == 'A' || c == 'B') return teleportDest.get(Character.toLowerCase(c));
    if (c == 'a' || c == 'b') return teleportSrc.get(Character.toUpperCase(c));
    return null;
  }
}
