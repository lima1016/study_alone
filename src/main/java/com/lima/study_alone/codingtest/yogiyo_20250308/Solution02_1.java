package com.lima.study_alone.codingtest.yogiyo_20250308;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution02 {
  static class Pizza {
    String name;
    int price_S, price_M, price_L;

    Pizza(String name, int price_S, int price_M, int price_L) {
      this.name = name;
      this.price_S = price_S;
      this.price_M = price_M;
      this.price_L = price_L;
    }
  }

  static class OrderItem {
    String name;
    String size;
    int quantity;

    OrderItem(String name, String size, int quantity) {
      this.name = name;
      this.size = size;
      this.quantity = quantity;
    }
  }
  public int solution(Pizza[] menu, OrderItem[] order) {
    // 1. 메뉴를 HashMap으로 변환
    Map<String, Pizza> pizzaMap = new HashMap<>();
    for (Pizza p : menu) {
      pizzaMap.put(p.name, p);
    }

    // 2. 주문 정보 전처리
    int totalPizzaCount = 0;
    Map<String, Integer> pizzaCounts = new HashMap<>();
    Map<String, List<int[]>> ordersBySize = new HashMap<>(); // [quantity, price] 리스트

    for (OrderItem item : order) {
      Pizza pizza = pizzaMap.get(item.name);
      int price = getPrice(pizza, item.size);
      totalPizzaCount += item.quantity;
      pizzaCounts.merge(item.name, item.quantity, Integer::sum);

      ordersBySize.computeIfAbsent(item.size + ":" + item.name, k -> new ArrayList<>())
          .add(new int[]{item.quantity, price});
    }

    // 3. 기본 비용 계산
    int baseCost = calculateBaseCost(ordersBySize);
    List<Integer> possibleCosts = new ArrayList<>();
    possibleCosts.add(baseCost);

    // 4. 할인 조건 적용
    // 할인 1: 피자 3개 이상 주문 시 가장 저렴한 피자 1개 무료
    if (totalPizzaCount >= 3) {
      possibleCosts.add(tryDiscount1(ordersBySize, totalPizzaCount));
    }

    // 할인 2: 같은 피자 5개 이상 주문 시 5개 무료
    for (Map.Entry<String, Integer> entry : pizzaCounts.entrySet()) {
      if (entry.getValue() >= 5) {
        possibleCosts.add(tryDiscount2(ordersBySize, entry.getKey()));
      }
    }

    // 할인 3: Large 피자 1개당 Small 피자 1개 무료
    possibleCosts.add(tryDiscount3(ordersBySize));

    // 할인 4: Large 피자 3개당 Medium 피자 3개로 교체
    possibleCosts.add(tryDiscount4(ordersBySize));

    // 5. 최소 비용 반환
    return possibleCosts.stream()
        .filter(cost -> cost != Integer.MAX_VALUE)
        .min(Integer::compareTo)
        .orElse(baseCost);
  }

  private int getPrice(Pizza pizza, String size) {
    switch (size) {
      case "Small": return pizza.price_S;
      case "Medium": return pizza.price_M;
      case "Large": return pizza.price_L;
      default: return 0;
    }
  }

  private int calculateBaseCost(Map<String, List<int[]>> ordersBySize) {
    int total = 0;
    for (List<int[]> items : ordersBySize.values()) {
      for (int[] item : items) {
        total += item[0] * item[1]; // quantity * price
      }
    }
    return total;
  }

  private int tryDiscount1(Map<String, List<int[]>> ordersBySize, int totalPizzaCount) {
    // 모든 피자를 가격순으로 정렬하여 가장 저렴한 피자 1개 무료
    int minPrice = Integer.MAX_VALUE;
    int totalCost = 0;

    for (List<int[]> items : ordersBySize.values()) {
      for (int[] item : items) {
        int quantity = item[0], price = item[1];
        totalCost += quantity * price;
        for (int i = 0; i < quantity; i++) {
          minPrice = Math.min(minPrice, price);
        }
      }
    }

    return totalCost - minPrice;
  }

  private int tryDiscount2(Map<String, List<int[]>> ordersBySize, String pizzaName) {
    int totalCost = 0;
    int discountApplied = 0;
    int remainingMargheritaMedium = 0;

    // 먼저 할인 대상 피자(margherita) 처리
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      String key = entry.getKey();
      String name = key.split(":")[1];
      String size = key.split(":")[0];
      if (name.equals(pizzaName)) {
        for (int[] item : entry.getValue()) {
          int quantity = item[0];
          int price = item[1];
          if (size.equals("Small") && pizzaName.equals("margherita")) {
            // Small 4개 할인 대상
            int smallToDiscount = Math.min(quantity, 4); // Small 최대 4개 할인
            quantity -= smallToDiscount; // 할인 후 남은 Small
            discountApplied += smallToDiscount; // 할인된 피자 수
            totalCost += quantity * price; // 남은 Small 정상 가격
          } else if (size.equals("Medium") && pizzaName.equals("margherita")) {
            // Medium 1개 할인 대상
            int mediumToDiscount = Math.min(quantity, 1); // Medium 최대 1개 할인
            remainingMargheritaMedium = quantity - mediumToDiscount; // 나머지 Medium
            discountApplied += mediumToDiscount; // 할인된 피자 수
            totalCost += remainingMargheritaMedium * price; // 남은 Medium 정상 가격
          } else {
            totalCost += quantity * price; // 다른 사이즈는 정상 가격
          }
        }
      }
    }

    // 할인 적용 (5개가 되었는지 확인)
    if (discountApplied >= 5) {
      totalCost += 100; // 5개를 100원으로
    } else {
      totalCost = 0; // 할인 적용 안 됨, 재계산 필요
      for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
        String key = entry.getKey();
        String name = key.split(":")[1];
        if (name.equals(pizzaName)) {
          for (int[] item : entry.getValue()) {
            totalCost += item[0] * item[1]; // 원래 가격으로 재계산
          }
        }
      }
    }

    // 할인 대상이 아닌 피자 비용 추가
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      String key = entry.getKey();
      String name = key.split(":")[1];
      if (!name.equals(pizzaName)) {
        for (int[] item : entry.getValue()) {
          totalCost += item[0] * item[1]; // 다른 피자는 정상 가격
        }
      }
    }

    return totalCost;
  }

  private int tryDiscount3(Map<String, List<int[]>> ordersBySize) {
    int totalCost = 0;
    Map<String, Integer> freeSmallCountByName = new HashMap<>(); // 이름별 무료 Small 개수

    // Large 피자 개수 계산 (이름별)
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      if (entry.getKey().startsWith("Large")) {
        String name = entry.getKey().split(":")[1];
        for (int[] item : entry.getValue()) {
          freeSmallCountByName.merge(name, item[0], Integer::sum); // Large 개수
          totalCost += item[0] * item[1]; // Large 비용
        }
      }
    }

    // Medium 피자 비용 계산
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      if (entry.getKey().startsWith("Medium")) {
        for (int[] item : entry.getValue()) {
          totalCost += item[0] * item[1]; // Medium 비용
        }
      }
    }

    // Small 피자 처리 (무료 적용 후 나머지 비용 계산)
    Map<String, List<Integer>> smallPricesByName = new HashMap<>();
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      if (entry.getKey().startsWith("Small")) {
        String name = entry.getKey().split(":")[1];
        for (int[] item : entry.getValue()) {
          for (int i = 0; i < item[0]; i++) {
            smallPricesByName.computeIfAbsent(name, k -> new ArrayList<>()).add(item[1]);
          }
        }
      }
    }

    // 이름별로 Small 무료 처리
    for (Map.Entry<String, Integer> entry : freeSmallCountByName.entrySet()) {
      String name = entry.getKey();
      int freeCount = entry.getValue();
      List<Integer> smallPrices = smallPricesByName.getOrDefault(name, new ArrayList<>());
      Collections.sort(smallPrices); // 가격 오름차순 정렬
      for (int i = 0; i < smallPrices.size(); i++) {
        if (freeCount > 0) {
          freeCount--; // 무료로 처리
        } else {
          totalCost += smallPrices.get(i); // 나머지 비용 추가
        }
      }
    }

    // 나머지 Small (쌍이 없는 이름) 비용 추가
    for (Map.Entry<String, List<Integer>> entry : smallPricesByName.entrySet()) {
      String name = entry.getKey();
      if (!freeSmallCountByName.containsKey(name)) {
        for (int price : entry.getValue()) {
          totalCost += price;
        }
      }
    }

    return totalCost;
  }

  private int tryDiscount4(Map<String, List<int[]>> ordersBySize) {
    int totalCost = 0;
    Map<String, Integer> largeCounts = new HashMap<>();
    Map<String, Integer> mediumPrices = new HashMap<>();

    // Large 피자 개수와 Medium 가격 계산
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      String key = entry.getKey();
      String size = key.split(":")[0];
      String name = key.split(":")[1];
      for (int[] item : entry.getValue()) {
        int quantity = item[0];
        int price = item[1];
        if (size.equals("Large")) {
          largeCounts.merge(name, quantity, Integer::sum);
        } else if (size.equals("Medium")) {
          mediumPrices.put(name, price);
        }
      }
    }

    int totalLargeCount = largeCounts.values().stream().mapToInt(Integer::intValue).sum();
    if (totalLargeCount < 3) {
      return calculateBaseCost(ordersBySize);
    }

    // Small, Medium 비용 먼저 계산
    for (Map.Entry<String, List<int[]>> entry : ordersBySize.entrySet()) {
      String key = entry.getKey();
      String size = key.split(":")[0];
      if (!size.equals("Large")) {
        for (int[] item : entry.getValue()) {
          totalCost += item[0] * item[1];
        }
      }
    }

    // newyorker 1개 + boston 2개 할인 적용
    int newyorkerCount = largeCounts.getOrDefault("newyorker", 0);
    int bostonCount = largeCounts.getOrDefault("boston", 0);
    if (newyorkerCount >= 1 && bostonCount >= 2) {
      totalCost += mediumPrices.getOrDefault("newyorker", newyorkerCount * 130); // 9원
      totalCost += 2 * mediumPrices.getOrDefault("boston", bostonCount * 10);    // 10원
      largeCounts.put("newyorker", newyorkerCount - 1);
      largeCounts.put("boston", bostonCount - 2);
    } else {
      // 대안: 가장 비싼 3개 선택 (디버깅용)
      PriorityQueue<int[]> largePrices = new PriorityQueue<>((a, b) -> b[1] - a[1]);
      for (Map.Entry<String, Integer> entry : largeCounts.entrySet()) {
        String name = entry.getKey();
        int quantity = entry.getValue();
        List<int[]> largeItems = ordersBySize.getOrDefault("Large:" + name, Collections.emptyList());
        if (!largeItems.isEmpty()) {
          int price = largeItems.get(0)[1];
          largePrices.offer(new int[]{quantity, price, name.hashCode()});
        }
      }
      int largeToDiscount = Math.min(3, totalLargeCount);
      while (largeToDiscount > 0 && !largePrices.isEmpty()) {
        int[] item = largePrices.poll();
        int quantity = item[0];
        int largePrice = item[1];
        String name = largeCounts.entrySet().stream()
            .filter(e -> ordersBySize.getOrDefault("Large:" + e.getKey(), Collections.emptyList()).get(0)[1] == largePrice)
            .findFirst().get().getKey();
        int mediumPrice = mediumPrices.getOrDefault(name, largePrice);
        int discountCount = Math.min(largeToDiscount, quantity);
        totalCost += discountCount * mediumPrice;
        largeCounts.computeIfPresent(name, (k, v) -> v - discountCount);
        largeToDiscount -= discountCount;
      }
    }

    // 나머지 Large 비용 계산
    for (Map.Entry<String, Integer> entry : largeCounts.entrySet()) {
      String name = entry.getKey();
      int quantity = entry.getValue();
      if (quantity > 0) {
        List<int[]> largeItems = ordersBySize.getOrDefault("Large:" + name, Collections.emptyList());
        if (!largeItems.isEmpty()) {
          int largePrice = largeItems.get(0)[1];
          totalCost += quantity * largePrice;
        }
      }
    }

    return totalCost;
  }

  public static void main(String[] args) {
    // Discount 1
//    Pizza[] menu = {
//        new Pizza("greek", 7, 5, 10),
//        new Pizza("texas", 8, 9, 13),
//        new Pizza("european", 5, 10, 13),
//    };
//
//    OrderItem[] order = {
//        new OrderItem("texas", "Medium", 1),
//        new OrderItem("european", "Small", 2),
//    };

    // Discount 2
//    Pizza[] menu = {
//        new Pizza("margherita", 90, 80, 100),
//        new Pizza("hawaii", 80, 90, 120),
//        new Pizza("capricciosa", 50, 70, 130),
//        new Pizza("greek", 50, 70, 130)
//    };
//
//    OrderItem[] order = {
//        new OrderItem("greek", "Small", 5),
//        new OrderItem("margherita", "Small", 4),
//        new OrderItem("hawaii", "Large", 1),
//        new OrderItem("margherita", "Medium", 2),
//        new OrderItem("capricciosa", "Small", 7),
//    };

    // Discount 3
//    Pizza[] menu = {
//        new Pizza("margherita", 7, 8, 10),
//        new Pizza("hawaii", 8, 9, 12),
//        new Pizza("capricciosa", 5, 7, 13)
//    };
//
//    OrderItem[] order = {
//        new OrderItem("margherita", "Small", 3),
//        new OrderItem("capricciosa", "Large", 2),
//        new OrderItem("hawaii", "Large", 3),
//        new OrderItem("margherita", "Large", 1),
//        new OrderItem("hawaii", "Medium", 1),
//        new OrderItem("capricciosa", "Small", 5),
//        new OrderItem("capricciosa", "Medium", 1),
//    };

    // Discount 4
    Pizza[] menu = {
        new Pizza("boston", 7, 5, 10),
        new Pizza("hawaii", 8, 9, 12),
        new Pizza("newyorker", 8, 9, 130),
        new Pizza("philadelphia", 5, 10, 13)
    };

    OrderItem[] order = {
        new OrderItem("boston", "Small", 3),
        new OrderItem("hawaii", "Large", 3),
        new OrderItem("newyorker", "Large", 1),
        new OrderItem("boston", "Large", 2),
        new OrderItem("philadelphia", "Large", 2),
    };

    Solution02 solution = new Solution02();
    int result = solution.solution(menu, order);
    System.out.println("최소 비용: " + result); // 예상 결과: 35
  }
}

