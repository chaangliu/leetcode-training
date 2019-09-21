package dfs.dfswithcache;

/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.
 * <p>
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 * <p>
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 * 20190920
 */
public class PredictTheWinner {
    /**
     * 这题挺抽象的，转移方程我看了好久，最后看例子才看懂。
     * Approach 1, dfs with memo
     * dp[i][j]代表[i,j]范围先手的人比后手的人多拿的score。比如[1,5,2]，我选1，然后对方成了先手，在5,2里选一个，他一定可以多拿3。也就是说我当前拿的必须比他即将多拿的还要多才能赢
     */

    public boolean PredictTheWinner(int[] nums) {
        return helper(0, nums.length - 1, nums) >= 0;
    }

    //helper返回palyer1 - palyer2的score差值
    private int helper(int l, int r, int[] nums) {
        return l == r ? nums[l] : Math.max(nums[l] - helper(l + 1, r, nums), nums[r] - helper(l, r - 1, nums));
    }

    /**
     * 加上memo
     */
    public boolean PredictTheWinner_with_memo(int[] nums) {
        return helper(0, nums.length - 1, nums, new Integer[nums.length][nums.length]) >= 0;
    }

    //helper返回palyer1 - palyer2的score差值
    private int helper(int l, int r, int[] nums, Integer[][] memo) {
        if (l == r) return nums[l];
        int ll = memo[l + 1][r] != null ? memo[l + 1][r] : helper(l + 1, r, nums, memo);
        int rr = memo[l][r - 1] != null ? memo[l][r - 1] : helper(l, r - 1, nums, memo);
        memo[l][r] = Math.max(nums[l] - ll, nums[r] - rr);
        return memo[l][r];
    }


    /**
     * Approach 2, 改写成dp(还能转1D的)
     */
    public boolean PredictTheWinner__(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = Math.max(nums[j] - dp[i][j - 1], nums[i] - dp[i + 1][j]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
