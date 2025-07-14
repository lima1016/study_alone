package com.lima.study_alone.codingtest.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution01 {

  // job을 로딩하는 데에 시간이 얼마나 걸릴지 계산 하고 비교.

  //cache에는 최대 job을 3개까지 저정할 수 있음.

  public static void main(String[] args) {
    String csvStr = "3, job1, job2, job3, job2, job4, job3, job1";

    String[] data = csvStr.split(",");

    int cacheSize = Integer.parseInt(data[0].trim());

    List<String> jobs = new ArrayList<>(Arrays.asList(data).subList(1, data.length));

    System.out.println("FIFO: " + testFIFO(cacheSize, jobs));
    System.out.println("LRU: " + testLRU(cacheSize, jobs));
    System.out.println("LFU: " + testLFU(cacheSize, jobs));
  }

  // 가장 먼저 캐시에 들어온 job을 삭제.
  public static int testFIFO(int cacheSize, List<String> jobs) {
    Queue<String> queue = new LinkedList<>();
    Set<String> cache = new HashSet<>();
    int time = 0;
    for (String job : jobs) {
      if (cache.contains(job)) {
        time += 1;
      } else {
        time += 10;
        if (cache.size() >= cacheSize) {
          String removeJob = queue.poll();
          cache.remove(removeJob);
        }
        queue.offer(job);
        cache.add(job);
      }
    }
    return time;
  }

  // 가장 오래전에 사용된 항목을 삭제.
  public static int testLRU(int cacheSize, List<String> jobs) {
    LinkedHashMap<String, Boolean> cache = new LinkedHashMap<>(cacheSize, 0.75f, true);
    int time = 0;

    for (String job : jobs) {
      if (cache.containsKey(job)) {
        time += 1;
        cache.get(job);
      } else {
        time += 10;
        if (cache.size() >= cacheSize) {
          String oldCache = cache.keySet().iterator().next();
          cache.remove(oldCache);
        }
        cache.put(job, true);
      }
    }
    return time;
  }

  // 가장 적게 사용된 job을 삭제.
  public static int testLFU(int cacheSize, List<String> jobs) {
    Map<String, Integer> cache = new HashMap<>();
    int time = 0;

    for (String job : jobs) {
      if (cache.containsKey(job)) {
        time += 1;
        cache.put(job, cache.get(job) + 1);
      } else {
        time += 10;
        if (cache.size() >= cacheSize) {
          String oldCache = cache.keySet().iterator().next();
          cache.remove(oldCache);
        }
        cache.put(job, 1);
      }
    }
    return time;
  }
}























