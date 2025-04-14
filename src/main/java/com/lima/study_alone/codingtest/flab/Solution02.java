package com.lima.study_alone.codingtest.flab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1197
public class Solution02 {
  static int[] parent; // 각 정점이 속한 집합을 관리하는 배열

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt(); // 정점 개수
    int E = sc.nextInt(); // 간선 개수

    parent = new int[V + 1]; // 부모 저장 배열 초기화

    for (int i = 1; i <= V; i++) {
      parent[i] = i; // 처음엔 자기 자신이 부모
    }

    List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < E; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      int w = sc.nextInt();
      edges.add(new int[]{w, u, v}); // (가중치, 정점1, 정점2)
    }

    // 크루스칼 알고리즘은 가중치가 작은 간선부터 선택해야 하니까, 리스트를 가중치(a[0]) 기준으로 오름차순 정렬합니다.
    edges.sort(Comparator.comparingInt(a -> a[0])); // 가중치 기준 정렬

    // MST의 총 가중치 합.
    int mstWeight = 0;
    int count = 0; // MST에 추가된 간선 수. MST는 정확히 V-1개의 간선을 가집니다.
    for (int[] edge : edges) {
      if (union(edge[1], edge[2])) { // 사이클이 없으면 추가
        mstWeight += edge[0];
        count++;
        if (count == V - 1) break; // MST 완성되면 종료
      }
    }

    System.out.println(mstWeight);
  }

  static int find(int x) {
    if (parent[x] == x) return x; // 자기 자신이 부모면 반환
    return parent[x] = find(parent[x]); // 경로 압축
  }

  static boolean union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    if (rootA == rootB) return false; // 같은 집합이면 사이클 발생 → 추가 X
    parent[rootB] = rootA; // 두 집합을 합침
    return true;
  }
}
