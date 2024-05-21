package com.lima.study_alone.inflearn.thejava.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Java8DateTimeApp {
  public static void main(String[] args) {
    // **첫번째 예제**
    //    ● Universal Time Coordinated == Greenwich Mean Time
//    Instant instant = Instant.now();
    //    ● Instant.now(): 현재 UTC (GMT)를 리턴한다. 현재 시간이랑 다르게 나옴
//    System.out.println(instant);
//    System.out.println(instant.atZone(ZoneId.of("UTC")));

    // 지금 이 순간을 기계 시간으로 표현하는 방법
//    ZoneId zone = ZoneId.systemDefault();
//    System.out.println("zone = " + zone);
//    ZonedDateTime zonedDateTime = instant.atZone(zone);
//    System.out.println("zonedDateTime = " + zonedDateTime);

    // **두번째 예제**
    /*
    인류용 일시를 표현하는 방법
      ● LocalDateTime.now(): 현재 시스템 Zone에 해당하는(로컬) 일시를 리턴한다.
      ● LocalDateTime.of(int, Month, int, int, int, int): 로컬의 특정 일시를 리턴한다.
      ● ZonedDateTime.of(int, Month, int, int, int, int, ZoneId): 특정 Zone의 특정 일시를리턴한다.
     */
    LocalDateTime now = LocalDateTime.now();
//    System.out.println(now);
    LocalDateTime birth = LocalDateTime.of(1993, Month.OCTOBER, 16, 17, 0);

    ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    System.out.println("nowInKorea = " + nowInKorea);

    Instant nowInstant = Instant.now();
    ZonedDateTime zonedDateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
    System.out.println("zonedDateTime = " + zonedDateTime);

    // **세번째 예제**
    // 기간을 표현하는 방법
    //    ● Period (휴먼용)
    LocalDate today = LocalDate.now();
    LocalDate thisYearBirthday = LocalDate.of(2022, Month.OCTOBER, 16);
    Period period = Period.between(today, thisYearBirthday);
    System.out.println("period = " + period.getDays());
    System.out.println(period.get(ChronoUnit.DAYS));

    Period until = today.until(thisYearBirthday);
    System.out.println(until.get(ChronoUnit.DAYS));

    // / Duration . beteen() (머신용)
    Instant now2 = Instant.now();
    Instant plus = now2.plus(10, ChronoUnit.SECONDS);
    Duration between = Duration.between(now2, plus);
    System.out.println("between = " + between.getSeconds());

    // **네번째 예제**
    // 파싱 또는 포매팅
    //    ● 미리 정의해둔 포맷 참고
    //    https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#predefined
    //    ● LocalDateTime.parse(String, DateTimeFormatter);
    //    ● Dateteme

    LocalDateTime now3 = LocalDateTime.now();

    DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    System.out.println(now3.format(MMddyyyy));

    LocalDate parse = LocalDate.parse("10/16/1993", MMddyyyy);
    System.out.println(parse);

    // **다섯번째 예제**
    // 레거시 API 지원
    //    ● GregorianCalendar와 Date 타입의 인스턴스를 Instant나 ZonedDateTime으로 변환 가능.
    //    ● java.util.TimeZone에서 java.time.ZoneId로 상호 변환 가능.
    Date date = new Date();
    Instant instant = date.toInstant();
    Date newDate = Date.from(instant);

    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    ZonedDateTime zonedDateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
    GregorianCalendar from = GregorianCalendar.from(zonedDateTime1);

    ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
    TimeZone timeZone = TimeZone.getTimeZone(zoneId);

    // **여섯번째예제**
    LocalDateTime plus1 = now3.plus(10, ChronoUnit.DAYS);
  }
}
