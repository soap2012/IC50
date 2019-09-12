package com.shenyue.IC50.lamda;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoField.MICRO_OF_SECOND;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;

/**
 * Created by shenyue3 on 2019/8/5.
 */
public class DateAndTime {
    public static void main(String[] args){
        Instant now = Instant.now();
        System.out.println(now.toEpochMilli());
        System.out.println(System.currentTimeMillis());
        Duration duration = Duration.between(now,Instant.now());
        System.out.println(duration.toNanos());

        Instant torrow = now.plusMillis(24*3600*1000L);
        System.out.println(torrow.toEpochMilli());

        LocalDate date = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(date.toString());
        LocalDate dateNew = date.plusMonths(2);
        System.out.println(dateNew.toString());

        //2017-02-03的下一个星期五(包含当天)  2017-03-03
        LocalDate.of(2017, 2, 3).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        //2017-02-03的下一个星期五(不包含当天)  2017-02-10
        LocalDate.of(2017, 2, 3).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        //2月中的第3个星期五  2017-02-17
        LocalDate.of(2017, 2, 3).with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
        //2月中的最后一个星期五  2017-02-24
        LocalDate.of(2017, 2, 3).with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        //下个月的第一天
        LocalDate.of(2017, 2, 3).with(TemporalAdjusters.firstDayOfNextMonth());


    }
}
