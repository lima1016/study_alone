package com.lima.study_alone.designpatterns.structural_patterns.adapterpattern;

public class Adapter01LoginHandler {
  Adapter01UserDetailService userDetailService;

  public Adapter01LoginHandler(Adapter01UserDetailService userDetailService) {
    this.userDetailService = userDetailService;
  }

  public String login(String username, String password) {
    Adapter01UserDetails userDetails = userDetailService.loadUser(username);
    if (userDetails.getPassword().equals(password)) {
      return userDetails.getUsername();
    } else {
      throw new IllegalArgumentException();
    }

  }
}
