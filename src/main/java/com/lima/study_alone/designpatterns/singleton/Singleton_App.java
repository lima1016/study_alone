package com.lima.study_alone.designpatterns.singleton;

public class Singleton_App {

  public static void main(String[] args) {
    Singleton_01 one = Singleton_01.getInstance();
    Singleton_01 two = Singleton_01.getInstance();
    System.out.println(one == two);
  }
}
