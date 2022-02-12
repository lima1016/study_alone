package com.lima.study_alone.thejava.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
자바 8에 새로운 날짜와 시간 API가 생긴 이유
    ● 날짜 시간 처리가 복잡한 애플리케이션에서는 보통 Joda Time을 쓰곤했다.
 */
public class DataTimeApplication {
  public static void main(String[] args) throws InterruptedException {
    // ● 클래스 이름이 명확하지 않다. Date인데 시간까지 다룬다.
    Date date = new Date();
    long time = date.getTime();
    // 인류용 시간(human time)
    System.out.println(date);
    // 기계용 시간 (machine time)
    System.out.println(time);

    // ● 그전까지 사용하던 java.util.Date 클래스는 mutable(객체 상태를 바꿀수있음) 하기 때문에 thead safe하지 않다.
    Thread.sleep(1000 * 3);
    Date after3Seconds = new Date();
    System.out.println(after3Seconds);
    after3Seconds.setTime(time);
    // ● 버그 발생할 여지가 많다. (타입 안정성이 없고, 월이 0부터 시작한다거나..)
    Calendar calendar = new GregorianCalendar(1993, Calendar.OCTOBER, 16);
    System.out.println(calendar.getTime());
    // Calendar 에서 getTime() 하면 Date가 나옴 엉망임!
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    System.out.println(calendar.getTime());

    /*
    주요 API
        ● 기계용 시간 (machine time)과 인류용 시간(human time)으로 나눌 수 있다.
        ● 기계용 시간은 EPOCK (1970년 1월 1일 0시 0분 0초)부터 현재까지의 타임스탬프를표현한다.
        ● 인류용 시간은 우리가 흔히 사용하는 연,월,일,시,분,초 등을 표현한다.
        ● 타임스탬프는 Instant를 사용한다.
        ● 특정 날짜(LocalDate), 시간(LocalTime), 일시(LocalDateTime)를 사용할 수 있다.
        ● 기간을 표현할 때는 Duration (시간 기반)과 Period (날짜 기반)를 사용할 수 있다.
        ● DateTimeFormatter를 사용해서 일시를 특정한 문자열로 포매팅할 수 있다.
     */

  }
}