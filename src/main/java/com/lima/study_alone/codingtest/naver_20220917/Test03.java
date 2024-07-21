package com.lima.study_alone.codingtest.naver_20220917;

//LIMA: 다시 해보기
public class Test03 {
  public int solution(String L1, String L2) {
    char[] L1Ary = L1.toCharArray();
    char[] L2Ary = L2.toCharArray();
    int count = 0;

    for (int i = 0; i < L1.length(); i++) {
      if (L1Ary[i] != L2Ary[i]) {
        count++;
      }
    }

    if (L1Ary[L1.length()-1] == L2Ary[L1.length()-1] && (L1Ary[L1.length()-1] == 'X' || L1Ary[L1.length()-1] == 'x')) {
      count++;
    }

    int l1Check = 0;
    // 마지막 L1 테스트
    for (int i = 0; i < L1.length(); i++) {
      if (L1Ary[i] == 'X' || L1Ary[i] == 'x') {
        l1Check++;
      }
    }

    if (l1Check == L1Ary.length) {
      count = L2Ary.length;
    }
    int l2Check = 0;
    // 마지막 L2 테스트
    for (int i = 0; i < L2.length(); i++) {
      if (L2Ary[i] == 'X' || L2Ary[i] == 'x') {
        l2Check++;
      }
    }
    if (l2Check == L2Ary.length) {
      count = L2Ary.length;
    }

    // 만약 마지막에서 둘중 하나가 다르다면 -1
    if (L1Ary[L1Ary.length-1] != L2Ary[L2Ary.length-1]) {
      count--;
    }
    return count;
  }

  public static void main(String[] args) {
//    String L1 = "xxxxx";
    String L1 = "X...X";
//    String L2 = ".X..X";
    String L2 = "..X..";

    Test03 test = new Test03();
    System.out.println(test.solution(L1, L2));
  }
}
