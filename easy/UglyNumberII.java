package easy;

/**
 * 264. Ugly Number II
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * Example:
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note:
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */
public class UglyNumberII {
    //有时候dp不仅仅依赖dp[i - 1]位
    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        int dp[] = new int[n];
        dp[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(factor2, Math.min(factor3, factor5));
            dp[i] = min;
            if (min == factor2) {
                factor2 = 2 * dp[++index2]; // 1 * 2, 2* 2, 3 * 2...
            }
            //注意这里不能加else......
            if (min == factor3) {
                factor3 = 3 * dp[++index3];
            }
            if (min == factor5) {
                factor5 = 5 * dp[++index5];
            }
        }
        return dp[n - 1];
    }

    public static void main(String args[]) {
        new UglyNumberII().nthUglyNumber(10);
    }
}
