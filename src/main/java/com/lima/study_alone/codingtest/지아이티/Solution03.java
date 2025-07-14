package com.lima.study_alone.codingtest.지아이티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
leap
wednesday
13
first wednesday of january
first thursday of january
first friday of january
first saturday of january
first sunday of january
first monday of january
first tuesday of january
second wednesday of january
third wednesday of january
last wednesday of january
last friday of january
fourth saturday of january
last saturday of january
 */

/*
non-leap
sunday
12
first sunday of january
second monday of february
third tuesday of march
fourth wednesday of april
last thursday of may
first friday of june
second saturday of july
third sunday of august
fourth monday of september
last tuesday of october
first wednesday of november
second thursday of december
 */
public class Solution03 {
  static final String[] DAYS = {
      "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"
  };
  static final String[] MONTHS = {
      "january", "february", "march", "april", "may", "june",
      "july", "august", "september", "october", "november", "december"
  };

  static final Map<String, Integer> dayIndex = new HashMap<>();
  static final Map<String, Integer> monthIndex = new HashMap<>();

  static {
    for (int i = 0; i < DAYS.length; i++) {
      dayIndex.put(DAYS[i], i);
    }
    for (int i = 0; i < MONTHS.length; i++) {
      monthIndex.put(MONTHS[i], i);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    boolean isLeap = br.readLine().trim().equals("leap");
    String jan1 = br.readLine().trim().toLowerCase();
    int n = Integer.parseInt(br.readLine().trim());

    int[] daysInMonth = {
        31, isLeap ? 29 : 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    int[] monthStartDay = new int[12];
    monthStartDay[0] = dayIndex.get(jan1);

    for (int i = 1; i < 12; i++) {
      monthStartDay[i] = (monthStartDay[i - 1] + daysInMonth[i - 1]) % 7;
    }

    for (int i = 0; i < n; i++) {
      String line = br.readLine().trim().toLowerCase();
      String[] parts = line.split(" ");
      String nth = parts[0];
      String day = parts[1];
      String month = parts[3];

      int mIdx = monthIndex.get(month);
      int dIdx = dayIndex.get(day);
      int startDay = monthStartDay[mIdx];
      int totalDays = daysInMonth[mIdx];

      List<Integer> matchingDates = new ArrayList<>();
      for (int d = 1; d <= totalDays; d++) {
        if ((startDay + d - 1) % 7 == dIdx) {
          matchingDates.add(d);
        }
      }

      int resultDay = 0;
      switch (nth) {
        case "first":
          resultDay = matchingDates.get(0);
          break;
        case "second":
          resultDay = matchingDates.get(1);
          break;
        case "third":
          resultDay = matchingDates.get(2);
          break;
        case "fourth":
          resultDay = matchingDates.get(3);
          break;
        case "last":
          resultDay = matchingDates.get(matchingDates.size() - 1);
          break;
      }

      System.out.println((mIdx + 1) + " " + resultDay);
    }
  }
}
