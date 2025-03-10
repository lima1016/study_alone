package com.lima.study_alone.codingtest.yogiyo_20250308;

public class Solution01 {

  public boolean solution(String S) {
    // 전화번호 패턴: "123-456-789" 형태인지 확인
    String regex = "^[0-9]{3}-[0-9]{3}-[0-9]{3}$";

    // 정규식을 사용하여 문자열이 패턴과 일치하는지 확인
    return S.matches(regex);
  }

  public static void main(String[] args) {
    Solution01 sol = new Solution01();

    // 테스트 케이스 실행
    System.out.println(sol.solution("123-123-123")); // true
    System.out.println(sol.solution("123 123 123")); // false
    System.out.println(sol.solution("123-123-1234")); // false
    System.out.println(sol.solution("001-501-511")); // true
    System.out.println(sol.solution("123-abc-123")); // false
  }

}
