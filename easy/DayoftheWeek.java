package easy;

import java.time.Month;
import java.time.Year;

/**
 * Given a date, return the corresponding day of the week for that date.
 * <p>
 * The input is given as three integers representing the day, month and year respectively.
 * <p>
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 * Example 2:
 * <p>
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 * Example 3:
 * <p>
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given dates are valid dates between the years 1971 and 2100.
 * 20190908
 */
public class DayoftheWeek {

    /**
     * Zeller Formula
     */
    public String dayOfTheWeek(int d, int m, int y) {
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (m < 3) {
            m += 12;
            y -= 1;
        }
        int c = y / 100;
        y = y % 100;
        int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
        return days[(w + 7) % 7];
    }

    public String dayOfTheWeek_(int day, int month, int year) {
        String[] weeks = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int weekOfDay = Year.of(year).atMonth(Month.of(month)).atDay(day).getDayOfWeek().getValue() - 1;
        return weeks[weekOfDay];
    }
}
