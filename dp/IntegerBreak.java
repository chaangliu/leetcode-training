package dp;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * <p>
 * 20190911
 */
public class IntegerBreak {
    /**
     * 题意：把一个整数分成至少两份，返回最大可能的乘积值。
     * 这题很好，是剑指offer第14题，剪绳子。可以用动态规划和贪心两种方式来做。其中动态规划有可以用记忆化来做。
     * <p>
     * Approach1. DP, O(n^2)
     * 我在想怎么拆分成N个（怎么确定N的数量）？
     * 对于输入的n，可以用一个m(ranging from 1 .. n - 1，有一半是重复的)拆分成两部分；
     * 比如拆分成m 和 n - m，那么, m和n - m又能继续拆分，关键：目标是保证两遍都是最大；那么要不要继续往下拆，拆成几份？取决于之前保存的状态里面的最大值！
     * dp[i] = max( max(m, dp[m]) * max(i - m, dp[i - m]))
     * <p>
     * 等于bottom up解法
     **/
    public int integerBreak(int n) {
        //dp[i] means output when input = i, e.g. dp[4] = 4 (2*2),dp[8] = 18 (2*2*3)...
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++)//枚举区间长度
            for (int m = 1; m <= i / 2; m++) {//枚举分割点
                dp[i] = Math.max(dp[i], Math.max(m, dp[m]) * Math.max(i - m, dp[i - m]));//分割与不分割
            }
        return dp[n];
    }

    /**
     * Approach2. Math, O(n)
     * 分析：首先，我们永远不需要>=4的factor(因子)，因为>=4的factor可以分为2和f-2。所以我们只需要1，2，3。另外，3比2更好。所以优先把n分解成很多个3，加上0到1个2。
     **/
    public int integerBreak__(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        product *= n;

        return product;
    }



    /**
     * Recursion原型:
     * int result = Math.max(2 * integerBreak(10 - 2) * i, 3 * integerBreak(10 - 3), 4 * integerBreak(10 - 4)...)
     */
    public int integerBreak____(int n) {
        if (n <= 2) return 1;//base case
        int solution = 0;
        for (int i = 2; i < n; i++) {
            solution = Math.max(solution, i * integerBreak____(n - i));
        }
        return solution;
    }


    /**
     * 根据上面的原型，给dfs加上memo：
     */
    public int integerBreak_(int n) {
        return dfs(n, new Integer[n + 1]);
    }

    public int dfs(int n, Integer[] dp) {
        if (n <= 2) return 1;//base case
        if (dp[n] != null) return dp[n];
        dp[n] = 0;
        for (int i = 2; i < n; i++) {
            dp[n] = Math.max(dp[n], i * (n - i));
            dp[n] = Math.max(dp[n], i * dfs(n - i, dp));
        }
        return dp[n];
    }

    /**
     * 或者这么写：
     */
    private int solve_topdown(int n, int[] dp) {
        if (n == 2) {
            dp[2] = 1;
            return dp[2];
        }
        if (dp[n] > 0) {
            return dp[n];
        }
        for (int i = n - 1; i >= 2; i--) {
            dp[n] = Math.max(dp[n], i * (n - i));            // do not break
            dp[n] = Math.max(dp[n], solve_topdown(i, dp) * (n - i));// do break
        }
        return dp[n];
    }
}
