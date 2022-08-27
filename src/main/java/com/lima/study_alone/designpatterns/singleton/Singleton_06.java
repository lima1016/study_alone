package com.lima.study_alone.designpatterns.singleton;


import java.io.*;

/**
 * 싱글톤 (Singleton) 패턴 구현 깨트리는 방법 2
 * 직렬화 & 역직렬화를 사용한다면?
 * 1. 자바의 직렬화 & 역직렬화에 대해 설명하세요.
 * 2. SerializableId란 무엇이며 왜 쓰는가?
 * 3. try-resource 블럭에 대해 설명하세요.
 */
public class Singleton_06 implements Serializable {
  private static class SingletonHolder {
    private static final Singleton_06 SINGLETON_06 = new Singleton_06();
  }

  public static Singleton_06 getInstance() {
    return Singleton_06.SingletonHolder.SINGLETON_06;
  }

  // 이 시그니처를 갖고있으면 역직렬화를 반듯이 사용하게 됨.
  // 원래는 new Singleton_06 이지만 사용자가 getInstance()를 리턴하게 바꿔주면 실행시 동일한 객체를 얻을 수 있게됨.
  protected Object readResolve() {
    return getInstance();
  }
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Singleton_06 one = Singleton_06.getInstance();
    Singleton_06 two = null;

    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
      out.writeObject(one);
    }

    try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
      two = (Singleton_06) in.readObject();
    }
    System.out.println(one == two);
  }
}
