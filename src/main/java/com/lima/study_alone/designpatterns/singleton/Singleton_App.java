package com.lima.study_alone.designpatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton_App {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    // true
//    Singleton_01 one = Singleton_01.getInstance();
//    Singleton_01 two = Singleton_01.getInstance();

    // true
//    Singleton_05 one = Singleton_05.getInstance();
//    Singleton_05 two = Singleton_05.getInstance();

    /**
     * 싱글톤 (Singleton) 패턴 구현 깨트리는 방법 1
     * 리플렉션을 사용한다면?
     * 1. 리플렉션에 대해 설명하세요.
     * 2. setAccessible(true)를 사용하는 이유는?
     */
    // 어떤 방법으로 두 인스턴스의 비교값을 false 로 만들수있을까?
    // false
    Singleton_05 one = Singleton_05.getInstance();
    Constructor<Singleton_05> constructor = Singleton_05.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    Singleton_05 two = constructor.newInstance();

    System.out.println(one == two);
  }
}
