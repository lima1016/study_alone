package com.lima.study_alone.codingtest.Ignight;

import java.util.*;

public class Solution1 {
  public int[] solution(String[] record) {
    // Lists to store purchase information
    List<Integer> buyPrices = new ArrayList<>();
    List<Integer> buyQty = new ArrayList<>();

    // Lists to track FIFO and LIFO sales
    int fifoSales = 0;
    int lifoSales = 0;

    // For FIFO and LIFO: track remaining quantities
    List<Integer> fifoLeftQty = new ArrayList<>();
    List<Integer> lifoLeftQty = new ArrayList<>();

    // Process each record in order
    for (String r : record) {
      r = r.replace("[", "").replace("]", "").replace("\"", "");
      String[] parts = r.split(" ");

      char type = parts[0].charAt(0); // 'P' or 'S'
      int price = Integer.parseInt(parts[1]);
      int qty = Integer.parseInt(parts[2]);

      if (type == 'P') {
        // Add new purchase
        buyPrices.add(price);
        buyQty.add(qty);
        fifoLeftQty.add(qty);
        lifoLeftQty.add(qty);
      } else if (type == 'S') {
        // Process a sale using FIFO
        int saleQtyLeft = qty;
        int currentBuyIndex = 0;

        while (saleQtyLeft > 0 && currentBuyIndex < fifoLeftQty.size()) {
          int buyQtyLeft = fifoLeftQty.get(currentBuyIndex);
          if (buyQtyLeft >= saleQtyLeft) {
            fifoSales += buyPrices.get(currentBuyIndex) * saleQtyLeft;
            fifoLeftQty.set(currentBuyIndex, buyQtyLeft - saleQtyLeft);
            saleQtyLeft = 0;
          } else if (buyQtyLeft > 0) {
            fifoSales += buyPrices.get(currentBuyIndex) * buyQtyLeft;
            saleQtyLeft -= buyQtyLeft;
            fifoLeftQty.set(currentBuyIndex, 0);
            currentBuyIndex++;
          } else {
            currentBuyIndex++;
          }
        }

        // Process a sale using LIFO
        saleQtyLeft = qty;
        for (int i = lifoLeftQty.size() - 1; i >= 0 && saleQtyLeft > 0; i--) {
          int buyQtyLeft = lifoLeftQty.get(i);
          if (buyQtyLeft >= saleQtyLeft) {
            lifoSales += buyPrices.get(i) * saleQtyLeft;
            lifoLeftQty.set(i, buyQtyLeft - saleQtyLeft);
            saleQtyLeft = 0;
          } else if (buyQtyLeft > 0) {
            lifoSales += buyPrices.get(i) * buyQtyLeft;
            saleQtyLeft -= buyQtyLeft;
            lifoLeftQty.set(i, 0);
          }
        }
      }
    }

    return new int[] {fifoSales, lifoSales};
  }

  // Main method for testing
  public static void main(String[] args) {
    Solution1 solution = new Solution1();

    // Test cases from the problem
    String[][] testCases = {
        {"P 300 6", "P 500 3", "S 1000 4", "P 600 2", "S 1200 1"},
        {"P 300 6", "P 500 3", "S 1000 4", "P 600 1", "S 1200 2"},
        {"P 100 4", "P 300 9", "S 1000 7", "P 1000 8", "S 700 7", "S 700 3"}
    };

    int[][] expectedResults = {
        {1500, 2400},
        {1800, 2700},
        {7100, 10700}
    };

    // Run tests
    for (int i = 0; i < testCases.length; i++) {
      int[] result = solution.solution(testCases[i]);

      System.out.println("Test Case #" + (i+1));
      System.out.println("Input: " + Arrays.toString(testCases[i]));
      System.out.println("Result: " + Arrays.toString(result));
      System.out.println("Expected: " + Arrays.toString(expectedResults[i]));

      // Check if the results match the expected values
      boolean isCorrect = result[0] == expectedResults[i][0] && result[1] == expectedResults[i][1];
      System.out.println("Passed: " + isCorrect);
      System.out.println();
    }
  }
}