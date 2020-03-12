package easy;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Write a program to count the number of days between two dates.
 * <p>
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 * Example 1:
 * <p>
 * Input: date1 = "2019-06-29", date2 = "2019-06-30"
 * Output: 1
 * Example 2:
 * <p>
 * Input: date1 = "2020-01-15", date2 = "2019-12-31"
 * Output: 15
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given dates are valid dates between the years 1971 and 2100.
 * 20200224
 */
public class NumberofDaysBetweenTwoDates {
    /**
     * 题意：计算两个日子之间的天数绝对值。
     * 计算方法就是调库。
     */
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs((int) ChronoUnit.DAYS.between(LocalDate.parse(date1), LocalDate.parse(date2)));
    }

    public static int daysBetweenDates_(String date1, String date2) {
        int res = -1;
        try {
            res = daysBetween(date1, date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int daysBetween(String smdate, String bdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Math.abs(Integer.parseInt(String.valueOf(between_days)));
    }
}
