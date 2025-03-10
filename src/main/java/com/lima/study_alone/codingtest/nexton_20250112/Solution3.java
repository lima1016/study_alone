package com.lima.study_alone.codingtest.nexton_20250112;

import java.util.Arrays;
import java.util.List;

public class Solution3 {

  /*
  문제 분석:

  n개의 암호화된 파일이 있고, 각각 다른 값을 가집니다.
  n개의 이진값 리스트가 있으며, 1은 복호화된 파일, 0은 아직 복호화되지 않은 파일을 나타냅니다.
  한 번의 작업으로 최대 k개의 연속된 파일을 복호화할 수 있습니다.
  목표는 최대 한 번의 작업으로 얻을 수 있는 최대 value sum을 찾는 것입니다.

  해결 방법:

  길이가 k인 모든 연속된 부분 배열을 확인합니다.
  각 부분 배열에 대해, binary 배열의 해당 위치를 1로 바꾸었을 때의 value sum을 계산합니다.
  가장 큰 value sum을 반환합니다.
   */
//  public static int getMaxValueSum(List<Integer> encrypted_file, List<Integer> binary, int k) {
//    int n = encrypted_file.size();
//    int maxSum = 0;
//
//    // 현재 binary 배열로 계산되는 기본 sum 계산
//    int currentSum = 0;
//    for (int i = 0; i < n; i++) {
//      if (binary.get(i) == 1) {
//        currentSum += encrypted_file.get(i);
//      }
//    }
//
//    // 모든 가능한 연속된 k개 이하의 부분 배열 확인
//    for (int len = 1; len <= k; len++) {
//      for (int start = 0; start <= n - len; start++) {
//        // binary 배열의 복사본 생성
//        List<Integer> tempBinary = new ArrayList<>(binary);
//        int additionalSum = 0;
//
//        // 선택된 부분 배열의 값을 1로 설정하고 추가되는 합 계산
//        for (int i = start; i < start + len; i++) {
//          if (tempBinary.get(i) == 0) {
//            additionalSum += encrypted_file.get(i);
//          }
//        }
//
//        // 최대값 갱신
//        maxSum = Math.max(maxSum, currentSum + additionalSum);
//      }
//    }
//
//    return maxSum;
//  }

//  public static int getMaxValueSum(List<Integer> encrypted_file, List<Integer> binary, int k) {
//    int n = encrypted_file.size();
//    int currentSum = 0;
//
//    // binary 배열이 1인 경우의 합 계산
//    for (int i = 0; i < n; i++) {
//      if (binary.get(i) == 1) {
//        currentSum += encrypted_file.get(i);
//      }
//    }
//
//    // 슬라이딩 윈도우로 최대 추가 가능한 합 계산
//    int maxAdditionalSum = 0, additionalSum = 0;
//    for (int start = 0, end = 0; end < n; end++) {
//      // binary가 0인 경우 추가합에 포함
//      if (binary.get(end) == 0) {
//        additionalSum += encrypted_file.get(end);
//      }
//
//      // 윈도우 크기가 k를 초과하면 시작점을 이동
//      if (end - start + 1 > k) {
//        if (binary.get(start) == 0) {
//          additionalSum -= encrypted_file.get(start);
//        }
//        start++;
//      }
//
//      // 최대 추가 가능 합 갱신
//      maxAdditionalSum = Math.max(maxAdditionalSum, additionalSum);
//    }
//
//    // 최종 결과 반환
//    return currentSum + maxAdditionalSum;
//  }
public static int getMaxValueSum(List<Integer> encryptedFile, List<Integer> binary, int windowSize) {
  final int BINARY_ONE = 1; // Introduce constant for clarity
  int n = encryptedFile.size();

  // Extracted helper method for calculating the fixed sum
  int fixedSum = calculateFixedSum(encryptedFile, binary, BINARY_ONE);

  // Sliding window logic to compute the maximum additional sum
  int maxAdditionalSum = 0, currentWindowSum = 0;
  for (int start = 0, end = 0; end < n; end++) {
    // Add the current element to the window sum only if binary is 0
    if (binary.get(end) == 0) {
      currentWindowSum += encryptedFile.get(end);
    }

    // Shrink window if it exceeds the allowed size
    if (end - start + 1 > windowSize) {
      if (binary.get(start) == 0) {
        currentWindowSum -= encryptedFile.get(start);
      }
      start++;
    }

    // Update the maximum additional sum
    maxAdditionalSum = Math.max(maxAdditionalSum, currentWindowSum);
  }

  // Return the total sum considering the computed sliding window max
  return fixedSum + maxAdditionalSum;
}

  // Helper method to compute the sum of elements where corresponding binary value is 1
  private static int calculateFixedSum(List<Integer> encryptedFile, List<Integer> binary, int binaryOne) {
    int sum = 0;
    for (int i = 0; i < encryptedFile.size(); i++) {
      if (binary.get(i) == binaryOne) {
        sum += encryptedFile.get(i);
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    // Sample Case 0 테스트
    List<Integer> encrypted_file0 = Arrays.asList(1, 3, 5, 2, 5, 4);
    List<Integer> binary0 = Arrays.asList(1, 1, 0, 1, 0, 0);
    int k0 = 3;

    System.out.println("Sample Case 0:");
    System.out.println("Input:");
    System.out.println("encrypted_file = " + encrypted_file0);
    System.out.println("binary = " + binary0);
    System.out.println("k = " + k0);
    int result0 = getMaxValueSum(encrypted_file0, binary0, k0);
    System.out.println("Output: " + result0);
    System.out.println();

    // Sample Case 1 테스트
    List<Integer> encrypted_file1 = Arrays.asList(1, 3, 2, 3, 6);
    List<Integer> binary1 = Arrays.asList(0, 0, 0, 1, 0);
    int k1 = 2;

    System.out.println("Sample Case 1:");
    System.out.println("Input:");
    System.out.println("encrypted_file = " + encrypted_file1);
    System.out.println("binary = " + binary1);
    System.out.println("k = " + k1);
    int result1 = getMaxValueSum(encrypted_file1, binary1, k1);
    System.out.println("Output: " + result1);
  }
}
