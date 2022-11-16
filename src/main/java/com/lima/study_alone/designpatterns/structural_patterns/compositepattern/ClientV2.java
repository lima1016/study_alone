package com.lima.study_alone.designpatterns.structural_patterns.compositepattern;

public class ClientV2 {
  public static void main(String[] args) {
    ItemV2 doranBlade = new ItemV2("도란검", 450);
    ItemV2 healPotion = new ItemV2("체력 물약", 50);

    BagV2 bag = new BagV2();
    bag.add(doranBlade);
    bag.add(healPotion);

    ClientV2 client = new ClientV2();
    client.printPrice(doranBlade);
    client.printPrice(bag);
  }

  private void printPrice(Component component) {
    System.out.println(component.getPrice());
  }

}
