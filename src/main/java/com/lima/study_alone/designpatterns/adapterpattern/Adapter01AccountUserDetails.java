package com.lima.study_alone.designpatterns.adapterpattern;

public class Adapter01AccountUserDetails implements Adapter01UserDetails{

  private Adapter01Account account;

  public Adapter01AccountUserDetails(Adapter01Account account) {
    this.account = account;
  }

  @Override
  public String getUsername() {
    return this.account.getName();
  }

  @Override
  public String getPassword() {
    return this.account.getPassword();
  }
}
