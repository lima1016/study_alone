package com.lima.study_alone.designpatterns.structural_patterns.adapterpattern;

public class Adapter01AccountUserDetailsService implements Adapter01UserDetailService{

  Adapter01AccountService accountService;

  public Adapter01AccountUserDetailsService(Adapter01AccountService accountService) {
    this.accountService = accountService;
  }

  // Adapter 완성
  @Override
  public Adapter01UserDetails loadUser(String username) {
    Adapter01Account account = accountService.findAccountByUSername(username);
    return new Adapter01AccountUserDetails(account);
  }
}
