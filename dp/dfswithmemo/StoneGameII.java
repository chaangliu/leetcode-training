package dp.dfswithmemo;

/**
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * The objective of the game is to end with the most stones.
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * The game continues until all the stones have been taken.
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 * Example 1:
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again.
 * Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 * Constraints:
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 * 20191030
 */
public class StoneGameII {
    /**
     * 题意:两个人轮流从一行石头堆里拿石头，每人最多拿2M堆，下一轮的M = max(M, X)。问先手的人最多能拿到多少石头。假设每人都拿全局最优解。
     * 这题我还是模仿StoneGameI的做法，dfs计算从start开始取，先手比后手多拿的数量，最后做一次代数计算。做的过程中才发现，这个dfs返回的值可能是负数的。
     * 另外，这题我在添加memo的时候一开始只申请了一维，也就是存放start的维度，这样会WA的。一定要加上M这个维度。
     * 下面是我的代码。
     */
    public int stoneGameII(int[] piles) {
        int[] prefix = new int[piles.length];
        for (int i = 0; i < prefix.length; i++) {
            prefix[i] = i == 0 ? piles[0] : prefix[i - 1] + piles[i];
        }
        int more = dfs(0, 1, piles, prefix, new Integer[piles.length][piles.length]);
        return (prefix[prefix.length - 1] - Math.abs(more)) / 2 + (more > 0 ? more : 0);
    }

    //dfs返回从start开始取，M限制下, 先手比后手多拿的数量，返回值可能是负数
    private int dfs(int start, int M, int[] piles, int[] prefix, Integer[][] memo) {
        if (start + 2 * M - 1 >= piles.length - 1) {//已犯错误：左边忘记-1
            return prefix[prefix.length - 1] - (start == 0 ? 0 : prefix[start - 1]);//如果当前的人能取完，他一定会取完，递归结束
        }
        if (memo[start][M] != null) return memo[start][M];
        int res = Integer.MIN_VALUE;//已犯错误：写成了0
        for (int i = start; i <= start + 2 * M - 1; i++) {
            int mine = prefix[i] - (start == 0 ? 0 : prefix[start - 1]);
            res = Math.max(res, mine - dfs(i + 1, Math.max(M, i - start + 1), piles, prefix, memo));
        }
        memo[start][M] = res;
        return res;
    }

    /**
     * 讨论区的答案
     * 思路跟我不一样，他的dfs直接返回了答案，并且维护的是后缀数组，因为他想计算的是下一个人最少能拿多少个石头min，这样用总石头减去min就行了。
     */
    private int[] sums;//the sum from piles[i] to the end
    private int[][] hash;

    public int stoneGameII__(int[] piles) {
        if (piles == null || piles.length == 0) return 0;
        int n = piles.length;
        sums = new int[n];
        sums[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + piles[i]; //the sum from piles[i] to the end
        }
        hash = new int[n][n];
        return helper(piles, 0, 1);
    }

    //dfs返回从i开始拿，M限制下, 先手能拿到的最多的石头数量
    private int helper(int[] a, int i, int M) {
        if (i == a.length) return 0;
        if (2 * M >= a.length - i) {
            return sums[i];
        }
        if (hash[i][M] != 0) return hash[i][M];
        int min = Integer.MAX_VALUE;//the min value the next player can get
        for (int x = 1; x <= 2 * M; x++) {
            min = Math.min(min, helper(a, i + x, Math.max(M, x)));
        }
        hash[i][M] = sums[i] - min;  //max stones = all the left stones - the min stones next player can get
        return hash[i][M];
    }
}
