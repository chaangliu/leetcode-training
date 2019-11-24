package dp.dfswithmemo;

import java.util.HashMap;

/**
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).
 * Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * Example 1:
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * Example 2:
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * Example 3:
 * Input: steps = 4, arrLen = 2
 * Output: 8
 * Constraints:
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 * 20191124
 */
public class NumberofWaystoStayintheSamePlaceAfterSomeSteps {
    /**
     * 题意：一个指针初始在0位置，问在数组上移动(向左，向右或者停留)steps次之后回到0的走法一共有多少。
     * 解法：这题一看就是dfs + memo了，之前的训练还是有效果的。感觉做过类似的题。周赛过程中WA了两发还是过了。如果用数组会MLE，就改了Map。
     */
    int mod = (int) 1e9 + 7;

    public int numWays(int steps, int arrLen) {
        HashMap<String, Integer> memo = new HashMap<>();
        return dfs(steps, arrLen, 0, memo);
    }

    private int dfs(int steps, int len, int cur, HashMap<String, Integer> memo) {
        if (cur < 0 || cur >= len) return 0;
        if (steps < 0) return 0;
        String key = cur + "#" + steps;
        if (memo.containsKey(key)) return memo.get(key);
        if (cur == 0 && steps == 0) {
            return 1;
        }
        int res = 0;
        for (int i = -1; i <= 1; i++) {
            res += dfs(steps - 1, len, cur + i, memo) % mod;
            res = res % mod;
        }
        memo.put(key, res);
        return res;
    }


    /**
     * 摘抄一个non-recursive解法
     * // dp[arrLen][steps].
     * // There are basically three ways to at index j and step i. They are
     * // 1 stay at index j. Contribution from dp[j][i-1];
     * // 2 move right from index j-1. Contribution from dp[j-1][i-1].
     * // 3 move left from index j+1. Contribution from dp[j+1][i-1].
     * // Optimizations:
     * // 1 rolling matrix to avoid abusing the memory.
     * // 2 earlier exist when reaching the regions of all zeros.
     */
    public int numWays__(int steps, int arrLen) {
        int[][] dp = new int[arrLen][2];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; ++i) {
            for (int j = 0; j < arrLen; ++j) {
                // stay
                dp[j][i % 2] = (dp[j][(i - 1) % 2]) % this.mod;
                // move right.
                if (j > 0) {
                    dp[j][i % 2] = (dp[j][i % 2] + dp[j - 1][(i - 1) % 2]) % this.mod;
                }
                // move left
                if (j < arrLen - 1) {
                    dp[j][i % 2] = (dp[j][i % 2] + dp[j + 1][(i - 1) % 2]) % this.mod;
                }
                if (dp[j][i % 2] == 0) {
                    break;
                }
            }
        }
        return dp[0][steps % 2];
    }
}
