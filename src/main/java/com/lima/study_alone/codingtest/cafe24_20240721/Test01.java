package com.lima.study_alone.codingtest.cafe24_20240721;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test01 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 첫번째 출력값 받기.
    String[] firstInput = br.readLine().split(" ");
    int N = Integer.parseInt(firstInput[0]);
    int T = Integer.parseInt(firstInput[1]);

    // 두번째 출력값 받기.
    String[] secondInout = br.readLine().split(" ");
    int[] dice = new int[N];
    for (int i = 0; i < N; i++) {
      dice[i] = Integer.parseInt(secondInout[i]);
    }

    int[] setDice = setArray(dice);
    Arrays.sort(setDice);

    int minValue = setDice[0];
    int maxValue = setDice[setDice.length - 1];

    int minCount = calculateMinRollCount(minValue, T);
    int maxCount = calculateMaxRollCount(maxValue, T);

    System.out.println(maxCount + " " + minCount);
  }

  private static int[] setArray(int[] dice) {
    Set<Integer> uniqueValuesSet = new HashSet<>();
    for (int num : dice) {
      uniqueValuesSet.add(num);
    }

    return uniqueValuesSet.stream().mapToInt(Integer::intValue).toArray();
  }

  private static int calculateMinRollCount(int minValue, int T) {
    int sum = 0;
    int rolls = 0;
    while (sum < T) {
      sum += minValue;
      rolls++;
    }
    return rolls;
  }

  private static int calculateMaxRollCount(int maxValue, int T) {
    int sum = 0;
    int rolls = 0;
    while (sum < T) {
      sum += maxValue;
      rolls++;
    }
    return rolls;
  }
}
