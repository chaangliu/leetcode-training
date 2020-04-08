package dp.dfswithmemo.minmax;

/**
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 * <p>
 * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.
 * <p>
 * The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
 * <p>
 * The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.
 * <p>
 * Assume Alice and Bob play optimally.
 * <p>
 * Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
 * Example 1:
 * <p>
 * Input: values = [1,2,3,7]
 * Output: "Bob"
 * Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
 * Example 2:
 * <p>
 * Input: values = [1,2,3,-9]
 * Output: "Alice"
 * Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
 * If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with value = -9 and lose.
 * If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with value = -9 and also lose.
 * Remember that both play optimally so here Alice will choose the scenario that makes her win.
 * Example 3:
 * <p>
 * Input: values = [1,2,3,6]
 * Output: "Tie"
 * Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
 * Example 4:
 * <p>
 * Input: values = [1,2,3,-1,-2,-3,7]
 * Output: "Alice"
 * Example 5:
 * <p>
 * Input: values = [-1,-2,-3]
 * Output: "Tie"
 * Constraints:
 * <p>
 * 1 <= values.length <= 50000
 * -1000 <= values[i] <= 1000
 * 20200408
 */
public class StoneGameIII {
    /**
     * 题意：Alice和Bob每人能从石头堆的开始处拿1,2,3个石头，石头上印有value。问Alice先手能不能赢。
     * 解法：跟前两题一样。这题我纠结的一点是memo要不要记录每次取的石子数，最后发现没记录也过了。
     */
    public String stoneGameIII(int[] stoneValue) {
        int res = dfs(0, new Integer[stoneValue.length], stoneValue);
        return res > 0 ? "Alice" : res < 0 ? "Bob" : "Tie";
    }

    // dfs返回先手的人比start开始拿k个，最多能比后手的人多拿多少个石头
    private int dfs(int start, Integer[] memo, int[] val) {
        if (start >= val.length) return 0;
        if (memo[start] != null) return memo[start];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < 3 && start + i < val.length; i++) {
            int a = 0;
            for (int j = 0; j <= i; j++) a += val[start + j];
            res = Math.max(res, a - dfs(start + i + 1, memo, val));
        }
        memo[start] = res;
        return res;
    }

    /**
     * bottom up解法
     */
    public String stoneGameIII_(int[] A) {
        int n = A.length, dp[] = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
                take += A[i + k];
                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}
