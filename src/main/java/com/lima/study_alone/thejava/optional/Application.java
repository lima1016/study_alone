package com.lima.study_alone.thejava.optional;

import java.util.ArrayList;
import java.util.List;
/*
메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
  ● 예외를 던진다. (비싸다, 스택트레이스를 찍어두니까.)
  ● null을 리턴한다. (비용 문제가 없지만 그 코드를 사용하는 클리어인트 코드가 주의해야
한다.)
  ● (자바 8부터) Optional을 리턴한다. (클라이언트에 코드에게 명시적으로 빈 값일 수도
  있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.)
Optional
  ● 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨네이너.
주의할 것

  ● 프리미티브 타입용 Optional을 따로 있다. OptionalInt, OptionalLong,...
  ● Collection, Map, Stream Array, Optional은 Opiontal로 감싸지 말 것.
 */

/*
Optional을 만든 목적이 메소드 리턴타입으로 쓰라는 용도로 만들어진거라 그밖에 다른 용도로 쓰신다면  Optional을 만든 취지에 어긋나게 사용하는 겁니다.

설계적인 측면에서 "어떤 필드가 있을 수도 있고 없을 수도 있다"라는 의미인데 "어떤 필드의 값이 있을 수도 있다 없을 수도 있다"와는 전혀 다른 이야기가 됩니다.
해당 필드가 그 도메인에 있는게 맞는지 다시 생각해 보는게 좋겠죠.
스택오버 플로에서 Optional을 개발에 참여했던 개발자가 비슷한 질문에 답글을 단 것을 찾을 수 있습니다.

https://stackoverflow.com/questions/26327957/should-java-8-getters-return-optional-type/26328555#26328555
 */
public class Application {

  public static void main(String[] args) {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(2, "spring data jpa", true));
    springClasses.add(new OnlineClass(3, "spring mvc", false));
    springClasses.add(new OnlineClass(4, "spring core", false));
    springClasses.add(new OnlineClass(5, "rest api development", false));

    OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
    // **첫번째 예제**
    // Nullpoint
//    Duration studyDuration = spring_boot.getProgress().getStudyDuration();
//    System.out.println(studyDuration);

    // **두번째 예제**
    // null 체크 하는 방법 2
//    Progress progress = spring_boot.getProgress();
//    if (progress != null) {
//      System.out.println("progress.getStudyDuration() = " + progress.getStudyDuration());
//    }

  }

}
