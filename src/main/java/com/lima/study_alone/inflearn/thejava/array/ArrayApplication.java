package com.lima.study_alone.inflearn.thejava.array;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/*
Arrays.parallelSort()
  ● Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.
병렬 정렬 알고리듬
  ● 배열을 둘로 계속 쪼갠다.
  ● 합치면서 정렬한다.
 */
public class ArrayApplication {
  public static void main(String[] args) {
    int size = 1500;
    int[] numbers = new int[size];
    Random random = new Random();

    // 값을 랜덤하게 채워줌
   IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
    // 시간 측정
    long start = System.nanoTime();;
    // sort 는 thead 를 하나만 씀
    // 자바는 기본적으로 Dual-Pivot Quicksort 를 씀
    // single thread 를 사용해서 한계가있음. 시간도 걸림
    Arrays.sort(numbers);
    System.out.println("serial sorting took " + (System.nanoTime() - start));

    // 다시 값을 랜덤하게 채워줌
    IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
    // 다시 시간 측정
    start = System.nanoTime();
    // parallelSort 가 더 빠르다고 하시는데 내 컴퓨터에서는 parallelSort 가 더 느리게 나옴
    // cpu 에 따라서 달라진다고 하심 ㅎㅎ
    Arrays.parallelSort(numbers);
    System.out.println("parallel sorting took " + (System.nanoTime() - start));
  }
}
