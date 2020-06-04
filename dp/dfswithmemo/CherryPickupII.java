package dp.dfswithmemo;

/**
 * 给你一个 rows x cols 的矩阵 grid 来表示一块樱桃地。 grid 中每个格子的数字表示你能获得的樱桃数目。
 * 你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 (0,0) 出发，机器人 2 从右上角格子 (0, cols-1) 出发。
 * 请你按照如下规则，返回两个机器人能收集的最多樱桃数目：
 * 从格子 (i,j) 出发，机器人可以移动到格子 (i+1, j-1)，(i+1, j) 或者 (i+1, j+1) 。
 * 当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。
 * 当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。
 * 两个机器人在任意时刻都不能移动到 grid 外面。
 * 两个机器人最后都要到达 grid 最底下一行。
 * 20200603
 */
public class CherryPickupII {
    /**
     * 题意：两个机器人从左下角和右上角往最后一行走，每次只能往下面一行的左中右三个相邻的格子走。问最多能摘到多少个樱桃。
     * 解法：这题的DP的状态是x1,x2,y，因为y是相同的，所以可以从4维缩短到3维。
     * 但是的top down解法跟我往常理解的有个不一样的地方是，虽然它是top down, 但是确是从第一行往下的。
     */
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][][] dp = new Integer[m][n][n];
        return dfs(grid, m, n, 0, 0, n - 1, dp);
    }

    int dfs(int[][] grid, int m, int n, int r, int c1, int c2, Integer[][][] dp) {
        if (r == m) return 0; // Reach to bottom row
        if (dp[r][c1][c2] != null) return dp[r][c1][c2];
        int ans = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nc1 = c1 + i, nc2 = c2 + j;
                if (nc1 >= 0 && nc1 < n && nc2 >= 0 && nc2 < n) {
                    ans = Math.max(ans, dfs(grid, m, n, r + 1, nc1, nc2, dp));
                }
            }
        }
        int cherries = c1 == c2 ? grid[r][c1] : grid[r][c1] + grid[r][c2];
        return dp[r][c1][c2] = ans + cherries;
    }


    /**
     * WNJXYK的DP写法，也很清晰。
     */
    public int cherryPickup__DP(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][n]; // dp[r][a][b]表示两个机器人分别在(a, r)和(b, r)位置时的最大收益
        for (int[][] a : dp)
            for (int[] b : a) {
                for (int i = 0; i < b.length; i++) b[i] = -1;
            }
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int r = 0; r < m - 1; r++) { // 枚举行数
            for (int a = 0; a < n; a++) { // 枚举机器人a的纵坐标
                for (int b = 0; b < n; b++) { //枚举机器人b的纵坐标
                    if (dp[r][a][b] == -1) continue; //跳过第一行中的其他位置
                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {// 一个错误，写成了d2 < 1，找了一小时
                            int na = a + d1, nb = b + d2;
                            if (na < 0 || na >= n || nb < 0 || nb >= n) continue;
                            int gain = grid[r + 1][na] + (na != nb ? grid[r + 1][nb] : 0);
                            dp[r + 1][na][nb] = Math.max(dp[r + 1][na][nb], dp[r][a][b] + gain);
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp[m - 1][i][j]);
            }
        }
        return res;
    }
}
