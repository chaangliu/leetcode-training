package array;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * Example 1:
 * <p>
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 * Note:
 * <p>
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 * Created by DrunkPiano on 2017/3/7.
 */

public class Pow {
    /**
     * 题意：求x的n次方。剑指offer原题。
     * 解法：top down递归，不需要cache。注意处理 n == -1的情况。
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        //half的每个值对应一个return 不能正向理解
        double half = myPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        }
        if (n % 2 == 1) {
            return half * half * x;
        } else { // n == -1
            return half * half / x; // * x ^ -1 == / x
        }
    }

    /**
     * 20200511review，注意-3 % 2 是 -1，不是1; 而-3 & 1 = 1
     */
    public double myPow__(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;
        double half = myPow__(x, n / 2);
        double compensation = ((n % 2) == 1 ? x : (n % 2) == -1 ? 1 / x : 1);
        return half * half * compensation;
    }
}
