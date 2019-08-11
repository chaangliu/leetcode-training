package dp.knapsack;

import java.util.HashMap;
import java.util.Map;

/**
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 * <p>
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: d = 1, f = 6, target = 3
 * Output: 1
 * Explanation:
 * You throw one die with 6 faces.  There is only one way to get a sum of 3.
 * Example 2:
 * <p>
 * Input: d = 2, f = 6, target = 7
 * Output: 6
 * Explanation:
 * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 * <p>
 * Input: d = 2, f = 5, target = 10
 * Output: 1
 * Explanation:
 * You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
 * Example 4:
 * <p>
 * Input: d = 1, f = 2, target = 3
 * Output: 0
 * Explanation:
 * You throw one die with 2 faces.  There is no way to get a sum of 3.
 * Example 5:
 * Input: d = 30, f = 30, target = 500
 * Output: 222616187
 * Explanation:
 * The answer must be returned modulo 10^9 + 7.
 * Constraints:
 * <p>
 * 1 <= d, f <= 30
 * 1 <= target <= 1000
 * 20190811
 */
public class NumberOfDiceRollsWithTargetSum {
    /**
     * Input: d = 2, f = 6, target = 7
     * Output: 6
     * Explanation:
     * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
     * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
     */
    int mod = (int) 1e9 + 7;

    /**
     * top-down, dfs with memo
     */
    public int numRollsToTarget_(int d, int f, int target) {
        long res = permute(d, f, target, new HashMap<>());
        System.out.println(res % mod);
        return (int) (res % mod);
    }

    private long permute(int d, int f, int target, Map<String, Long> memo) {
        if (d == 0) return target == 0 ? 1 : 0;//0个骰子可以掷出0
        //下面两行是我contest时写的边界条件，一直无法通过30,30,500的case，还以为是mod出了问题(为什么普通的case能过？不容易理解)。
        //我感觉这种边界条件反而比dp难写，也许应该统一以0为边界？
        //if (d == 0) return 0;
        //if (d == 1) return target <= f ? 1 : 0;
        String key = d + "#" + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long res = 0;
        for (int i = 1; i <= f && i <= target; i++) {
            res = (res + (permute(d - 1, f, target - i, memo))) % mod;
            memo.put(key, res);
        }
        return res;
    }

    /**
     * bottom up dp，类似背包问题那类，也是从后往前思考
     */
    public int numRollsToTarget(int d, int f, int target) {
        long[][] dp = new long[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                for (int k = 1; k <= f && k <= j; k++) {//第i个骰子扔出了k
                    dp[i][j] += (dp[i - 1][j - k]) % mod;
                }
            }
        }
        System.out.println((int) (dp[d][target] % mod));
        return (int) (dp[d][target] % mod);
    }

    /**
     * bottom up dp，滚动数组
     */
    public int numRollsToTarget_1D(int d, int f, int target) {
        long[] dp = new long[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= d; i++) {
            long[] dp1 = new long[target + 1];
            for (int j = 1; j <= target; j++) {
                for (int k = 1; k <= f && k <= j; k++) {//第i个骰子扔出了k
                    dp1[j] += dp[j - k] % mod;
                }
            }
            dp = dp1;//这里并不需要arrayCopy，因为dp1会重新初始化
        }
        System.out.println((int) (dp[target] % mod));
        return (int) (dp[target] % mod);
    }
}
