package com.lima.study_alone.codingtest.yogiyo_20250308;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Solution02_1 {
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
    Map<String, Pizza> pizzaMap = new HashMap<>();
    for (Pizza p : menu) pizzaMap.put(p.name, p);

    int totalCount = 0, baseCost = 0;
    Map<String, List<int[]>> ordersBySize = new HashMap<>();

    for (OrderItem item : order) {
      Pizza pizza = pizzaMap.get(item.name);
      int price = getPrice(pizza, item.size);
      totalCount += item.quantity;
      ordersBySize.computeIfAbsent(item.size + ":" + item.name, k -> new ArrayList<>()).add(new int[]{item.quantity, price});
      baseCost += item.quantity * price;
    }

    List<Integer> costs = new ArrayList<>(List.of(baseCost));
    System.out.println("costs0 >>> " + costs);
    if (totalCount >= 3) costs.add(applyDiscount1(ordersBySize, baseCost));
    System.out.println("costs1 >>> " + costs);
    costs.add(applyDiscount2(ordersBySize, baseCost));
    System.out.println("costs2 >>> " + costs);
    costs.add(applyDiscount3(ordersBySize, baseCost));
    System.out.println("costs3 >>> " + costs);
    costs.add(applyDiscount4(ordersBySize, baseCost));
    System.out.println("costs4 >>> " + costs);

    return Collections.min(costs);
  }

  private int getPrice(Pizza pizza, String size) {
    return switch (size) {
      case "Small" -> pizza.price_S;
      case "Medium" -> pizza.price_M;
      case "Large" -> pizza.price_L;
      default -> 0;
    };
  }

  private int applyDiscount1(Map<String, List<int[]>> orders, int cost) {
    OptionalInt minPriceOptional = orders.values().stream()
        .flatMap(List::stream)
        .mapToInt(item -> item[1])
        .min(); // min()은 OptionalInt를 반환

    // OptionalInt가 값이 있으면 그 값을 사용하고, 없으면 기본값을 반환
    int total = minPriceOptional.isPresent() ? cost - minPriceOptional.getAsInt() : cost;
    return total;
  }


  private int applyDiscount2(Map<String, List<int[]>> orders, int cost) {
    for (String key : orders.keySet()) {
      int totalQty = orders.get(key).stream().mapToInt(o -> o[0]).sum();
      if (totalQty >= 5) {
        int discountedCost = cost - (5 * orders.get(key).get(0)[1]);
        return discountedCost - 90;
      }

    }
    return cost;
  }

  private int applyDiscount3(Map<String, List<int[]>> orders, int cost) {
    int largePizzaCount = orders.entrySet().stream()
        .filter(e -> e.getKey().startsWith("Large"))
        .mapToInt(e -> e.getValue().stream().mapToInt(o -> o[0]).sum())
        .sum();

    return reduceCostForFreeItems(orders, cost, "Small", largePizzaCount);
  }

  private int applyDiscount4(Map<String, List<int[]>> orders, int cost) {
    // Large 피자 개수 계산
    int largeCount = orders.entrySet().stream()
        .filter(e -> e.getKey().startsWith("Large"))
        .mapToInt(e -> e.getValue().stream().mapToInt(o -> o[0]).sum())
        .sum();

    System.out.println("Large pizza count: " + largeCount);

    // Large 3개당 Medium 1개 무료 제공 (할인 가능 개수 계산)
    int exchangeCount = largeCount / 3;
    System.out.println("Free Count (Medium pizzas to be discounted): " + exchangeCount);

    // Medium 피자 없는 경우, 다른 방법으로 할인 적용하도록 변경
    if (exchangeCount > 0) {
      // Medium 피자 없으면 다른 방식으로 할인 적용 (예: Large 피자 자체에 대해 할인 적용)
      cost = applyDiscountForLargePizzas(orders, cost, exchangeCount);
    }

    // Medium 피자 중 무료로 제공할 수 있는 피자 개수를 계산
    return reduceCostForFreeItems(orders, cost, "Medium", exchangeCount);
  }

  private int applyDiscountForLargePizzas(Map<String, List<int[]>> orders, int cost, int exchangeCount) {
    List<Integer> prices = orders.entrySet().stream()
        .filter(e -> e.getKey().startsWith("Large"))
        .flatMap(e -> e.getValue().stream()
            .flatMap(o -> Collections.nCopies(o[0], o[1]).stream()))  // 가격을 복사해서 리스트에 추가
        .sorted()  // 오름차순 정렬
        .toList();

    // 만약 Medium 피자가 없을 경우, Large 피자에서 할인 적용
    if (prices.size() >= exchangeCount) {
      for (int i = 0; i < exchangeCount; i++) {
        cost -= prices.get(i);  // 가장 저렴한 Large 피자부터 할인
      }
    }

    return cost;
  }

  private int reduceCostForFreeItems(Map<String, List<int[]>> orders, int cost, String size, int freeCount) {
    // size에 해당하는 피자 가격 리스트 생성
    List<Integer> prices = orders.entrySet().stream()
        .filter(e -> e.getKey().startsWith(size))
        .flatMap(e -> e.getValue().stream()
            .flatMap(o -> Collections.nCopies(o[0], o[1]).stream()))  // 가격을 복사해서 리스트에 추가
        .sorted()  // 오름차순 정렬
        .toList();

    System.out.println("Available " + size + " Pizza Prices: " + prices);  // 디버깅

    // Medium 피자 가격이 없으면 freeCount는 0으로 설정하여 할인 적용 안 함
    if (prices.isEmpty()) {
      System.out.println("No " + size + " pizzas available for discount.");
      return cost;
    }

    // 가장 저렴한 Medium 피자부터 차감
    for (int i = 0; i < Math.min(freeCount, prices.size()); i++) {
      cost -= prices.get(i);
    }

    return cost;
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

    Solution02_1 solution = new Solution02_1();
    int result = solution.solution(menu, order);
    System.out.println("최소 비용: " + result); // 예상 결과: 35
  }
}

