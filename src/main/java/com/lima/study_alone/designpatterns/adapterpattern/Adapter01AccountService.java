package com.lima.study_alone.designpatterns.adapterpattern;

public class Adapter01AccountService {

  public Adapter01Account findAccountByUSername(String username) {
    Adapter01Account account = new Adapter01Account();
    account.setName(username);
    account.setPassword(username);
    account.setEmail(username);
    return account;
  }

  public void createNewAccount(Adapter01Account account) {

  }

  public void updateAccount(Adapter01Account account) {

  }
}
