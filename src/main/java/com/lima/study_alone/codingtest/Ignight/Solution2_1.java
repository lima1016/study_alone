package com.lima.study_alone.codingtest.Ignight;

import com.lima.study_alone.codingtest.flab.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution2_1 {

  public int[] solution(int num_teams, String[] remote_tasks, String[] office_tasks, String[] employees) {
    // 빠른 조회를 위해 Set 생성
    Set<String> remoteSet = new HashSet<>(Arrays.asList(remote_tasks));
    Set<String> officeSet = new HashSet<>(Arrays.asList(office_tasks));

    // 팀별 사원 목록을 저장할 맵
    Map<Integer, List<Integer>> teamMap = new HashMap<>();
    // 사무실 근무자 집합
    Set<Integer> officeWorkers = new HashSet<>();
    // 재택근무 가능 사원 목록
    List<Integer> remoteEligible = new ArrayList<>();

    // 사원 정보 파싱
    for (int i = 0; i < employees.length; i++) {
      String[] parts = employees[i].split(" ");
      int team = Integer.parseInt(parts[0]); // 팀 번호
      boolean canRemote = true;
      boolean mustOffice = false;

      // 업무 확인
      for (int j = 1; j < parts.length; j++) {
        String task = parts[j];
        // 모든 업무가 remoteSet에 속해야 재택근무 가능
        if (!remoteSet.contains(task)) {
          canRemote = false;
        }
        // 하나라도 officeSet에 속하면 사무실 근무
        if (officeSet.contains(task)) {
          mustOffice = true;
          break;
        }
      }

      // 사원 번호는 1부터 시작 (i + 1)
      if (mustOffice) {
        officeWorkers.add(i + 1);
      } else if (canRemote) {
        remoteEligible.add(i + 1);
      } else {
        // 재택근무도 사무실 근무도 아닌 경우, 사무실 근무자로 간주
        officeWorkers.add(i + 1);
      }
      teamMap.computeIfAbsent(team, k -> new ArrayList<>()).add(i + 1);
    }

    // 각 팀 확인
    for (int team = 1; team <= num_teams; team++) {
      List<Integer> members = teamMap.getOrDefault(team, new ArrayList<>());
      boolean hasOfficeWorker = false;

      // 팀 내 사무실 근무자 확인
      for (int member : members) {
        if (officeWorkers.contains(member)) {
          hasOfficeWorker = true;
          break;
        }
      }

      // 사무실 근무자가 없으면, 재택근무 가능한 사원 중 최소 번호를 사무실로 지정
      if (!hasOfficeWorker && !members.isEmpty()) {
        List<Integer> remoteInTeam = new ArrayList<>();
        for (int member : members) {
          if (remoteEligible.contains(member)) {
            remoteInTeam.add(member);
          }
        }
        if (!remoteInTeam.isEmpty()) {
          int minEmployee = Collections.min(remoteInTeam);
          officeWorkers.add(minEmployee);
          remoteEligible.remove(Integer.valueOf(minEmployee));
        }
      }
    }

    // 재택근무 가능 사원 배열로 변환 및 정렬
    int[] answer = remoteEligible.stream().mapToInt(Integer::intValue).toArray();
    Arrays.sort(answer);
    return answer;
  }

  public static void main(String[] args) {
    Solution2_1 sol = new Solution2_1();

    // 예시 입력
    int num_teams = 3;
    String[] remote_tasks = {"development", "marketing", "hometask"};
    String[] office_tasks = {"recruitment", "education", "officetask"};
    String[] employees = {
        "1 development hometask",
        "1 recruitment marketing",
        "2 hometask",
        "2 development marketing hometask",
        "3 marketing",
        "3 officetask",
        "3 development"
    };

    // solution 메서드 호출
    int[] result = sol.solution(num_teams, remote_tasks, office_tasks, employees);

    // 결과 출력
    System.out.println("재택근무 가능한 사원 번호: " + Arrays.toString(result));
  }
}
