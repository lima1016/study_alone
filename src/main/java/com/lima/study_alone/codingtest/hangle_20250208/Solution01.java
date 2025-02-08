package com.lima.study_alone.codingtest.hangle_20250208;

import java.util.TreeSet;

public class Solution01 {

  public String[] solution(String[] goods) {
    String[] answer = new String[goods.length];

    for (int i = 0; i < goods.length; i++) {
      TreeSet<String> uniqueStrings = new TreeSet<>();
      String word = goods[i];

      for (int len = 1; len <= word.length(); len++) {
        boolean foundUnique = false;
        TreeSet<String> temp = new TreeSet<>();

        for (int j = 0; j <= word.length() - len; j++) {
          String substring = word.substring(j, j + len);
          boolean isUnique = true;

          for (int k = 0; k < goods.length; k++) {
            if (k != i && goods[k].contains(substring)) {
              isUnique = false;
              break;
            }
          }

          if (isUnique) {
            temp.add(substring);
            foundUnique = true;
          }
        }

        if (foundUnique) {
          uniqueStrings = temp;
          break;
        }
      }

      if (uniqueStrings.isEmpty()) {
        answer[i] = "";
      } else {
        answer[i] = String.join(" ", uniqueStrings);
      }
    }
    return answer;
  }


  public static void main(String[] args) {
    Solution01 solution01 = new Solution01();

    String[] goods = {"pencil", "cilicon", "contrabase", "picturelist"};

    for (String s : solution01.solution(goods)) {
      System.out.println(s);
    }
  }
}
