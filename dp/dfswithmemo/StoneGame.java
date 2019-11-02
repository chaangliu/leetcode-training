package dp.dfswithmemo;

/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * <p>
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 * <p>
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
 * <p>
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 * Example 1:
 * Input: [5,3,4,5]
 * Output: true
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 * Note:
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 * 20191029
 */
public class StoneGame {
    /**
     * 题意：有n堆石子，n是偶数，石子总和是奇数。两个人轮流从head或者tail取一堆，最后得到的石子更多的人赢，问先手的人能不能赢。假设每个人都做全局最优选择。
     * 解法1：top down dp, 拿到后发现跟Predict the winner那题一样。
     */
    public boolean stoneGame(int[] piles) {
        return dfs(0, piles.length - 1, piles, new Integer[piles.length][piles.length]) > 0;
    }

    //dfs返回[l,r]内我先手选择的比对方最多多几个stones
    private int dfs(int l, int r, int[] piles, Integer[][] memo) {
        if (l == r) return piles[l];
        int ll = memo[l + 1][r] != null ? memo[l + 1][r] : dfs(l + 1, r, piles, memo);//已犯错误：写成了piles[l] - dfs(l + 1, r , piles, memo)
        int rr = memo[l][r - 1] != null ? memo[l][r - 1] : dfs(l, r - 1, piles, memo);
        memo[l][r] = Math.max(piles[l] - ll, piles[r] - rr);
        return memo[l][r];
    }

    /**
     * 解法2：Math, 直接return true。用的堆的总数是偶数这个条件，因为这样一来你会发现先手的人总有办法拿完所有的偶数堆或者奇数堆。
     */
    public boolean stoneGame_(int[] piles) {
        return true;
    }
}