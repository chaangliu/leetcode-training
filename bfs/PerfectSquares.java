package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * <p>
 * 20190125
 */
public class PerfectSquares {

    /**
     * 题意：给你一个n，问最少由几个数的平方和能组成n。
     * 解法：DP或者BFS，思路都是从[1,sqrt(n)]去凑n。BFS凑了几层就代表最少需要几个。
     */
    //1. dp
    //状态转移方程： //dp[i] = min(dp[i - j * j] + 1)
    public int numSquares___DP(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //2. BFS
    public int numSquares___BFS2(int n) {
        if (n <= 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int remain = queue.poll();
                for (int j = 1; j * j <= remain; j++) {
                    if (j * j == remain) return level;
                    queue.add(remain - j * j);
                }
            }
        }
        return -1;
    }
}