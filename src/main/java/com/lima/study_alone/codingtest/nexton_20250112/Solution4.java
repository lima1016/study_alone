package com.lima.study_alone.codingtest.nexton_20250112;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
알고리즘 로직 (getMinMachines 메소드):

입력 리스트를 새로운 ArrayList로 복사하여 원본을 보존합니다.
Collections.sort()를 사용하여 시작 시간과 종료 시간을 각각 정렬합니다.
두 포인터 방식으로 시간순으로 진행하면서:

시작 시간이 종료 시간보다 이르면 기계 수를 증가
종료 시간이 시작 시간보다 이르거나 같으면 기계 수를 감소
과정 중 필요했던 최대 기계 수를 반환합니다.
메인 메소드:
주어진 샘플 입력을 테스트합니다.
실행 결과를 자세히 출력합니다.
테스트 케이스:
다양한 시나리오를 검증하기 위한 추가 테스트 케이스를 포함:
겹치지 않는 작업들
모든 작업이 겹치는 경우
일부 작업이 겹치는 경우

시간 복잡도: O(n log n) - 정렬에 의해 결정됩니다.
공간 복잡도: O(n) - 입력 리스트의 복사본이 필요합니다.
실행하면 샘플 입력에 대해 3을 반환하며, 이는 모든 작업을 처리하기 위해 필요한 최소 기계의 수입니다. 추가 테스트 케이스들도 각각의 시나리오에 대해 올바른 결과를 반환하는 것을 확인할 수 있습니다.
 */
public class Solution4 {
  public static int getMinMachines(List<Integer> start, List<Integer> end) {
    // 입력 리스트를 새로운 ArrayList로 복사
    List<Integer> startTimes = new ArrayList<>(start);
    List<Integer> endTimes = new ArrayList<>(end);

    // 시작 시간과 종료 시간을 각각 정렬
    Collections.sort(startTimes);
    Collections.sort(endTimes);

    int machines = 0;      // 현재 필요한 기계 수
    int maxMachines = 0;   // 최대로 필요했던 기계 수
    int i = 0, j = 0;      // 시작과 종료 시간 리스트의 인덱스

    // 두 포인터를 사용하여 동시에 실행 중인 작업 수를 계산
    while (i < startTimes.size() && j < endTimes.size()) {
      // 중요: 시작 시간이 종료 시간보다 작거나 같을 때도 새로운 기계가 필요
      if (startTimes.get(i) <= endTimes.get(j)) {
        machines++;    // 새로운 작업 시작
        maxMachines = Math.max(maxMachines, machines);
        i++;
      } else {
        machines--;    // 작업 종료
        j++;
      }
    }

    return maxMachines;
  }

  public static void main(String[] args) {
    // Sample Case 0 테스트
    System.out.println("=== Sample Case 0 ===");
    List<Integer> start0 = Arrays.asList(2, 1, 5, 5, 8);
    List<Integer> end0 = Arrays.asList(5, 3, 8, 6, 12);
    System.out.println("Result: " + getMinMachines(start0, end0));  // 예상 결과: 3

    // Sample Case 1 테스트
    System.out.println("\n=== Sample Case 1 ===");
    List<Integer> start1 = Arrays.asList(2, 2, 2, 2);
    List<Integer> end1 = Arrays.asList(5, 5, 5, 5);
    System.out.println("Result: " + getMinMachines(start1, end1));  // 예상 결과: 4
  }
}
