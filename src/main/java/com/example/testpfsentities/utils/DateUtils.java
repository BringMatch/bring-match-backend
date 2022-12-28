package com.example.testpfsentities.utils;

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
        return firstDate.getTime() <= secondDate.getTime();
    }
}
