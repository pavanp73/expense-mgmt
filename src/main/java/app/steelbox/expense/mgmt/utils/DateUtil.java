package app.steelbox.expense.mgmt.utils;

import app.steelbox.expense.mgmt.model.shared.DateRange;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

public class DateUtil {

    public static DateRange getDateRange(String month, String year) {
        return getDateRange(getMonthValue(month), getYearValue(year));
    }

    private static DateRange getDateRange(String month, int year) {
        LocalDateTime start;
        LocalDateTime end;
        start = LocalDateTime.of(year, Month.valueOf(month), 1, 0, 0, 0);
        end = start.withDayOfMonth(start.toLocalDate().lengthOfMonth());
        return new DateRange(getEpochSeconds(start)-1L, getEpochSeconds(end)+1L, month, year);
    }

    private static String getMonthValue(String month) {
        return (month != null && !month.isEmpty()) ?
                Month.valueOf(month.toUpperCase()).name() :
                LocalDateTime.now().getMonth().name();
    }

    private static int getYearValue(String year) {
        return (year != null) ? Integer.parseInt(year) :
                LocalDateTime.now().getYear();
    }

    private static Long getEpochSeconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
