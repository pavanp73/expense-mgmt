package app.steelbox.expense.mgmt.utils;

import app.steelbox.expense.mgmt.model.shared.DateRange;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;

public class DateUtil {

    public static DateRange getDateRange(String month, Integer year) {
        LocalDateTime start;
        LocalDateTime end;
        start = LocalDateTime.of(getYearValue(year), Month.valueOf(getMonthValue(month)), 1, 0, 0);
        end = start.withDayOfMonth(start.toLocalDate().lengthOfMonth());
        return new DateRange(getEpochSeconds(start)-1L, getEpochSeconds(end)+1L);
    }

    private static String getMonthValue(String month) {
        return (month != null && !month.isEmpty()) ?
                Month.valueOf(month.toUpperCase()).name() :
                LocalDateTime.now().getMonth().name();
    }

    private static int getYearValue(Integer year) {
        return (year != null) ? year :
                LocalDateTime.now().getYear();
    }

    private static Long getEpochSeconds(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(OffsetDateTime.now().getOffset());
    }
}
