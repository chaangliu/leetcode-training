package dp.dfswithmemo;

/**
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours: Red, Yellow or Green while making sure that no two adjacent cells have the same colour (i.e no two cells that share vertical or horizontal sides have the same colour).
 * <p>
 * You are given n the number of rows of the grid.
 * <p>
 * Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 * Example 1:
 * Input: n = 1
 * Output: 12
 * Explanation: There are 12 possible way to paint the grid as shown:
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 54
 * Example 3:
 * <p>
 * Input: n = 3
 * Output: 246
 * Example 4:
 * <p>
 * Input: n = 7
 * Output: 106494
 * Example 5:
 * <p>
 * Input: n = 5000
 * Output: 30228214
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 * 20200413
 */
public class NumberofWaystoPaintN3Grid {
    /**
     * 题意：一个grid，相邻的格子不能颜色相同。
     * 解法：dfs with memo。另有一种math递推做法，很巧妙，但我觉得想不到。。参考lee的代码。
     */
    public int numOfWays(int n) {
        return dfs(new int[n + 1][4][4][4], n, 0, 0, 0); // 0,0,0就表示上一行所有颜色组合的种类之和
    }

    /**
     * memo[i][a][b][c]表示，第i行被涂色a,b,c的时候有多少种方式
     **/
    private int dfs(int[][][][] memo, int i, int a, int b, int c) {
        if (i == 0) return 1; // base case
        if (memo[i][a][b][c] != 0) return memo[i][a][b][c];
        int[] color = {1, 2, 3};
        int res = 0;
        for (int c1 : color) {
            if (c1 == a) continue;
            for (int c2 : color) {
                if (c2 == b || c2 == c1) continue;
                for (int c3 : color) {
                    if (c3 == c || c3 == c2) continue;
                    res += dfs(memo, i - 1, c1, c2, c3);
                    res %= 1000_000_007;
                }
            }
        }
        return memo[i][a][b][c] = res;
    }
}
