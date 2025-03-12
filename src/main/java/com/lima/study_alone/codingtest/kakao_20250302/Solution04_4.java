package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 이거 제출함
public class Solution04_4 {
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

    // 그래프 초기화 (1부터 시작, 0은 사용 안 함)
    List<List<Edge>> graphA = new ArrayList<>(Na + 1);
    List<List<Edge>> graphB = new ArrayList<>(Nb + 1);
    for (int i = 0; i <= Na; i++) graphA.add(new ArrayList<>());
    for (int i = 0; i <= Nb; i++) graphB.add(new ArrayList<>());

    // 구름 나라 도로 입력
    for (int i = 0; i < Ma; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graphA.get(u).add(new Edge(v, w));
      graphA.get(v).add(new Edge(u, w)); // 양방향
    }

    // 하늘 나라 도로 입력
    for (int i = 0; i < Mb; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      graphB.get(u).add(new Edge(v, w));
      graphB.get(v).add(new Edge(u, w)); // 양방향
    }

    int minCloseness = Integer.MAX_VALUE;
    // 구름 나라와 하늘 나라의 모든 포탈 위치 쌍에 대해 계산
    for (int ua = 1; ua <= Na; ua++) {
      for (int ub = 1; ub <= Nb; ub++) {
        int maxDist = 0;
        // 구름 나라와 하늘 나라의 모든 도시 쌍에 대해 거리 계산
        for (int i = 1; i <= Na; i++) {
          for (int j = 1; j <= Nb; j++) {
            int distA = dijkstra(graphA, i, ua, Na); // i에서 ua까지
            int distB = dijkstra(graphB, j, ub, Nb); // j에서 ub까지
            if (distA == Integer.MAX_VALUE || distB == Integer.MAX_VALUE) continue;
            maxDist = Math.max(maxDist, distA + distB);
          }
        }
        minCloseness = Math.min(minCloseness, maxDist);
      }
    }

    System.out.println(minCloseness == Integer.MAX_VALUE ? -1 : minCloseness);
  }

  // 다익스트라: 출발지에서 목표지까지의 최단 거리 계산
  static int dijkstra(List<List<Edge>> graph, int start, int target, int n) {
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    boolean[] visited = new boolean[n + 1];

    dist[start] = 0;
    pq.offer(new int[]{start, 0});

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int node = curr[0];
      if (node == target) return dist[node]; // 목표지 도달 시 바로 반환
      if (visited[node]) continue;
      visited[node] = true;

      for (Edge edge : graph.get(node)) {
        int next = edge.to;
        int weight = edge.weight;
        if (dist[next] > dist[node] + weight) {
          dist[next] = dist[node] + weight;
          pq.offer(new int[]{next, dist[next]});
        }
      }
    }
    return dist[target];
  }
}
