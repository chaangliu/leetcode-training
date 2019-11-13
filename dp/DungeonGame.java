package dp;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 * Note:
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 * 20191113
 */
public class DungeonGame {
    /**
     * 题意：一个骑士从左上角向右下角救公主，每次只能往右/往下一格，每个格子的数字代表生命的增加减少。生命到达0的时候会死亡。问最少需要多少生命才能在抵达最后一个格子之后至少还有1点hp。
     * 转移方程，dp代表到达i,j之后最少需要的hp
     * 从右下角往左上角：dp[i][j] = max(1,    min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]  )，重点是外围的max(1, ..)，代表当前保证要至少有1点hp
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][dp[0].length - 1] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[dp.length - 1][i] = Integer.MAX_VALUE;
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int y = m - 1; y >= 0; y--) {
            for (int x = n - 1; x >= 0; x--) {
                dp[y][x] = Math.max(Math.min(dp[y + 1][x], dp[y][x + 1]) - dungeon[y][x], 1);
            }
        }
        return dp[0][0];
    }
}
