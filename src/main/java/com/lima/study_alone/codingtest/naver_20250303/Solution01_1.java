package com.lima.study_alone.codingtest.naver_20250303;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution01_1 {
  public int solution(int[] A) {
    int length = A.length;
    if (length < 2) return 0; // 배열 크기가 2 미만이면 세그먼트 생성 불가능

    // 세그먼트 합과 마지막 선택된 위치를 저장 (합 -> {마지막 끝 인덱스, 선택된 개수})
    Map<Integer, int[]> sumToState = new HashMap<>();
    int maxSegments = 0;

    // 배열을 한 번만 순회하며 세그먼트 합 계산
    for (int i = 0; i < length - 1; i++) {
      int sum = A[i] + A[i + 1];
      int[] state = sumToState.getOrDefault(sum, new int[]{-2, 0}); // {마지막 끝 인덱스, 선택된 개수}

      // 현재 세그먼트의 끝은 i + 1
      if (i > state[0]) { // 이전 선택과 교차하지 않으면
        state[1]++; // 선택된 세그먼트 개수 증가
        state[0] = i + 1; // 마지막 끝 인덱스 업데이트
      }
      sumToState.put(sum, state);

      // 현재 합에 대해 최대 세그먼트 개수 갱신
      maxSegments = Math.max(maxSegments, state[1]);
    }

    return maxSegments;
  }

  // 테스트를 위한 메인 메서드
  public static void main(String[] args) {
    Solution01_1 solution = new Solution01_1();

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