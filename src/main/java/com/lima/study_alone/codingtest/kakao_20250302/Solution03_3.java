package com.lima.study_alone.codingtest.kakao_20250302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이거로 제출함
public class Solution03_3 {
  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new InputStreamReader(System.in));
    br.readLine(); // 문자열 길이 읽지만 사용하지 않음
    String s = br.readLine();

    for (int d = 1; d <= 26; d++) {
      int left = 0, distinct = 0, maxLen = 0;
      int[] freq = new int[26];

      for (int right = 0; right < s.length(); right++) {
        if (freq[s.charAt(right) - 'a']++ == 0) distinct++;

        while (distinct > d) {
          if (--freq[s.charAt(left++) - 'a'] == 0) distinct--;
        }

        if (distinct == d) {
          maxLen = Math.max(maxLen, right - left + 1);
        }
      }
      System.out.println(maxLen);
    }
  }
}
