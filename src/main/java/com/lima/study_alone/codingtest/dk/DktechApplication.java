package com.lima.study_alone.codingtest.dk;

import java.io.PrintStream;
import java.util.Scanner;

public class DktechApplication {

  static {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    long totalStartTime = System.currentTimeMillis();
    DataManager manager = DataManager.getInstance();

    manager.generatePairs();
    manager.saveToFile();

    System.out.print("검색할 문자열 또는 숫자 입력: ");
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine().trim();

    if (input.matches("\\d+")) {
      manager.searchByNumber(Integer.parseInt(input));
    } else {
      manager.searchByPrefix(input);
    }

    System.out.printf("총 실행 시간: %d ms%n", System.currentTimeMillis() - totalStartTime);
  }
}
