package dp.knapsack;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * <p>
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * <p>
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * 20190531
 */
public class LastStoneWeightII {
    /**
     * 题意是每次随便选两个石头砸碎，它们之差的绝对值成为新的石头，问最后剩下石头的重量最小是多少。
     * 这题很难理解，首先要把它理解成：把石头分成两组，它们重量之和的差最小是多少。
     * 转换成dp背包问题，boolean dp[i]代表石头能否组成i的重量。
     */
    public int lastStoneWeightII(int[] A) {
        boolean[] dp = new boolean[1501];
        dp[0] = true;
        int sumA = 0;
        for (int a : A) {
            sumA += a;
            for (int i = sumA; i >= a; --i)//每加进来一个新的石头，都要更新从这块石头的重量到总重量是否能使用石头构成。比如{2,7..}加入7的时候，重量9取决于重量(9-7)=2，重量8取决于重量1
                dp[i] = dp[i] || dp[i - a];
        }
        for (int i = sumA / 2; i > 0; --i)
            if (dp[i]) return sumA - i - i;
        return 0;
    }
}
