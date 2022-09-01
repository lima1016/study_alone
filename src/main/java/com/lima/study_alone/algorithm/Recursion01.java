package com.lima.study_alone.algorithm;

// 재귀
public class Recursion01 {
  // 최대 공약수
  // m >= n인 두 양의 정수 m과 n에 대해서 m이 n의 배수이면 gcd(m, n)-n 이고, 그렇지 않으면 gcd(m, n) = gcd(n, m%n)이다.
  public double gcd(int m, int n) {
    if (m < n) {
      int tmp = m;
      m = n;
      n = tmp; // swap m and n
    }
    if (m % n == 0)
      return n;
    else
      return gcd(n, m % n);
  }

  // 간단한 버전
  // p가 q보다 클 필요는 없음
  // 왜 무한 루프에 빠지지 않을까?
  public int gcd2(int p, int q) {
    if (q == 0)
      return p;
    else return gcd2(q, p % q);
  }
  public static void main(String[] args) {
    Recursion01 r = new Recursion01();
//    System.out.println(r.gcd(1563, 88));
    System.out.println(r.gcd2(554, 23));
  }
}
