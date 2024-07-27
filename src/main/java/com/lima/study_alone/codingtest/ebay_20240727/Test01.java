package com.lima.study_alone.codingtest.ebay_20240727;
import java.util.*;

public class Test01 {

  public static void main(String[] args) {
    Test01 main = new Test01();

    int n = 5;
    int[][] orders = {
        {10, 60},
        {15, 30},
        {20, 80},
        {30, 40},
        {35, 70},
        {40, 20}
    };
    System.out.println(main.solution(n, orders));  // 결과는 170이어야 합니다.

    n = 5;
    orders = new int[][]{
        {7, 35},
        {5, 25},
        {3, 15},
        {1, 5}
    };
    System.out.println(main.solution(n, orders));  // 결과는 35이어야 합니다.

    n = 4;
    orders = new int[][]{
        {13, 39},
        {16, 50},
        {50, 17},
        {61, 39},
        {92, 22}
    };
    System.out.println(main.solution(n, orders));  // 결과는 128이어야 합니다.
  }

  public int solution(int n, int[][] orders) {
    Arrays.sort(orders, Comparator.comparingInt(o -> o[0]));
    return maxShipped(orders, n, 0, 0, new HashMap<>());
  }

  private int maxShipped(int[][] orders, int n, int currentIndex, int currentProduction, HashMap<String, Integer> memo) {
    if (currentIndex >= orders.length) {
      return 0;
    }

    String memoKey = currentIndex + "," + currentProduction;
    if (memo.containsKey(memoKey)) {
      return memo.get(memoKey);
    }

    int currentDay = orders[currentIndex][0];
    int requiredQuantity = orders[currentIndex][1];
    int daysToProduce = currentDay - (currentIndex > 0 ? orders[currentIndex - 1][0] : 0);

    // 생산량 갱신
    currentProduction += daysToProduce * n;

    // 납품 함
    int shippedIfTaken = 0;
    if (currentProduction >= requiredQuantity) {
      shippedIfTaken = requiredQuantity + maxShipped(orders, n, currentIndex + 1, currentProduction - requiredQuantity, memo);
    }

    // 납품 안함
    int shippedIfSkipped = maxShipped(orders, n, currentIndex + 1, currentProduction, memo);

    int maxShipped = Math.max(shippedIfTaken, shippedIfSkipped);
    memo.put(memoKey, maxShipped);

    return maxShipped;
  }
}
