package dp;

/**
 * We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.
 * XX  <- domino
 * XX  <- "L" tromino
 * X
 * Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.
 * <p>
 * (In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)
 * <p>
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * The five different ways are listed below, different letters indicates different tiles:
 * XYZ XXZ XYY XXY XYY
 * XYZ YYZ XZZ XYY XXY
 * Note:
 * <p>
 * N  will be in range [1, 1000].
 * 20190928
 */
public class DominoAndTrominoTiling {
    /**
     * 这题铺瓷砖让我想到剑指offer上的一道1*2瓷砖的题目，但是这题的转移方程更难，因为组合更多，甚至要用到i-3的状态，很容易漏掉。
     * 转移方程需要画图来发现，但是我画图的时候漏掉了好几种。。属实难。
     * 另外有一点，对于i - 2有两种方式到i的情况，需要的是2 * dp[i - 2]，而不是，dp[i - 2] + 1，这一点我也弄错了。
     * 另外，等你发现了规律是dp[n]=dp[n-1]+dp[n-2]+ 2*(dp[n-3]+...+dp[0])之后，
     * 还需要利用代数化简（当前，也可以不化简直接做），得到 dp[n]=2*d[n-1]+dp[n-3]，化简也有小技巧，此时就能了math题。
     */
    int numTilings(int N) {
        int md = (int) Math.pow(10, 9) + 7;
        if (N == 1) return 1;
        if (N == 2) return 2;
        if (N == 3) return 5;
        int[] dp = new int[N];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 5;
        for (int i = 3; i < N; ++i) {
            long i1 = 2 * dp[i - 1] % md;
            dp[i] = (int) i1 + dp[i - 3];
            dp[i] %= md;
        }
        return dp[N - 1];
    }
}
