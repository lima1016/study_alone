package com.lima.study_alone.codingtest.Ignight;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Solution3_1 {
  public int[] solution(String[] record) {
    // Arrays to store purchase and sale information
    java.util.List<Integer> purchasePrices = new java.util.ArrayList<>();
    java.util.List<Integer> purchaseQuantities = new java.util.ArrayList<>();
    java.util.List<Integer> salePrices = new java.util.ArrayList<>();
    java.util.List<Integer> saleQuantities = new java.util.ArrayList<>();

    // Parse the record
    for (String r : record) {
      String[] parts = r.split(" ");
      char type = parts[0].charAt(1); // 'P' or 'S'
      int price = Integer.parseInt(parts[1]);
      int quantity = Integer.parseInt(parts[2].replace("\"", "").replace("]", ""));

      if (type == 'P') {
        purchasePrices.add(price);
        purchaseQuantities.add(quantity);
      } else if (type == 'S') {
        salePrices.add(price);
        saleQuantities.add(quantity);
      }
    }

    // Calculate sales amount using FIFO
    int fifoSalesAmount = calculateFIFO(purchasePrices, purchaseQuantities, saleQuantities);

    // Calculate sales amount using LIFO
    int lifoSalesAmount = calculateLIFO(purchasePrices, purchaseQuantities, saleQuantities);

    return new int[] {fifoSalesAmount, lifoSalesAmount};
  }

  private int calculateFIFO(java.util.List<Integer> purchasePrices,
      java.util.List<Integer> purchaseQuantities,
      java.util.List<Integer> saleQuantities) {
    int totalSales = 0;
    int currentPurchaseIndex = 0;
    int remainingPurchaseQuantity = purchaseQuantities.get(0);

    for (int saleQuantity : saleQuantities) {
      int remainingSaleQuantity = saleQuantity;

      while (remainingSaleQuantity > 0) {
        if (remainingPurchaseQuantity >= remainingSaleQuantity) {
          // We have enough items in current purchase to cover this sale
          totalSales += purchasePrices.get(currentPurchaseIndex) * remainingSaleQuantity;
          remainingPurchaseQuantity -= remainingSaleQuantity;
          remainingSaleQuantity = 0;
        } else {
          // We need to use multiple purchases to cover this sale
          totalSales += purchasePrices.get(currentPurchaseIndex) * remainingPurchaseQuantity;
          remainingSaleQuantity -= remainingPurchaseQuantity;
          currentPurchaseIndex++;
          remainingPurchaseQuantity = purchaseQuantities.get(currentPurchaseIndex);
        }
      }
    }

    return totalSales;
  }

  private int calculateLIFO(java.util.List<Integer> purchasePrices,
      java.util.List<Integer> purchaseQuantities,
      java.util.List<Integer> saleQuantities) {
    int totalSales = 0;

    // Create a stack-like structure for LIFO calculation
    int[] remainingQuantities = new int[purchaseQuantities.size()];
    for (int i = 0; i < purchaseQuantities.size(); i++) {
      remainingQuantities[i] = purchaseQuantities.get(i);
    }

    for (int saleQuantity : saleQuantities) {
      int remainingSaleQuantity = saleQuantity;

      for (int i = remainingQuantities.length - 1; i >= 0 && remainingSaleQuantity > 0; i--) {
        if (remainingQuantities[i] >= remainingSaleQuantity) {
          // We have enough items in current purchase to cover this sale
          totalSales += purchasePrices.get(i) * remainingSaleQuantity;
          remainingQuantities[i] -= remainingSaleQuantity;
          remainingSaleQuantity = 0;
        } else if (remainingQuantities[i] > 0) {
          // We need to use multiple purchases to cover this sale
          totalSales += purchasePrices.get(i) * remainingQuantities[i];
          remainingSaleQuantity -= remainingQuantities[i];
          remainingQuantities[i] = 0;
        }
      }
    }

    return totalSales;
  }
}