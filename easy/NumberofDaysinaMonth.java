package easy;

/**
 * Given a year Y and a month M, return how many days there are in that month.
 * Example 1:
 * Input: Y = 1992, M = 7
 * Output: 31
 * Example 2:
 * <p>
 * Input: Y = 2000, M = 2
 * Output: 29
 * Example 3:
 * <p>
 * Input: Y = 1900, M = 2
 * Output: 28
 * Note:
 * <p>
 * 1583 <= Y <= 2100
 * 1 <= M <= 12
 * 20190714
 * 【0分easy题】
 */
public class NumberofDaysinaMonth {
    /**
     * 1900年的闰平年判定：
     * 是平年，不是闰年 (闰年判定方法：能被400整除。或者能被4整除但不能被100整除。)
     */
    public int numberOfDays(int Y, int M) {
        if (M == 1 || M == 3 || M == 5 || M == 7 || M == 8 || M == 10 || M == 12) {
            return 31;
        }
        if (M == 2) {
            return Y % 400 == 0 || Y % 4 == 0 && Y % 100 != 0 ? 29 : 28;
        }
        return 30;
    }
}
