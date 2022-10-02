package com.lima.study_alone.algorithm.codingtest;

import java.util.Scanner;

/**
 * 8. 유효한 팰린드롬
 * 설명
 * 앞에서 읽을 때나 뒤에서 읽을 때나 같은 문자열을 팰린드롬이라고 합니다.
 * 문자열이 입력되면 해당 문자열이 팰린드롬이면 "YES", 아니면 “NO"를 출력하는 프로그램을 작성하세요.
 * 단 회문을 검사할 때 알파벳만 가지고 회문을 검사하며, 대소문자를 구분하지 않습니다.
 * 알파벳 이외의 문자들의 무시합니다.
 * 입력
 * 첫 줄에 길이 100을 넘지 않는 공백이 없는 문자열이 주어집니다.
 * 출력
 * 첫 번째 줄에 팰린드롬인지의 결과를 YES 또는 NO로 출력합니다.
 * found7, time: study; Yduts; emit, 7Dnuof => YES
 */
public class Inflearn20221002 {
    // LIM: 특수기호 위치 생각해보자 결과 안나옴
    public String solution(String input) {
        String reversedInput = new StringBuilder(input).reverse().toString().toLowerCase();
        String result = input.toLowerCase().equalsIgnoreCase(reversedInput) ? "YES" : "NO";
        return result;
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        String input1 = in.nextLine();
        Inflearn20221002 main = new Inflearn20221002();
        System.out.println(main.solution(input1));
    }
}
