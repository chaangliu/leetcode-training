package dp.dfswithmemo;

import java.util.Arrays;

/**
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
 * 20191031
 */
public class KnightProbabilityinChessboard {
    /**
     * 题意：棋盘上一个骑士任意方向走K步，求最后留在棋盘上的概率。
     * 解法：这题我模仿TossStrangeCoins那题的概率DP来做的，没想到一次AC了。感觉开窍了。
     * 拿到之后发现因为可能用到外围的状态，所以不能顺序地迭代做DP，很自然想到记忆化递归。
     * 状态转移方程就是，当前位置的概率等于8个方向每个方向的概率相加*1/8。
     * 我的代码，top down, dfs with memo：
     */
    public double knightProbability(int N, int K, int r, int c) {
        int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        return dfs(r, c, N, new Double[N][N][K + 1], K, dirs);
    }

    //dfs返回在r,c位置走K步留在棋盘的概率。
    private double dfs(int r, int c, int N, Double[][][] memo, int K, int[][] dirs) {
        if (K == 0) return r >= 0 && r < N && c >= 0 && c < N ? 1 : 0; //走完之后留在棋盘上则留在棋盘上的概率是1，否则是0
        if (!(r >= 0 && r < N && c >= 0 && c < N)) return 0;//没走完已经掉下去，则留在棋盘上的概率是0
        if (memo[r][c][K] != null) return memo[r][c][K];
        double res = 0;
        for (int[] dir : dirs) {
            res += 0.125 * dfs(r + dir[0], c + dir[1], N, memo, K - 1, dirs);
        }
        memo[r][c][K] = res;
        return res;
    }


    /**
     * 讨论区的top down代码。
     */
    private int[][] dir = new int[][]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
    private double[][][] dp;

    public double knightProbability__(int N, int K, int r, int c) {
        dp = new double[N][N][K + 1];
        return find(N, K, r, c);
    }

    public double find(int N, int K, int r, int c) {
        if (r < 0 || r > N - 1 || c < 0 || c > N - 1) return 0;//出去了，概率是0
        if (K == 0) return 1;//没出去就走完了，概率是1
        if (dp[r][c][K] != 0) return dp[r][c][K];
        double rate = 0;
        for (int i = 0; i < dir.length; i++) rate += 0.125 * find(N, K - 1, r + dir[i][0], c + dir[i][1]);
        dp[r][c][K] = rate;
        return rate;
    }

    /**
     * 讨论区另有bottom up做法，dp[r][c]代表rc位置在棋盘上一共有多少legal走法，最后除以8^K。没有top down intuitive。
     */
    int[][] moves = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

    public double knightProbability___(int N, int K, int r, int c) {
        int len = N;
        double dp0[][] = new double[len][len];
        for (double[] row : dp0) Arrays.fill(row, 1);
        for (int l = 0; l < K; l++) {
            double[][] dp1 = new double[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int[] move : moves) {
                        int row = i + move[0];
                        int col = j + move[1];
                        if (isLegal(row, col, len)) dp1[i][j] += dp0[row][col];
                    }
                }
            }
            dp0 = dp1;
        }
        return dp0[r][c] / Math.pow(8, K);
    }

    private boolean isLegal(int r, int c, int len) {
        return r >= 0 && r < len && c >= 0 && c < len;
    }
}
