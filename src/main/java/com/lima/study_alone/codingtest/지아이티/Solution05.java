package com.lima.study_alone.codingtest.지아이티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution05 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int M = Integer.parseInt(br.readLine());

    int minArea = Integer.MAX_VALUE;
    int bestA = 0, bestB = 0, bestC = 0;

    for (int a = 1; a <= 50; a++) {
      for (int b = a; b <= 50; b++) {
        for (int c = b; c <= 50; c++) {
          int volume = a * b * c;
          if (volume >= M) {
            int area = 2 * (a * b + b * c + a * c);
            if (area < minArea ||
                (area == minArea && (a < bestA || (a == bestA && (b < bestB || (b == bestB && c < bestC)))))) {
              minArea = area;
              bestA = a;
              bestB = b;
              bestC = c;
            }
          }
        }
      }
    }

    System.out.println(bestA + " " + bestB + " " + bestC);
  }
}
