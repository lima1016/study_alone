package com.lima.study_alone.codingtest.nexton_20250112;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
주어진 문제는 라운드 로빈 방식의 로드 밸런싱 알고리즘을 구현하는 것입니다.

요청은 특정 도착 시간과 실행 시간을 가지며, 요청은 가능한 서버 중 가장 낮은 인덱스의 서버에 할당됩니다.
요청을 처리할 서버가 없으면 요청은 드롭됩니다
 */
public class Solution6 {
  public static List<Integer> getServerIndex(int n, List<Integer> arrival, List<Integer> burstTime) {
    // 각 요청을 (도착 시간, 실행 시간, 요청 인덱스)로 묶는다
    List<int[]> requests = new ArrayList<>();
    for (int i = 0; i < arrival.size(); i++) {
      requests.add(new int[]{arrival.get(i), burstTime.get(i), i});
    }

    // 도착 시간을 기준으로 요청을 정렬한다
    requests.sort(Comparator.comparingInt(a -> a[0]));

    // 각 서버의 사용 가능 시간을 추적
    int[] serverAvailableTime = new int[n];
    int[] result = new int[arrival.size()];

    for (int[] request : requests) {
      int requestTime = request[0];
      int burst = request[1];
      int originalIndex = request[2];
      int assignedServer = -1;

      // 서버 순회하며 사용 가능한 서버를 찾는다
      for (int j = 0; j < n; j++) {
        if (serverAvailableTime[j] <= requestTime) {
          assignedServer = j;
          break;
        }
      }

      if (assignedServer != -1) {
        // 서버 할당 및 사용 가능 시간 갱신
        serverAvailableTime[assignedServer] = requestTime + burst;
      }

      // 결과 저장 (1-based index)
      result[originalIndex] = assignedServer == -1 ? -1 : assignedServer + 1;
    }

    // 결과를 리스트로 반환
    List<Integer> finalResult = new ArrayList<>();
    for (int r : result) {
      finalResult.add(r);
    }
    return finalResult;
  }

  public static void main(String[] args) {
    // 샘플 입력 0
    int n = 4;
    List<Integer> arrival = Arrays.asList(3, 5, 1, 6, 8);
    List<Integer> burstTime = Arrays.asList(9, 2, 10, 4, 5);

    // 함수 호출 및 결과 출력
    List<Integer> result = getServerIndex(n, arrival, burstTime);
    System.out.println(result); // [2, 3, 1, 4, 3]

    // 샘플 입력 1
    n = 2;
    arrival = Arrays.asList(2, 2, 2, 3);
    burstTime = Arrays.asList(1, 3, 1, 2);

    result = getServerIndex(n, arrival, burstTime);
    System.out.println(result); // [1, 2, -1, 1]
  }
}
