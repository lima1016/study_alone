package com.lima.study_alone.codingtest.cafe24_20240721;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test03 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 테스트 케이스.
    int T = Integer.parseInt(br.readLine());

    for (int t = 0; t < T; t++) {
      // L 값.
      int L = Integer.parseInt(br.readLine());

      // 세 줄의 바이트 읽기
      String[] lowTemp = br.readLine().split("-");
      String[] highTemp = br.readLine().split("-");
      String[] roomTemp = br.readLine().split("-");

      // 불안정비트.
      int unstableBits = 0;

      for (int i = 0; i < L; i++) {
        // 이진으로 변환.
        String lowBinary = String.format("%8s", Integer.toBinaryString(Integer.parseInt(lowTemp[i]))).replace(' ', '0');
        String highBinary = String.format("%8s", Integer.toBinaryString(Integer.parseInt(highTemp[i]))).replace(' ', '0');
        String extremeBinary = String.format("%8s", Integer.toBinaryString(Integer.parseInt(roomTemp[i]))).replace(' ', '0');

        // 불안정한 비트.
        for (int j = 0; j < 8; j++) {
          char lowBit = lowBinary.charAt(j);
          char highBit = highBinary.charAt(j);
          char extremeBit = extremeBinary.charAt(j);

          if (lowBit != highBit || lowBit != extremeBit) {
            unstableBits++;
          }
        }
      }

      System.out.println(unstableBits);
    }
  }
}
