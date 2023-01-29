package com.lima.study_alone.codingtest.elevenstreet20230128;

public class Test01 {

  public static int solution(int N) {
    int result = N;
    int Nsum = 0;
    int findSum = 0;
    String[] split = String.valueOf(N).split("");

    // 입력 받은 값의 자리수 다 더하기
    for (int i = 0; i < split.length; i++) {
      Nsum += Integer.parseInt(split[i]);
    }
    // 입력 받은 값 보다 크고 자리수를 더했을때 Nsum과 같은 숫자 찾기
    boolean run = true;
    while (run) {
      result++;
      String[] str = String.valueOf(result).split("");
      findSum = 0;
      for (int i = 0; i < str.length; i++) {
        findSum += Integer.parseInt(str[i]);
      }
      if (findSum == Nsum) {
        run = false;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int num = 1000;
    Test01.solution(num);
  }
}
