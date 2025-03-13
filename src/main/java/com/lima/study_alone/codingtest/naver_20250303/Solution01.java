package com.lima.study_alone.codingtest.naver_20250303;

import java.util.*;

// 이거 제출함
class Solution01 {
  public int solution(int[] A) {
    int N = A.length;
    if (N < 2) return 0; // 배열 크기가 2 미만이면 세그먼트 생성 불가능

    // 모든 가능한 세그먼트(인접한 두 요소)의 합과 인덱스를 저장
    Map<Integer, List<Integer>> sumToIndices = new HashMap<>();
    for (int i = 0; i < N - 1; i++) {
      int sum = A[i] + A[i + 1];
      sumToIndices.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
    }

    int maxSegments = 0;
    // 각 합에 대해 최대 선택 가능한 세그먼트 수 계산
    for (List<Integer> indices : sumToIndices.values()) {
      if (indices.size() <= 1) continue; // 세그먼트가 1개 이하이면 무시

      // 인덱스를 정렬 (세그먼트가 교차하지 않도록)
      Collections.sort(indices);
      int count = 0;
      int lastEnd = -2; // 이전 세그먼트의 끝 인덱스 (-1은 세그먼트 길이 2 고려)

      // 동일한 합을 가진 세그먼트 중 교차하지 않게 선택
      for (int start : indices) {
        // 현재 세그먼트의 끝은 start + 1
        if (start > lastEnd) {
          count++; // 교차하지 않으므로 선택 가능
          lastEnd = start + 1; // 다음 세그먼트는 이 끝 이후여야 함
        }
      }
      maxSegments = Math.max(maxSegments, count);
    }

    return maxSegments;
  }

  // 테스트를 위한 메인 메서드
  public static void main(String[] args) {
    Solution01 solution = new Solution01();

    // 테스트 케이스 1
    int[] A1 = {10, 1, 3, 1, 2, 2, 1, 0, 4};
    System.out.println("Test 1: " + solution.solution(A1)); // 출력: 3

    // 테스트 케이스 2
    int[] A2 = {5, 3, 1, 3, 2, 3};
    System.out.println("Test 2: " + solution.solution(A2)); // 출력: 1

    // 테스트 케이스 3
    int[] A3 = {9, 9, 9, 9};
    System.out.println("Test 3: " + solution.solution(A3)); // 출력: 2

    // 테스트 케이스 4
    int[] A4 = {1, 5, 2, 4, 3, 3};
    System.out.println("Test 4: " + solution.solution(A4)); // 출력: 3
  }
}