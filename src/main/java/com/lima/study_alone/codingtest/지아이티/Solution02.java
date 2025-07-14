package com.lima.study_alone.codingtest.지아이티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution02 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 자동차 정가 x 입력 (단위: 만원)
    int carPrice = Integer.parseInt(br.readLine().trim());

    // 제약 조건 확인
    if (carPrice < 100 || carPrice > 10000) {
      System.out.println("자동차 정가는 100만원 이상 10,000만원 이하여야 합니다.");
      return;
    }

    if (carPrice % 100 != 0) {
      System.out.println("자동차 정가는 100만원의 배수여야 합니다.");
      return;
    }

    // 보조금 계산 (자동차 정가의 20%, 최대 1000만원)
    int subsidy = (int)(carPrice * 0.2);
    subsidy = Math.min(subsidy, 1000);

    // 실질 판매가 계산 (정가 - 보조금)
    int actualPrice = carPrice - subsidy;

    // 결과 출력
    System.out.println(actualPrice);
  }

}
