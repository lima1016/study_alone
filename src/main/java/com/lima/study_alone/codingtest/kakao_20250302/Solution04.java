package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution04 {
  static class Edge {
    int to, weight;

    Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int Na = Integer.parseInt(st.nextToken()); // 구름 나라 도시 수
    int Nb = Integer.parseInt(st.nextToken()); // 하늘 나라 도시 수
    int Ma = Integer.parseInt(st.nextToken()); // 구름 나라 도로 수
    int Mb = Integer.parseInt(st.nextToken()); // 하늘 나라 도로 수

    // 그래프 초기화
    List<List<Edge>> graphA = new ArrayList<>();
    List<List<Edge>> graphB = new ArrayList<>();

    for (int i = 0; i <= Na; i++) {
      graphA.add(new ArrayList<>());
    }

    for (int i = 0; i <= Nb; i++) {
      graphB.add(new ArrayList<>());
    }

    // 구름 나라 도로 정보 입력
    for (int i = 0; i < Ma; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      graphA.get(u).add(new Edge(v, w));
      graphA.get(v).add(new Edge(u, w)); // 양방향 도로
    }

    // 하늘 나라 도로 정보 입력
    for (int i = 0; i < Mb; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      graphB.get(u).add(new Edge(v, w));
      graphB.get(v).add(new Edge(u, w)); // 양방향 도로
    }

    // 각 나라의 모든 도시 간 최단 거리 계산
    int[][] distA = new int[Na + 1][Na + 1];
    int[][] distB = new int[Nb + 1][Nb + 1];

    for (int i = 1; i <= Na; i++) {
      Arrays.fill(distA[i], Integer.MAX_VALUE);
      dijkstra(graphA, distA[i], i, Na);
    }

    for (int i = 1; i <= Nb; i++) {
      Arrays.fill(distB[i], Integer.MAX_VALUE);
      dijkstra(graphB, distB[i], i, Nb);
    }

    // 모든 가능한 포탈 위치에 대해 밀접도 계산
    int minCloseness = Integer.MAX_VALUE;

    for (int ua = 1; ua <= Na; ua++) {
      for (int ub = 1; ub <= Nb; ub++) {
        // 이 도시들에 포탈을 설치할 경우의 밀접도 계산
        int maxDist = 0;

        for (int i = 1; i <= Na; i++) {
          for (int j = 1; j <= Nb; j++) {
            if (distA[i][ua] == Integer.MAX_VALUE || distB[j][ub] == Integer.MAX_VALUE) {
              continue; // 연결되지 않은 도시는 무시
            }

            int distance = distA[i][ua] + distB[j][ub];
            maxDist = Math.max(maxDist, distance);
          }
        }

        minCloseness = Math.min(minCloseness, maxDist);
      }
    }

    System.out.println(minCloseness);
  }

  // 다익스트라 알고리즘으로 출발 도시에서 다른 모든 도시까지의 최단 거리 계산
  static void dijkstra(List<List<Edge>> graph, int[] dist, int start, int n) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    boolean[] visited = new boolean[n + 1];

    dist[start] = 0;
    pq.offer(new int[]{start, 0});

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int node = curr[0];
      int distance = curr[1];

      if (visited[node]) continue;
      visited[node] = true;

      for (Edge edge : graph.get(node)) {
        int next = edge.to;
        int weight = edge.weight;

        if (dist[next] > distance + weight) {
          dist[next] = distance + weight;
          pq.offer(new int[]{next, dist[next]});
        }
      }
    }
  }
}
