package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
 * <p>
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：
 * <p>
 * 从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
 * 当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
 * 如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
 * 示例 1:
 * <p>
 * 输入: grid =
 * [[0, 1, -1],
 * [1, 0, -1],
 * [1, 1,  1]]
 * 输出: 5
 * 解释：
 * 玩家从（0,0）点出发，经过了向下走，向下走，向右走，向右走，到达了点(2, 2)。
 * 在这趟单程中，总共摘到了4颗樱桃，矩阵变成了[[0,1,-1],[0,0,-1],[0,0,0]]。
 * 接着，这名玩家向左走，向上走，向上走，向左走，返回了起始点，又摘到了1颗樱桃。
 * 在旅程中，总共摘到了5颗樱桃，这是可以摘到的最大值了。
 * 说明:
 * <p>
 * grid 是一个 N * N 的二维数组，N的取值范围是1 <= N <= 50。
 * 每一个 grid[i][j] 都是集合 {-1, 0, 1}其中的一个数。
 * 可以保证起点 grid[0][0] 和终点 grid[N-1][N-1] 的值都不会是 -1。
 * 20200605
 */
public class CherryPickup {
    /**
     * 题意：一个二维数组，从左下角到右下角走，-1不能走，1或者0代表樱桃数量。到达右下角后再往左上角走。问最多能收集多少樱桃。
     * 解法：DP，
     * 我的想法1：我一开始就想模拟先往右下角走再往左上角走，发现：第一，路径不容易记录 第二：去的时候取尽可能多的，这个策略可行吗？总觉得需要往返一起考虑。
     * 我的想法2：看了一眼题解，说可以想象两个人分别从左上角往右下角走。写了一下(bottom up形式)，觉得不太对，因为每次枚举步数又会从头开始走。看了下答案，别人是从后往前枚举的。原因未知。似乎用top down比较容易理解。先这样，晚上再看一下。
     */

    /**
     * 四维，top down
     * 注意这题，recursion是+1而不是-1。
     */
    public int cherryPickup___(int[][] grid) {
        return Math.max(0, cherryPickup(grid, grid.length, 0, 0, 0, 0));
    }

    private int cherryPickup(int[][] grid, int n, int r1, int c1, int r2, int c2) {
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) return Integer.MIN_VALUE;
        if (r1 == n - 1 && c1 == n - 1) return grid[r1][c1];
        if (r2 == n - 1 && c2 == n - 1) return grid[r2][c2];
        int cherries;
        if (r1 == r2 && c1 == c2) cherries = grid[r1][c1];
        else cherries = grid[r1][c1] + grid[r2][c2];
        //    P1     |      P2
        //   DOWN    |     DOWN
        //   DOWN    |     RIGHT
        //   RIGHT   |     DOWN
        //   RIGHT   |     RIGHT
        cherries += Math.max(
                Math.max(cherryPickup(grid, n, r1 + 1, c1, r2 + 1, c2), cherryPickup(grid, n, r1 + 1, c1, r2, c2 + 1)),
                Math.max(cherryPickup(grid, n, r1, c1 + 1, r2 + 1, c2), cherryPickup(grid, n, r1, c1 + 1, r2, c2 + 1)));

        return cherries;
    }

    /**
     * top down (AC)
     * 由于步长相同，可以减少一维
     */
    public int cherryPickup__(int[][] A) {
        int n = A.length;
        Integer[][][] dp = new Integer[n][n][n];
        // dp[0][0][0] = A[0][0];
        return Math.max(0, dfs(A, dp, 0, 0, 0));
    }

    private int dfs(int[][] A, Integer[][][] dp, int r1, int r2, int c1) {

        int N = A.length;

        int c2 = r1 + c1 - r2;
        if (r1 == N || r2 == N || c1 == N || c2 == N || A[r1][c1] == -1 || A[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }
        if (r1 == N - 1 && c1 == N - 1) return A[r1][c1]; // base case
        if (dp[r1][r2][c1] != null) return dp[r1][r2][c1];
        int gain = r1 == r2 ? A[r1][c1] : A[r1][c1] + A[r2][c2];
        int next = Integer.MIN_VALUE; // next 不能 = 0
        next = Math.max(next, dfs(A, dp, r1, r2 + 1, c1 + 1)); // 1号向右走, 2号向下走
        next = Math.max(next, dfs(A, dp, r1, r2, c1 + 1)); // 1号向右走， 2号向右走
        next = Math.max(next, dfs(A, dp, r1 + 1, r2, c1)); // 1号向下走，2号向右走
        next = Math.max(next, dfs(A, dp, r1 + 1, r2 + 1, c1)); // 1号向下走，2号向下走
        return dp[r1][r2][c1] = next + gain;
    }


    /**
     * 我的想法2（Wrong Answer）
     */
    public int cherryPickup_(int[][] A) {
        int m = A.length, n = A[0].length, len = m + n - 2;
        Integer[][] dp = new Integer[len][len]; // dp[i][j]表示两人分别从(0, 0)走到(i, s - i) 和 (j, s - j)的两条路最多获取多少樱桃。
        dp[0][0] = A[0][0];
        for (int s = 1; s < len; s++) {
            for (int i = 0; i < m && i <= s; i++) {
                if (s - i >= n || A[i][s - i] == -1) continue;
                for (int j = 0; j < m && j <= s; j++) {
                    if (s - j >= n || A[j][s - j] == -1) continue;
                    int gain = i == j ? A[i][s - 1] : (A[i][s - i] + A[j][s - j]); // 当前1或2个格子能收取的数量
                    int max = 0;
                    if (i > 0 && dp[i - 1][j] != null) {
                        max = Math.max(max, dp[i - 1][j]);
                    }
                    if (j > 0 && dp[i][j - 1] != null) {
                        max = Math.max(max, dp[i][j - 1]);
                    }
                    dp[i][j] = max + gain;
                }
            }
        }
        return dp[len - 1][len - 1];
    }

    /**
     * 我的想法1（Wrong Answer)
     */
    public int cherryPickup(int[][] A) {
        int m = A.length, n = A[0].length;
        Record[][] dp1 = new Record[m][n];
        Integer[][] dp2 = new Integer[m][n];
        dp1[0][0] = new Record(A[0][0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == -1) continue;
                dp1[i][j] = new Record(A[i][j]);
                if (i - 1 >= 0 && dp1[i - 1][j] != null) {
                    dp1[i][j].amount = Math.max(dp1[i - 1][j].amount, dp1[i][j].amount);
                }
                if (j - 1 >= 0 && dp1[i][j - 1] != null) {
                    dp1[i][j].amount = Math.max(dp1[i][j - 1].amount, dp1[i][j].amount);
                }
                dp1[i][j].path.add(new int[]{i, j});
            }
        }
        List<int[]> path = dp1[m - 1][n - 1].path;
        for (int[] p : path) {
            A[p[0]][p[1]] = 0;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (A[i][j] == -1) continue;
                if (i + 1 < m && dp2[i + 1][j] != null) {
                    dp2[i][j] = Math.max(dp2[i + 1][j], dp2[i][j]);
                }
                if (j + 1 < n && dp2[i][j + 1] != null) {
                    dp2[i][j] = Math.max(dp2[i][j + 1], dp2[i][j]);
                }
            }
        }
        return dp2[0][0] != null ? dp2[0][0] : 0;
    }

    class Record {
        int amount;
        List<int[]> path = new ArrayList<>();

        Record(int n) {
            amount = n;
        }
    }
}
