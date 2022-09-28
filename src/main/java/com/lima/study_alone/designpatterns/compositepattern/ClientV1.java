package com.lima.study_alone.designpatterns.compositepattern;

public class ClientV1 {
  public static void main(String[] args) {
    ItemV1 doranBlade = new ItemV1("도란검", 450);
    ItemV1 healPotion = new ItemV1("체력 물약", 50);

    BagV1 bag = new BagV1();
    bag.add(doranBlade);
    bag.add(healPotion);

    ClientV1 client = new ClientV1();
    client.printPrice(doranBlade);
    client.printPrice(bag);
  }

  private void printPrice(BagV1 bag) {
    int sum = bag.getItems().stream().mapToInt(ItemV1::getPrice).sum();
    System.out.println(sum);
  }

  private void printPrice(ItemV1 item) {
    System.out.println(item.getPrice());
  }
}
