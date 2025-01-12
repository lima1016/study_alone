package com.lima.study_alone.codingtest.nexton_20250112;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {

  /*
  A password string, pwd, consists of binary characters (os and 1s). A cyber security expert is trying to
  determine the minimum number of changes required to make the password secure. To do so, it must be divided
  into substrings of non-overlapping, even length substrings. Each substring can only contain 1s or 0s, not a mix. This helps to ensure that the password
  is string and less vulnerable to hacking attacks.

  Find the minimum number of characters that must be flipped inthe password string, i.e. changed from 0 to 1 or
  1 to 0 to allow the string to be divided as described.
   */

  public static int getMinFlips(String pwd) {
    int n = pwd.length();
    int flips = 0;

    // Traverse the string in steps of 2 (each step represents a substring of length 2)
    for (int i = 0; i < n; i += 2) {
      // Count the number of '1's and '0's in the current substring of length 2
      int count1 = 0; // Number of '1's
      int count0 = 0; // Number of '0's

      // Check both characters in the current substring
      if (pwd.charAt(i) == '1') count1++;
      else count0++;

      if (pwd.charAt(i + 1) == '1') count1++;
      else count0++;

      // To make the substring valid, flip the smaller count
      flips += Math.min(count1, count0);
    }

    return flips;
  }

  public static void main(String[] args) {
    // Test cases
    String pwd1 = "1110011000";
    System.out.println("Minimum flips for pwd1: " + getMinFlips(pwd1)); // Output: 3

    String pwd2 = "100110";
    System.out.println("Minimum flips for pwd2: " + getMinFlips(pwd2)); // Output: 3
  }
}
