package com.lima.study_alone.algorithm.codingtest;

import java.util.Scanner;

/**
 * 10. 가장 짧은 문자거리
 * 설명
 * 한 개의 문자열 s와 문자 t가 주어지면 문자열 s의 각 문자가 문자 t와 떨어진 최소거리를 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 번째 줄에 문자열 s와 문자 t가 주어진다. 문자열과 문자는 소문자로만 주어집니다.
 * 문자열의 길이는 100을 넘지 않는다.
 * 출력
 * 첫 번째 줄에 각 문자열 s의 각 문자가 문자 t와 떨어진 거리를 순서대로 출력한다.
 * teachermode e => 1 0 1 2 1 0 1 2 2 1 0
 */
public class Inflearn20221005 {
    public String solution(String word, String charWord) {
        String result = "";
        char[] chars = word.toCharArray();
        int[] charsIndex = new int[chars.length];
        int[] charWordIndex = {};
        int count = 0;
        // 먼저 문자 charWord의 인덱스들을 모아보자
        for (int i = 0; i < chars.length; i++) {
            charsIndex[i] = word.indexOf(chars[i]);
            if (charsIndex[i] == charWord.charAt(0)) {
                charWordIndex[count++] = word.indexOf(chars[i]);
            }
        }
        // word의 index들을 정리해서
        // charWord 인덱스들하고 다 빼놓은 다음에 마이너스가 아니면서 가장 작은거만 저장하는걸로
        int test = word.indexOf(charWord, 2);
        int indexOf = word.indexOf(charWord);
        System.out.println("charWordIndex >>>> " + charWordIndex);
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        String charWord = in.next();

        Inflearn20221005 main = new Inflearn20221005();
        System.out.println(main.solution(word, charWord));
    }
}
