package com.lima.study_alone.thejava.method;

public class FooApplication {
  public static void main(String[] args) {
    FooInterface foo = new DefaultFoo("lima");
    foo.printName();
    foo.printNameUpperCase();

    FooInterface.printAnything();
  }
}
