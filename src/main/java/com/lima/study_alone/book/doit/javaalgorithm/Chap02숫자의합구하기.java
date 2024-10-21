package com.lima.study_alone.book.doit.javaalgorithm;

import java.util.Scanner;

public class Chap02숫자의합구하기 {
/*
시간 복잡도의 종류
O(1): 입력 크기와 상관없이 항상 같은 시간이 걸림. 예를 들어 배열에서 특정 인덱스의 값을 가져오는 것.
O(log n): 입력 크기가 커질수록 걸리는 시간은 매우 느리게 증가. 예를 들어 이진 탐색 알고리즘.
O(n): 입력 크기에 비례하여 시간이 증가. 예를 들어 배열의 모든 요소를 한 번씩 검사하는 경우.
O(n log n): 병합 정렬 같은 효율적인 정렬 알고리즘이 여기에 속해.
O(n^2): 이중 for문 같은 경우. 예를 들어, 버블 정렬.
O(2^n): 입력이 늘어날수록 시간이 지수적으로 증가. 예를 들어, 피보나치 수열을 재귀로 푸는 경우.
O(n!): 순열을 전부 탐색하는 문제처럼 매우 느리게 시간이 늘어남.

알고리즘 풀 때 시간 복잡도 적용 방법
문제의 입력 크기 확인:
보통 n의 크기를 보고 어느 정도 시간 복잡도를 허용할 수 있는지 판단해.
예를 들어 n이 1,000일 때, O(n2)는 1,000,000번의 연산이므로 실행 가능한 수준.
하지만 n이 1,000,000이면 O(n^2)는 너무 느리니까 더 효율적인 알고리즘이 필요해.
알고리즘 분석 후 최적화: 먼저 떠오르는 방법으로 풀어보고, 시간 복잡도가 큰 경우 효율적인 자료구조나 알고리즘을 사용해서 개선해.


[기본적인 시간 복잡도 개념 이해]
시간 복잡도의 기본적인 정의를 물어보면서 다양한 복잡도 계층(O(1), O(n), O(n^2), O(log n) 등)에 대한 설명을 요구할 수 있어.
예시 질문: "시간 복잡도 O(n)과 O(log n)의 차이점에 대해 설명해보세요."
답변 포인트: 입력 크기가 커질 때 각 복잡도가 어떻게 동작하는지, 그리고 실제로 사용하는 예시를 들어서 설명.

[알고리즘의 시간 복잡도 분석]
주어진 코드나 알고리즘의 시간 복잡도를 직접 계산하게 할 수 있어.
예시 질문: "이중 for문이 포함된 알고리즘의 시간 복잡도를 계산해보세요."
답변 포인트: 반복문, 조건문 등을 살펴보고 최악의 경우 실행 시간을 계산하는 과정에서 O(n^2) 등의 결과를 도출.

[효율적인 알고리즘 설계]
문제를 해결하는 여러 알고리즘 중 어떤 것이 시간 복잡도 측면에서 더 나은지 비교하도록 할 수 있어.
예시 질문: "정렬 알고리즘 중에서 O(n log n)의 복잡도를 가진 알고리즘은 무엇이 있고, 그 동작 원리를 설명해보세요."
답변 포인트: 병합 정렬과 퀵 정렬 같은 알고리즘을 언급하고, 왜 O(n log n)인지 설명.

[최적화 방법 질문]
알고리즘의 시간 복잡도를 최적화하는 방법을 물어볼 수 있어.
예시 질문: "O(n^2) 시간이 걸리는 알고리즘을 O(n log n)으로 최적화할 수 있는 방법이 있나요?"
답변 포인트: 이중 for문 대신 이진 탐색, 분할 정복 같은 더 효율적인 방법을 제안할 수 있어야 해.

[다양한 자료 구조와 시간 복잡도]
자료 구조와 알고리즘의 결합을 통해 시간 복잡도를 물어볼 수 있어.
예시 질문: "해시맵을 사용하는 알고리즘의 시간 복잡도는 어떻게 되나요?"
답변 포인트: 해시맵의 삽입, 삭제, 검색이 평균적으로 O(1)인 이유와 최악의 경우 O(n)이 될 수 있는 상황(충돌)이 있음을 설명.

[공간 복잡도 관련 질문]
시간 복잡도 외에도 공간 복잡도에 대해 물어보며 메모리 효율성도 평가할 수 있어.
예시 질문: "시간 복잡도가 O(n log n)인 알고리즘의 공간 복잡도는 어떻게 되나요?"
답변 포인트: 알고리즘의 메모리 사용량을 계산하고, 예를 들어 병합 정렬처럼 추가 메모리가 필요한 경우 공간 복잡도를 분석할 수 있어야 해.

[최악의 경우와 평균적인 경우]ㅜ
특정 알고리즘의 최악의 경우와 평균적인 경우의 시간 복잡도를 구분할 수 있는지 확인할 수 있어.
예시 질문: "퀵 정렬의 최악의 경우 시간 복잡도는 어떻게 되고, 이를 피하기 위한 전략은 무엇인가요?"
답변 포인트: 최악의 경우 O(n^2)이지만, 피벗을 잘 선택하면 평균적으로 O(n log n)을 유지할 수 있음을 설명.
 */

  // 숫자의 합 구하기.
  // N개의 숫자가 공백 없이 써 있다. 이 숫자를 모두 합해 출력하는 프로그램을 작성하시오.
  // 1번째 줄에 숫자의 개수 N(1 <= N <= 100), 2번째 줄에 숫자 N개가 공백 없이 주어진다.
  // 예제: 1 1 => 출력: 1 / 5 54321 => 출력: 15 / 25 7000000000000000000000000 => 출력: 7 / 11 10987654321 => 출력: 46
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int sum = 0;
    int num = sc.nextInt();
    String string = sc.next();
    char[] charArray = string.toCharArray();

    for (int i = 0; i < num; i++) {
      int charNum = charArray[i] - '0';
      sum += charNum;
    }

    System.out.println(sum);
  }
}
