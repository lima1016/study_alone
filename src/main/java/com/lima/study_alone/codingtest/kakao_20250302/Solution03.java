package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution03 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String s = br.readLine();

    // 각 다양성(1~26)에 대한 결과를 저장할 배열
    int[] results = new int[27];

    // 다양성 1부터 26까지 검사
    for (int diversity = 1; diversity <= 26; diversity++) {
      int maxLength = findMaxSubstringLength(s, diversity);
      results[diversity] = maxLength;
    }

    // 결과 출력
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= 26; i++) {
      sb.append(results[i]).append("\n");
    }
    System.out.print(sb);
  }

  // 다양성이 exactDiversity인 부분 문자열 중 가장 긴 길이를 찾는 함수
  private static int findMaxSubstringLength(String s, int exactDiversity) {
    int maxLength = 0;
    int left = 0;
    int[] charCount = new int[26]; // 알파벳 소문자 26개 카운트
    int uniqueChars = 0;

    for (int right = 0; right < s.length(); right++) {
      // 현재 문자 추가
      int charIndex = s.charAt(right) - 'a';
      if (charCount[charIndex] == 0) {
        uniqueChars++;
      }
      charCount[charIndex]++;

      // 고유 문자 개수가 exactDiversity보다 많으면 왼쪽 포인터 이동
      while (uniqueChars > exactDiversity) {
        int leftCharIndex = s.charAt(left) - 'a';
        charCount[leftCharIndex]--;
        if (charCount[leftCharIndex] == 0) {
          uniqueChars--;
        }
        left++;
      }

      // 현재 다양성이 exactDiversity와 정확히 일치하면 길이 업데이트
      if (uniqueChars == exactDiversity) {
        maxLength = Math.max(maxLength, right - left + 1);
      }
    }

    return maxLength;
  }
}
