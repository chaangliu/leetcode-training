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
    /**
     * 题意：找出第n个丑数。
     * 解法：递推dp[i], dp[i] = min(n2,n3,n5)
     */
    public int nthUglyNumber(int n) {
        int idx1 = 0, idx2 = 0, idx3 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[idx1] * 2, n3 = dp[idx2] * 3, n5 = dp[idx3] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            // 判断取用了哪个数，就给那个数的idx+1
            if (dp[i] == n2) idx1++;
            if (dp[i] == n3) idx2++;
            if (dp[i] == n5) idx3++;
        }
        return dp[n - 1];
    }
}
