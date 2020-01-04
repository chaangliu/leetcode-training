package math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 * <p>
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 * Created by DrunkPiano on 2017/3/6.
 */

public class DivideTwoIntegers {
    /**
     * 题意：不用乘除和mod实现两数相除。
     * 这种限制一看就是面试题了。和剑指offer的《加减乘除做加法》一样，可以用bit manipulation。
     * 操作方法是，让divisor每次左移一位，直到即将超过dividend为止。在左移的过程中，用一个mul来记录左移的次数。
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int res = 0;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        while (dvs <= dvd) {
            long temp = dvs, mul = 1;
            while (dvd >= temp << 1) {
                temp <<= 1;
                mul <<= 1;
            }
            dvd -= temp;
            res += mul;
        }
        return sign == 1 ? res : -res;
    }

    public static void main(String args[]) {
        new DivideTwoIntegers().divide(14, 3);
    }
}

