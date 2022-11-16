package com.lima.study_alone.designpatterns.structural_patterns.adapterpattern;

public class Adapter01App {
  public static void main(String[] args) {

    Adapter01AccountService accountService = new Adapter01AccountService();
    Adapter01UserDetailService userDetailService = new Adapter01AccountUserDetailsService(accountService);
    Adapter01LoginHandler loginHandler = new Adapter01LoginHandler(userDetailService);
    String login = loginHandler.login("lima", "lima");
    System.out.println(login);
  }
}
