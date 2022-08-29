package com.lima.study_alone.designpatterns.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton_App {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
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
     * - 구체적인 클래스 타입을 알지 못해도, 그 클래스의 메서드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API
     * 2. setAccessible(true)를 사용하는 이유는?
     * - private 생성자에 접근하기 위한 목적이다.
     * - 필드나 메서드의 접근제어 지시자에 대한 제어를 변경하기 위한 설정이다.
     */
    // 어떤 방법으로 두 인스턴스의 비교값을 false 로 만들수있을까?
    // false
//    Singleton_05 one = Singleton_05.getInstance();
//    Constructor<Singleton_05> constructor = Singleton_05.class.getDeclaredConstructor();
//    constructor.setAccessible(true);
//    Singleton_05 two = constructor.newInstance();

    Singleton_07 one = Singleton_07.INSTANCE;
    Singleton_07 two = null;
    // Singleton_06 직렬화 코드 추가
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
      out.writeObject(one);
    }

    try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
      two = (Singleton_07) in.readObject();
    }

//    Constructor<?>[] declaredConstructors = Singleton_07.class.getDeclaredConstructors();
//    for (Constructor<?> constructor : declaredConstructors) {
//      constructor.setAccessible(true);
      /*
      if ((clazz.getModifiers() & Modifier.ENUM) != 0)
      throw new IllegalArgumentException("Cannot reflectively create enum objects");
       */
//      two = (Singleton_07) constructor.newInstance("INSTANCE");
//    }

    System.out.println(one == two);
  }
  /**
   * 싱글톤 (Singleton) 패턴 복습
   * 직접 말하며 설명하고 코딩해 보세요.
   * • 자바에서 enum을 사용하지 않고 싱글톤 패턴을 구현하는 방법은?
   * • private 생성자와 static 메소드를 사용하는 방법의 단점은?
   * • enum을 사용해 싱글톤 패턴을 구현하는 방법의 장점과 단점은?
   * • static inner 클래스를 사용해 싱글톤 패턴을 구현하라.
   */
}
