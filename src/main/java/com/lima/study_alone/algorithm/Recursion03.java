package com.lima.study_alone.algorithm;

/**
 * 순환적 알고리즘 설계
 * - 적어도 하나의 base case, 즉 순환되지 않고 종료되는 case가 있어야함
 * - 모든 case는 결국 base case로 수렴해야함
 */
public class Recursion03 {

  /**
   * 순차 탐색
   * 이 함수의 미션은 data[0]에서 data[n-1] 사이에서 target을 검색하는 것이다.
   * 하지만 검색 구간의 시작 인덱스 0은 보통 생략한다. 즉 암시적 매개 변수이다.
   */
  int search01(int[] data, int n, int target) {
    for (int i = 0; i < n; i++) {
      if (data[i] == target)
        return i;
    }
    return -1;
  }

  /**
   * 매개변수의 명시화: 순차 탐색
   * 이 함수의 미션은 data[begin]에서 data[end] 사이에서 target을 검색한다.
   * 즉, 검색 구간의 시작점을 명시적(explicit)으로 지정한다.
   * <p>
   * 이함수를 search02(data, 0, n-1, target)으로 호출한다면 위 search01메소드와 완전 동일한 일을 한다.
   */
  int search02(int[] data, int begin, int end, int target) {
    if (begin > end)
      return -1;
    else if (target == data[begin])
      return begin;
    else
      return search02(data, begin + 1, end, target);
  }

  // 순차 탐색: 다른버전
  int search03(int[] data, int begin, int end, int target) {
    if (begin > end)
      return -1;
    else if (target == data[begin])
      return begin;
    else
      return search03(data, begin, end - 1, target);
  }

  // 순차 탐색: 다른버전 (binary search와는 다름)
  int search04(int[] data, int begin, int end, int target) {
    if (begin > end)
      return -1;
    else {
      int middle = (begin + end) / 2;
      if (data[middle] == target)
        return middle;
      int index = search04(data, begin, middle - 1, target);
      if (index != -1)
        return index;
      else
        return search04(data, middle + 1, end, target);
    }
  }

  /**
   * 매개변수의 명시화: 최대값 찾기
   * 이 함수의 미션은 data[begin]에서 data[end] 사이에서 최대값을 찾아 반환한다. begin<=end라고 가정한다.
   */
  int findMax01(int[] data, int begin, int end) {
    if (begin == end)
      return data[begin];
    else
      return Math.max(data[begin], findMax01(data, begin + 1, end));
  }

  // 최대값 찾이: 다른버전
  int findMax02(int[] data, int begin, int end) {
    if (begin == end)
      return data[begin];
    else {
      int middle = (begin + end) / 2;
      int max1 = findMax02(data, begin, middle);
      int max2 = findMax02(data, middle, end);
      return Math.max(max1, max2);
    }
  }

  // Binary Search 2진 검색
  // items[begin]에서 items[end] 사이에서 target을 검색한다.
  public static int binarySearch(String[] items, String target, int begin, int end) {
    if (begin > end)
      return -1;
    else {
      int middle = (begin + end) / 2;
      int comResult = target.compareTo(items[middle]);
      if (comResult == 0)
        return middle;
      else if (comResult < 0)
        return binarySearch(items, target, begin, middle - 1);
      else
        return binarySearch(items, target, middle + 1, end);
    }
  }
}
