package com.lima.study_alone.codingtest.engaging;

import java.util.HashMap;
import java.util.Map;

public class IPPasswordValidator {

  /* 찾아볼수밖에 없었던 부분....
  - 사설 IP 범위
  - ip 범위 사이 값 찾는 방법 --> 코드는 보았지만... 아이디어가 떠오르지 않았습니다...
   */

  // 목표
  // 1. 사설 IP여야함
  // 2. 비밀번호가 조건에 맞게 강력해야함
  // 3. ip 점수 반환
  public static boolean isValidIP(String ipv4Str) {
    boolean privateIP = isPrivateIP(ipv4Str);
    if (!privateIP) {
      return false;
    }

    String[] octets = ipv4Str.split("\\.");
    if (octets.length != 4) {
      return false;
    }

    for (String octet : octets) {
      try {
        int value = Integer.parseInt(octet);
        // x1 is a decimal number, and 1 <= x1 <= 255 (i.e., x1 cannot be 0).
        // x2, x3, and x4 are also decimal numbers, and 0 <= xi <= 255.
        if (value < 0 || value > 255 || octet.charAt(0) == '0') {
          return false;
        }
      } catch (NumberFormatException e) {
        // 4. The address should not include any unnecessary characters, such as non-numeric characters, white spaces, or prefixes.
        // 문자들어오면 false
        return false;
      }
    }

    return true;
  }

  // private ip인지 확인
  // 1. It is a private IP in the form "x1.x2.x3.x4".
  // 10.0.0.0 ~ 10.255.255.255
  // 172.16.0.0 ~ 172.31.255.255
  // 192.168.0.0 ~ 192.168.255.255
  private static boolean isPrivateIP(String ip) {
    if (ip == null || ip.isEmpty()) {
      return false;
    }

    String[] octets = ip.split("\\.");
    int firstOctet = Integer.parseInt(octets[0]);
    int secondOctet = Integer.parseInt(octets[1]);

    if (firstOctet == 10) {
      return true;
    }

    if (firstOctet == 172 && (secondOctet >= 16 && secondOctet <= 31)) {
      return true;
    }

    if (firstOctet == 192 && secondOctet == 168) {
      return true;
    }

    return false;
  }

  public static boolean isStrongPassword(String password) {
    // 1. The password must be between 8 and 20 characters long.
    if (password == null || password.length() < 8 || password.length() > 20) {
      return false;
    }

    boolean isUpper = false;
    boolean isLower = false;
    boolean isNum = false;
    boolean isSpecial = false;
    Map<Character, Integer> charCount = new HashMap<>();

    for (int i = 0; i < password.length(); i++) {
      char c = password.charAt(i);
      charCount.put(c, charCount.getOrDefault(c, 0) + 1);
      if (charCount.get(c) > 2) {
        return false;
      }

      if (i > 0 && c == password.charAt(i - 1)) {
        return false;
      }


      if (c >= 'A' && c <= 'Z') { // 2. It must contain at least one uppercase alphabetic character and one lowercase alphabetic character (a-z, A-Z).
        isUpper = true;
      } else if (c >= 'a' && c <= 'z') {
        isLower = true;
      } else if (c >= '0' && c <= '9') { // 3. It must contain at least one digit (0-9).
        isNum = true;
      } else if ("~!@#$%'\"` /\\".indexOf(c) != -1) { // It must contain at least one special character below
        isSpecial = true;
      } else {
        return false;
      }
    }

    return isUpper && isLower && isNum && isSpecial;
  }

  public static int getSafetyScore(String ipv4Str) {
    if (!isValidIP(ipv4Str)) {
      return 0;
    }

    int score = 0;
    int firstOctet = 0;
    int lastOctet = 0;
    int octetIndex = 0;

    for (int i = 0; i < ipv4Str.length(); i++) {
      char c = ipv4Str.charAt(i);
      if (c == '.') {
        octetIndex++;
      } else {
        if (octetIndex == 0) {
          firstOctet = firstOctet * 10 + (c - '0');
        } else if (octetIndex == 3) {
          lastOctet = lastOctet * 10 + (c - '0');
        }
      }
    }

    // 3. If an IP address falls into multiple zones, all the scores should be added.

    // ** 첫번째 대역만 확인하는 것으로... 어쩔수 없이 결정
    // blue zones
    // IP addresses included in the blue zone earn a score of +1.
    // 195.250.0.0 - 197.255.255.255
    // 198.0.0.1 - 205.255.255.255 (only of the last of ip address number is even)
    // 190.250.0.0 - 200.255.10.10
    // 185.1.1.20 - 193.255.255.255
    // 170.255.0.0 - 175.254.128.50
    // 203.0.0.1 - 204.123.0.255
    if ((firstOctet >= 170 && firstOctet <= 175) ||
        (firstOctet >= 185 && firstOctet <= 200)) {
      if (firstOctet >= 198 && firstOctet <= 205) {
        score += (lastOctet % 2 == 0) ? +1 : 0;
      } else {
        score++;
      }
    }

    // red zones
    // IP addresses included in the red zone get a score of -1.
    // 190.250.0.0 - 200.255.10.255
    // 192.250.0.0 - 193.255.255.255
    // 192.248.0.10 - 192.255.0.255
    // 198.0.0.1 - 205.255.255.255 (only of the last of ip address numver is odd)
    // 199.0.0.1 - 201.0.0.255
    if ((firstOctet >= 190 && firstOctet <= 205)) {
      score += (lastOctet % 2 == 1) ? -1 : 0;
    }

    return score;
  }

  public static void main(String[] args) {
    // 4. If the `isValidIP()` result for the given IP is false, the score is 0.
    String[][] testCases = {
        {"192.168.1.1", "HiWorld123!@"}, // blue zone 이자 red zone 이여서 score -1 임.
        {"0.168.0.1", "HiWorld"}, // 0으로 시작 false
        {"192.168.1.010", "HiWorld1!!"}, // false
        {"192.168.@1.1", "HelloWorld1!"}, // false
        {"100.200.300.400", "HelloWorld1!"}, // 0  => Because `isValidIP()` is false
        {"185.1.1.20", "HelloWorld1!"}, // 1  => It is in one blue zone. (the left side on the map)
        {"205.255.255.255", "HiWorld123!@"}, // -1 => It is in unsafe zone. (the right side on the map)
        {"190.250.0.0", "HiWorld123!@"}, // 1  => It falls into two blue and one red zones: +1 -1 +1 = 1
    };

    for (String[] testCase : testCases) {
      String ip = testCase[0];
      String password = testCase[1];
      int score = getSafetyScore(ip);
      boolean strongPassword = isStrongPassword(password);
      boolean allowed = score > 0 && strongPassword;
      System.out.printf("IP: %s, Password: %s, Valid IP: %b, Strong Password: %b, Score: %s, Allowed: %b%n", ip, password, isValidIP(ip), strongPassword, score, allowed);
    }
  }
}
