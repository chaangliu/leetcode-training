package dp;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 * 20191030
 */
public class MinCostClimbingStairs {
    /**
     * 题意：给你一个数组，代表每一级台阶的cost，问你走到楼顶时的最小cost是多少。
     * dp[i]表示如果必须走到i这个台阶时累积的min cost
     * dp[i] = min(dp[i - 2], dp[i - 1]) + A[i];
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
        return Math.min(dp[len - 2], dp[len - 1]);
    }

    public int minCostClimbingStairs_O1(int[] cost) {
        int len = cost.length;
        //int[] dp = new int[len];
        int p1 = cost[0];
        int p2 = cost[1];
        int cur;
        for (int i = 2; i < len; i++) {
            cur = Math.min(p1, p2) + cost[i];
            p1 = p2;
            p2 = cur;
        }
        return Math.min(p2, p1);
    }

    /**
     * 讨论区的答案，直接用原来数组操作。
     */
    public int minCostClimbingStairs__(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}
