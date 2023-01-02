package com.example.testpfsentities.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

public interface DateUtils {
    static Date returnDateAfterDuration(Date date, int duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long timeInSecs = calendar.getTimeInMillis();
        return new Date(timeInSecs + ((long) duration * 60 * 1000));
    }

    static boolean compareTwoDates(Date firstDate, Date secondDate) {
        return firstDate.toInstant().isBefore(secondDate.toInstant());
    }

    static boolean compareTwoDatesShouldEqual(Date firstDate, Date secondDate) {
        return firstDate.getSeconds() == secondDate.getSeconds()
                && firstDate.getMinutes() == secondDate.getMinutes()
                && firstDate.getHours() == secondDate.getHours()
                && firstDate.getDay() == secondDate.getDay()
                && firstDate.getMonth() == secondDate.getMonth()
                && firstDate.getYear() == secondDate.getYear()
                ;
//        return firstDate.getTime() == secondDate.getTime();
    }
}
