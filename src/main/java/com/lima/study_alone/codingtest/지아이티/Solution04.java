package com.lima.study_alone.codingtest.지아이티;

/*
3
3428.01 2 O C
6200.29 3 X C
3038.94 1 O C

2605.20
5270.20
2020.80

2
9256.42 4 X P
5861.58 4 O C

8330.78
5011.60

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution04 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String[] parts = br.readLine().trim().split("\\s+");
      BigDecimal price      = new BigDecimal(parts[0]);
      int       level       = Integer.parseInt(parts[1]);
      String    coupon      = parts[2]; // O/X
      String    paymentMode = parts[3]; // C=현금, P=카드

      // 1) 레벨 할인
      BigDecimal rate;
      switch (level) {
        case 1: rate = new BigDecimal("0.30"); break;
        case 2: rate = new BigDecimal("0.20"); break;
        case 3: rate = new BigDecimal("0.15"); break;
        case 4: rate = new BigDecimal("0.10"); break;
        default: rate = BigDecimal.ZERO;
      }
      BigDecimal discounted = price.multiply(BigDecimal.ONE.subtract(rate));

      // 2) 쿠폰(O)이면 추가 5%
      if ("O".equals(coupon)) {
        discounted = discounted.multiply(new BigDecimal("0.95"));
      }

      BigDecimal finalPrice;
      if ("C".equals(paymentMode)) {
        // ────────────────
        // 현금: 소수점 둘째 자리(=hundredths) 자체를 버림
        // 즉, keep 1 decimal, drop everything after
        BigDecimal oneDecimal = discounted.setScale(1, RoundingMode.DOWN);
        // 그리고 두 자리 표기를 위해 한 번 더
        finalPrice = oneDecimal.setScale(2, RoundingMode.DOWN);
      } else {
        // ────────────────
        // 카드: 소수점 셋째 자리에서 반올림 → 소수점 둘째 자리까지
        finalPrice = discounted.setScale(2, RoundingMode.HALF_UP);
      }

      System.out.println(finalPrice.toPlainString());
    }
  }
}