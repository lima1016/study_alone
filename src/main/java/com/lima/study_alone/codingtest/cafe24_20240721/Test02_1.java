package com.lima.study_alone.codingtest.cafe24_20240721;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Test02_1 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input = br.readLine().split(" ");

    int currentPlanCost = Integer.parseInt(input[0]);
    int dataUsed = Integer.parseInt(input[1]);

    // 요금제 정보 (가격, 제공 데이터량)
    int[][] plans = {
        {29900, 300},
        {34900, 1000},
        {39900, 2000},
        {49900, 6000},
        {59900, 11000},
        {69900, Integer.MAX_VALUE} // 무제한 요금제
    };

    int minCost = Integer.MAX_VALUE;
    int recommendedPlanCost = 0;

    // 현재 요금제로 사용 시 월 요금 계산
    int currentPlanBaseData = 0;
    for (int[] plan : plans) {
      if (plan[0] == currentPlanCost) {
        currentPlanBaseData = plan[1];
        break;
      }
    }

    int currentTotalCost = calculateTotalCost(currentPlanCost, currentPlanBaseData, dataUsed);

    // 추천 요금제 계산
    for (int[] plan : plans) {
      int baseCost = plan[0];
      int baseData = plan[1];

      int totalCost = calculateTotalCost(baseCost, baseData, dataUsed);
      if (totalCost < minCost) {
        minCost = totalCost;
        recommendedPlanCost = baseCost;
      }
    }

    System.out.println(recommendedPlanCost + " " + currentTotalCost + " " + minCost);
  }

  private static int calculateTotalCost(int baseCost, int baseData, int dataUsed) {
    if (dataUsed <= baseData) {
      return baseCost;
    } else {
      if (baseCost == 69900) {
        return baseCost;
      } else {
        int extraData = dataUsed - baseData;
        int extraCost = extraData * 20;
        int totalCost = baseCost + extraCost;

        if (extraData <= 5000) {
          totalCost = Math.min(totalCost, baseCost + 25000);
        } else {
          totalCost = Math.min(totalCost, baseCost + 25000 + (extraData - 5000) * 20);
        }

        return Math.min(totalCost, 180000);
      }
    }
  }
}
