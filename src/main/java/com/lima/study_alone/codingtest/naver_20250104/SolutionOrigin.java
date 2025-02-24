package com.lima.study_alone.codingtest.naver_20250104;

/*
  피자 주문
  할인 1: 3개 사면 가장 싼 게 무료!
  할인 2: 5개 구매시 100원
  할인 3: 대형 피자 한판을 주문하면 소형피자 한판 무료
  할인 4: 대형 3개를 구매하면 중형가격으로 3개를 지불!

  목표
  피자 가게가 제공하는 할인 중 하나만 활용해 주문의 총 비용을 최소화 하는것.

  Every (name, size) pair appears in the order array at most once.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionOrigin {

  static class Pizza {

    String name;
    int price_S;
    int price_M;
    int price_L;

    public Pizza(String name, int price_S, int price_M, int price_L) {
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

    public OrderItem(String name, String size, int quantity) {
      this.name = name;
      this.size = size;
      this.quantity = quantity;
    }
  }

  public int solution(Pizza[] menu, OrderItem[] order) {
    Map<String, Pizza> pizzaMap = new HashMap<>();
    for (Pizza p : menu) {
      pizzaMap.put(p.name, p);
    }

    int baseCost = calculateBaseCost(order, pizzaMap);
    List<Integer> possibleCosts = new ArrayList<>();
    possibleCosts.add(baseCost);

    if (getTotalPizzaCount(order) >= 3) {
      possibleCosts.add(tryDiscount1(order, pizzaMap));
    }

    Map<String, Integer> pizzaCounts = getPizzaCounts(order);
    for (Map.Entry<String, Integer> entry : pizzaCounts.entrySet()) {
      if (entry.getValue() >= 5) {
        possibleCosts.add(tryDiscount2(order, pizzaMap, entry.getKey()));
      }
    }

    possibleCosts.add(tryDiscount3(order, pizzaMap));
    possibleCosts.add(tryDiscount4(order, pizzaMap));

    return possibleCosts.stream()
        .filter(cost -> cost != Integer.MAX_VALUE)
        .min(Integer::compareTo)
        .orElse(baseCost);
  }

  private int calculateBaseCost(OrderItem[] order, Map<String, Pizza> pizzaMap) {
    int total = 0;
    for (OrderItem item : order) {
      Pizza pizza = pizzaMap.get(item.name);
      total += getPrice(pizza, item.size) * item.quantity;
    }
    return total;
  }

  private int getPrice(Pizza pizza, String size) {
    switch (size) {
      case "Small": return pizza.price_S;
      case "Medium": return pizza.price_M;
      case "Large": return pizza.price_L;
      default: return 0;
    }
  }

  private int getTotalPizzaCount(OrderItem[] order) {
    int count = 0;
    for (OrderItem item : order) {
      count += item.quantity;
    }
    return count;
  }

  private Map<String, Integer> getPizzaCounts(OrderItem[] order) {
    Map<String, Integer> counts = new HashMap<>();
    for (OrderItem item : order) {
      counts.merge(item.name, item.quantity, Integer::sum);
    }
    return counts;
  }

  private int tryDiscount1(OrderItem[] order, Map<String, Pizza> pizzaMap) {
    List<PizzaPrice> prices = new ArrayList<>();
    for (OrderItem item : order) {
      Pizza pizza = pizzaMap.get(item.name);
      int price = getPrice(pizza, item.size);
      for (int i = 0; i < item.quantity; i++) {
        prices.add(new PizzaPrice(pizza.name, item.size, price));
      }
    }

    Collections.sort(prices, Comparator.comparingInt(p -> p.price));
    return prices.stream().skip(1).mapToInt(p -> p.price).sum();
  }

  private int tryDiscount2(OrderItem[] order, Map<String, Pizza> pizzaMap, String pizzaName) {
    int total = 0;
    int countToDiscount = 0;

    for (OrderItem item : order) {
      if (item.name.equals(pizzaName)) {
        int remainingQuantity = item.quantity;
        while (remainingQuantity > 0) {
          if (countToDiscount < 5) {
            countToDiscount++;
            remainingQuantity--;
          } else {
            Pizza pizza = pizzaMap.get(item.name);
            total += getPrice(pizza, item.size) * remainingQuantity;
            break;
          }
        }
      }
    }

    for (OrderItem item : order) {
      if (!item.name.equals(pizzaName)) {
        Pizza pizza = pizzaMap.get(item.name);
        total += getPrice(pizza, item.size) * item.quantity;
      }
    }

    return total + 100;
  }

  private int tryDiscount3(OrderItem[] order, Map<String, Pizza> pizzaMap) {
    int total = 0;
    int freeSmallCount = 0;

    // Count Large pizzas and calculate their cost
    for (OrderItem item : order) {
      if (item.size.equals("Large")) {
        freeSmallCount += item.quantity;
        Pizza pizza = pizzaMap.get(item.name);
        total += pizza.price_L * item.quantity;
      }
    }

    // Process other sizes
    List<PizzaPrice> smallPizzas = new ArrayList<>();
    for (OrderItem item : order) {
      if (item.size.equals("Small")) {
        Pizza pizza = pizzaMap.get(item.name);
        for (int i = 0; i < item.quantity; i++) {
          smallPizzas.add(new PizzaPrice(pizza.name, "Small", pizza.price_S));
        }
      } else if (item.size.equals("Medium")) {
        Pizza pizza = pizzaMap.get(item.name);
        total += pizza.price_M * item.quantity;
      }
    }

    Collections.sort(smallPizzas, Comparator.comparingInt(p -> p.price));
    for (int i = 0; i < smallPizzas.size(); i++) {
      if (freeSmallCount > 0) {
        freeSmallCount--;
      } else {
        total += smallPizzas.get(i).price;
      }
    }

    return total;
  }

  private int tryDiscount4(OrderItem[] order, Map<String, Pizza> pizzaMap) {
    int total = 0;
    Map<String, Integer> largePizzas = new HashMap<>();

    // Count Large pizzas by name
    for (OrderItem item : order) {
      if (item.size.equals("Large")) {
        largePizzas.merge(item.name, item.quantity, Integer::sum);
      }
    }

    for (OrderItem item : order) {
      Pizza pizza = pizzaMap.get(item.name);
      if (item.size.equals("Large")) {
        int groups = item.quantity / 3;
        int remainder = item.quantity % 3;
        total += (pizza.price_M * groups * 3);
        total += (pizza.price_L * remainder);
      } else {
        total += getPrice(pizza, item.size) * item.quantity;
      }
    }

    return total;
  }

  private static class PizzaPrice {
    String name;
    String size;
    int price;

    PizzaPrice(String name, String size, int price) {
      this.name = name;
      this.size = size;
      this.price = price;
    }
  }

  public static void main(String[] args) {
    // 수정된 메뉴와 주문
    Pizza[] menu = {
        new Pizza("margherita", 7, 8, 10),
        new Pizza("hawaii", 8, 9, 12),
        new Pizza("capricciosa", 5, 7, 13)
    };

    OrderItem[] order = {
        new OrderItem("margherita", "Small", 3),
        new OrderItem("capricciosa", "Large", 2),
        new OrderItem("hawaii", "Large", 3),
        new OrderItem("margherita", "Large", 1),
        new OrderItem("hawaii", "Medium", 1),
        new OrderItem("capricciosa", "Small", 5),
        new OrderItem("capricciosa", "Medium", 1)
    };

    SolutionOrigin solution = new SolutionOrigin();
    int result = solution.solution(menu, order);
    System.out.println("최소 비용: " + result);  // 예상되는 최소 비용 출력
  }
}

