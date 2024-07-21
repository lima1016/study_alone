package com.lima.study_alone.codingtest.cafe24_20240721;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test02 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");

    int currentPlan = Integer.parseInt(input[0]);
    int dataUsage = Integer.parseInt(input[1]);

    int[] plans = {29900, 34900, 39900, 49900, 59900, 69900};
    int[] dataLimits = {300, 1000, 2000, 6000, 11000, 9999999};

    int recommendPlan = findRecommendPlan(dataUsage, plans, dataLimits);
    int currentCost = calculateCost(currentPlan, dataUsage, plans, dataLimits);
    int recommendCost = calculateCost(recommendPlan, dataUsage, plans, dataLimits);

    System.out.println(recommendPlan + " " + currentCost + " " + recommendCost);
  }

  private static int findRecommendPlan(int dataUsage, int[] plans, int[] dataLimits) {
    int recommendPlan = plans[0];
    int minCost = 9999999;

    for (int i = 0; i < plans.length; i++) {
      int cost = calculateCost(plans[i], dataUsage, plans, dataLimits);
      if (cost < minCost) {
        minCost = cost;
        recommendPlan = plans[i];
      }
    }

    return recommendPlan;
  }

  private static int calculateCost(int plan, int dataUsage, int[] plans, int[] dataLimits) {
    int planIndex = Arrays.binarySearch(plans, plan);
    int dataLimit = dataLimits[planIndex];

    if (dataUsage <= dataLimit) {
      return plan;
    } else {
      int extraData = dataUsage - dataLimit;
      // 20원/1MB, 최대 25,000원.
      int extraCost = Math.min(extraData * 20, 25000);

      // 총액은 180,000원을 초과할 수 없음.
      return Math.min(plan + extraCost, 180000);
    }
  }
}
