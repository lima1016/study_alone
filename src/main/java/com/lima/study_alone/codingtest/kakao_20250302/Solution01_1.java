package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import java.io.*;
import java.util.*;

// 이거로 제출함
public class Solution01_1 {
  static int N, K;
  static char[][] map;
  static int baseX, baseY;
  static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동을 위한 배열
  static int[] dy = {0, 0, -1, 1};

  static class Resource implements Comparable<Resource> {
    int x, y;
    int distance;

    Resource(int x, int y, int baseX, int baseY) {
      this.x = x;
      this.y = y;
      this.distance = Math.abs(baseX - x) + Math.abs(baseY - y);
    }

    @Override
    public int compareTo(Resource other) {
      // 거리가 다르면 거리순으로 정렬
      if (this.distance != other.distance) {
        return this.distance - other.distance;
      }
      // 거리가 같으면 행(x)이 작은 순으로 정렬
      if (this.x != other.x) {
        return this.x - other.x;
      }
      // 행도 같으면 열(y)이 작은 순으로 정렬
      return this.y - other.y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");

    N = Integer.parseInt(input[0]);
    K = Integer.parseInt(input[1]);

    map = new char[N][N];

    // 지도 입력 받기
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < N; j++) {
        map[i][j] = line.charAt(j);
        if (map[i][j] == 'B') {
          baseX = i;
          baseY = j;
        }
      }
    }

    // BFS로 접근 가능한 자원 찾기
    List<Resource> accessibleResources = findAccessibleResources();

    // 채취 가능한 자원의 수가 K보다 적으면 -1 출력
    if (accessibleResources.size() < K) {
      System.out.println("-1");
    } else {
      // K개의 자원 위치 X로 표시
      for (int i = 0; i < K; i++) {
        Resource r = accessibleResources.get(i);
        map[r.x][r.y] = 'X';
      }

      // 결과 지도 출력
      for (int i = 0; i < N; i++) {
        System.out.println(new String(map[i]));
      }
    }
  }

  // BFS로 탐사 기지에서 접근 가능한 자원 찾기
  static List<Resource> findAccessibleResources() {
    List<Resource> accessibleResources = new ArrayList<>();
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];

    queue.offer(new int[]{baseX, baseY});
    visited[baseX][baseY] = true;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int x = current[0];
      int y = current[1];

      // 상하좌우 탐색
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 지도 범위 내에 있고, 방문하지 않았으며, 빈 공간이거나 자원이면 이동
        if (isValid(nx, ny) && !visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'O')) {
          visited[nx][ny] = true;
          queue.offer(new int[]{nx, ny});

          // 자원('O')인 경우에만 리스트에 추가
          if (map[nx][ny] == 'O') {
            accessibleResources.add(new Resource(nx, ny, baseX, baseY));
          }
        }
      }
    }

    // 맨해튼 거리 기준으로 정렬
    Collections.sort(accessibleResources);
    return accessibleResources;
  }

  // 좌표가 지도 범위 내에 있는지 확인
  static boolean isValid(int x, int y) {
    return x >= 0 && x < N && y >= 0 && y < N;
  }
}
