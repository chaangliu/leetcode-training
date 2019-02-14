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

    //https://leetcode.com/problems/perfect-squares/discuss/71475/Short-Python-solution-using-BFS
    //2. bfs解法，想象成树的bfs，res是最早node.val是0的那个level
    public int numSquares___BFS(int n) {
        if (n <= 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int level = 1;
        int curNum = 1, nextNum = 0;
        while (!queue.isEmpty()) {
            int remain = queue.poll();
            curNum--;
            for (int i = 1; i * i <= remain; i++) {//已犯错误1：<=, NOT <
                if (remain - i * i == 0) return level;//已犯错误2：remain - i * i, NOT n - i * i
                queue.add(remain - i * i);
                nextNum++;
            }
            if (curNum == 0) {
                level++;
                curNum = nextNum;
                nextNum = 0;
            }
        }
        return -1;
    }

    //bfs还有种写法，跟tree bfs一样，每次while开始时，保持queue里面只是同一层的元素
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

    public static void main(String args[]) {
        System.out.println(new PerfectSquares().numSquares___BFS(13));
    }
}