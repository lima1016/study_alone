package com.lima.study_alone.designpatterns.singleton;


import java.io.*;

/**
 * 싱글톤 (Singleton) 패턴 구현 깨트리는 방법 2
 * 직렬화 & 역직렬화를 사용한다면?
 * 1. 자바의 직렬화 & 역직렬화에 대해 설명하세요.
 * - 직렬화: 자바 시스템 내부에서 사용되는 객체 또는 데이터를 바이트(byte) 형태로 변환
 * - 객체즐의 데이터를 연속적인 데이터로 변형하여 스트림을 통해 데이터를 읽도록 해준다.
 * - 역직렬화: 바이트로 변환된 데이터를 다시 객체로 변환
 * = 저장된 파일을 읽거나 전송된 스트림 데이터를 읽어 원래 객체의 형태로 복원하여 객체의 형태로 만드는 것을 의미한다.
 * 2. SerializableId란 무엇이며 왜 쓰는가?
 * - 직렬화된 클래스의 버전을 기억하여 로드된 클래스와 직렬화된 객체가 호환되는지 확인한다.
 * - serialVersionUID 속성을 사용하여 Serializable 클래스의 버전을 기억 하여 로드된 크래스와 직렬화된 객체가 호환되는지 확인하기 위함.
 * 3. try-resource 블럭에 대해 설명하세요.
 * - try 안ㅇ네서 AutoCloseable을 구현한 객체가 선언되었을 경우 close() 메소드를 호출해주는 구문.
 * - try 코드 블럭이 끝다면 자동으로 자원을 종료해주기 때문에 명시적으로 자원 반환을 하지 않아도 된다.
 */
public class Singleton06 implements Serializable {
  private static class SingletonHolder {
    private static final Singleton06 SINGLETON_06 = new Singleton06();
  }

  public static Singleton06 getInstance() {
    return Singleton06.SingletonHolder.SINGLETON_06;
  }

  // 이 시그니처를 갖고있으면 역직렬화를 반듯이 사용하게 됨.
  // 원래는 new Singleton_06 이지만 사용자가 getInstance()를 리턴하게 바꿔주면 실행시 동일한 객체를 얻을 수 있게됨.
  protected Object readResolve() {
    return getInstance();
  }
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Singleton06 one = Singleton06.getInstance();
    Singleton06 two = null;

    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings.obj"))) {
      out.writeObject(one);
    }

    try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings.obj"))) {
      two = (Singleton06) in.readObject();
    }
    System.out.println(one == two);
  }
}
