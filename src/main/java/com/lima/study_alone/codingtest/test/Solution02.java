package com.lima.study_alone.codingtest.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

/*
모든 장비의 "시작 시간"과 "종료 시간"을 타임라인에 기록
시작 시점에는 +1, 종료 시점에는 -1을 기록해서 시간 순으로 정렬된 이벤트 리스트를 만든다.
이 이벤트 리스트를 순서대로 처리하며, 현재 작동 중인 장비의 수를 누적하고, 그 중 가장 큰 순간을 찾아낸다.
이 방법은 스위핑 라인 알고리즘 (sweep line algorithm) 또는 타임라인 누적법이라고 불리는 기법입니다.
O(N log N): 정렬 기반의 효율적인 처리
정확한 시점 파악 가능
시작과 종료만 처리하면 되기 때문에 메모리 효율도 좋음
 */
public class Solution02 {

  public static void main(String[] args) throws IOException {
    String csvData = """
        Machine1, 2021-03-10 14:00:00, 2021-03-10 15:00:00
        Machine1, 2021-03-10 18:00:00, 2021-03-10 21:30:00
        Machine2, 2021-03-10 12:00:00, 2021-03-10 14:30:00
        """;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /*
    TreeMap: 시간 순으로 자동 정렬되며 저장되는 자료구조.
    key: 시간 (LocalDateTime)
    value: 그 시간에 일어난 변화량 (시작이면 +1, 종료면 -1)
     */
    TreeMap<LocalDateTime, Integer> map = new TreeMap<>();

    BufferedReader br = new BufferedReader(new StringReader(csvData));

    String line;
    while ((line = br.readLine()) != null) {
      String[] tokens = line.split(",");
      LocalDateTime start = LocalDateTime.parse(tokens[1].trim(), formatter);
      LocalDateTime end = LocalDateTime.parse(tokens[2].trim(), formatter);

      /*
      시작 시간에는 +1
      종료 시간에는 -1
      getOrDefault는 해당 시간이 이미 존재하는 경우 누적하기 위해 사용
      "장비가 몇 개나 동작 중인가"를 시간 순으로 계산하기 위해서입니다.
       */
      map.put(start, map.getOrDefault(start, 0) + 1);
      map.put(end, map.getOrDefault(end, 0) - 1);
    }

    int currentRunning = 0;
    int maxRunning = 0;
    LocalDateTime maxTime = null;

    for (Map.Entry<LocalDateTime, Integer> entry : map.entrySet()) {
      currentRunning += entry.getValue();
      if (maxRunning < currentRunning) {
        maxRunning = currentRunning;
        maxTime = entry.getKey();
      }
    }

    System.out.println(maxRunning);
    System.out.println(maxTime);
  }
}
