package com.lima.study_alone.codingtest.지아이티;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution01 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int M = Integer.parseInt(br.readLine().trim());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int D1 = Integer.parseInt(st.nextToken());
    int D2 = Integer.parseInt(st.nextToken());
    int D3 = Integer.parseInt(st.nextToken());

    int prize;

    if (D1 == D2 && D2 == D3) {
      prize = M * D1 * D1 * D1;
    }
    else if (D1 == D2) {
      prize = M * D1 * D3;
    }
    else if (D1 == D3) {
      prize = M * D1 * D2;
    }
    else if (D2 == D3) {
      prize = M * D2 * D1;
    }
    else {
      prize = M * (D1 + D2 + D3) * 100;
    }

    System.out.println(prize);
  }
}