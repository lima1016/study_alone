package com.lima.study_alone.codingtest.dk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

public class DataManager {

  @Getter
  private static final DataManager instance = new DataManager();
  private final Map<String, Integer> stringNumberMap = new ConcurrentHashMap<>();
  private static final String DATA_FILE = "string_number_data.txt";
  private static final int TARGET_COUNT = 7_000_000;

  private DataManager() {
  }

  public void generatePairs() {
    long startTime = System.currentTimeMillis();
    int cpuCores = Runtime.getRuntime().availableProcessors();
    int threadCount = Math.max(2, cpuCores / 2);
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    AtomicInteger counter = new AtomicInteger(0);
    StringGenerator generator = new RandomAlphabetGenerator();

    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        Random random = new Random();
        while (counter.get() < TARGET_COUNT) {
          String key = generator.generate(random.nextInt(4) + 1);
          int value = random.nextInt(9999) + 1;
          if (stringNumberMap.putIfAbsent(key, value) == null) {
            counter.incrementAndGet();
          }
        }
      });
    }

    executor.shutdown();
    try {
      executor.awaitTermination(5, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      System.err.println("스레드 대기 오류: " + e.getMessage());
    }
    System.out.printf("데이터 생성 시간: %d ms%n", System.currentTimeMillis() - startTime);
  }

  public void saveToFile() {
    long startTime = System.currentTimeMillis();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
      stringNumberMap.entrySet().stream()
          .sorted(Map.Entry.comparingByKey())
          .forEach(entry -> {
            try {
              writer.write(entry.getKey() + " → " + entry.getValue());
              writer.newLine();
            } catch (IOException e) {
              System.err.println("파일 쓰기 오류: " + e.getMessage());
            }
          });
    } catch (IOException e) {
      System.err.println("파일 저장 오류: " + e.getMessage());
    }
    System.out.printf("파일 저장 시간: %d ms%n", System.currentTimeMillis() - startTime);
  }

  public void searchByPrefix(String prefix) {
    long startTime = System.currentTimeMillis();
    List<String> mappings = new ArrayList<>();
    Map<Integer, Integer> numberCount = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
      mappings = reader.lines()
          .map(line -> line.split(" → "))
          .filter(parts -> parts.length == 2 && prefix.startsWith(parts[0]))
          .peek(parts -> numberCount.merge(Integer.parseInt(parts[1]), 1, Integer::sum))
          .map(parts -> parts[0] + " → " + parts[1])
          .toList();
    } catch (IOException e) {
      System.err.println("문자 검색 오류: " + e.getMessage());
    }

    if (mappings.isEmpty()) {
      System.out.println("해당 문자에 매칭된 숫자 없음");
    } else {
      mappings.forEach(System.out::println);
      numberCount.forEach((num, count) -> System.out.printf("%d : %d개\n", num, count));
    }
    System.out.printf("문자 검색 시간: %d ms%n", System.currentTimeMillis() - startTime);
  }

  public void searchByNumber(int number) {
    long startTime = System.currentTimeMillis();
    List<String> result = new ArrayList<>();
    int count = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
      for (String line; (line = reader.readLine()) != null; ) {
        String[] parts = line.split(" → ");
        if (parts.length == 2) {
          int value = Integer.parseInt(parts[1]);
          if (value == number) {
            result.add(parts[0] + " → " + value);
            count++;
          }
        }
      }
    } catch (IOException e) {
      System.err.println("숫자 검색 오류: " + e.getMessage());
    }

    if (result.isEmpty()) {
      System.out.println("해당 숫자에 매칭된 문자열 없음");
    } else {
      result.forEach(System.out::println);
      System.out.printf("%d : %d개\n", number, count);
    }
    System.out.printf("숫자 검색 시간: %d ms%n", System.currentTimeMillis() - startTime);
  }
}