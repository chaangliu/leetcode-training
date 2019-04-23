package dfs;

import android.widget.HeterogeneousExpandableList;

import java.util.Arrays;

/**
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 * Example 1:
 * <p>
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * <p>
 * Example 2:
 * <p>
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 */
public class OutOfBoundaryPaths {

    //WRONG ANSWER
//    int res = 0;
//
//    public int findPaths___INTUITIVE(int m, int n, int N, int i, int j) {
//        dfs(new boolean[m][n], m, n, N, i, j);
//        return res;
//    }
//
//    private void dfs(boolean visited[][], int m, int n, int N, int i, int j) {
//        if (N < 0) return;
//        if (i < 0 || i >= m || j < 0 || j >= n) {
//            res++;
//            return;
//        }
//        if (visited[i][j]) return;
//        visited[i][j] = true;
//        dfs(visited, m, n, N - 1, i + 1, j);
//        dfs(visited, m, n, N - 1, i - 1, j);
//        dfs(visited, m, n, N - 1, i, j + 1);
//        dfs(visited, m, n, N - 1, i, j - 1);
//        visited[i][j] = false;
//    }

    /**
     * approach1. brute force（思路对但是TLE）
     * 这题的brute force我看它没有用visited[][]很奇怪，我以往认为这种四个方向的dfs必须要搭配visited不然会产生死循环；事实上并不是这样，不加visited只会产生很多的重复路径而已，进而很可能产生stackOverFlow；
     * 而这题用二维坐标表示Visited不行，因为允许走重复的线路。怎么办？我之前想到用set存储完整路径的string，但发现实施困难（而且可能会遇到不同string的hash相同的情况？）
     */

    public int findPath__TTLE(int m, int n, int N, int i, int j) {
        if (i == m || j == n || i < 0 || j < 0)
            return 1;
        if (N == 0)
            return 0;
        return findPath__TTLE(m, n, N - 1, i - 1, j)
                + findPath__TTLE(m, n, N - 1, i + 1, j)
                + findPath__TTLE(m, n, N - 1, i, j - 1)
                + findPath__TTLE(m, n, N - 1, i, j + 1);
    }

    /**
     * approach1 with cache
     * 对于每个格子，记录从那个格子开始走有多少种答案，以后来到那个路口就不需要再走下去了，跟DP一样
     * 这里用了三维数组，第三维表示路径。
     * <p>
     * 这里真正写起来的时候我发现memo[i][j][N] = helper + .... 以及最后一行return memo[i][j][N]很巧妙。另外这种写法的mod很折磨人，必须前两个mod后两个mod最后再mod不然WA。
     */

    int mod = (int) (1e9 + 7);

    public int findPaths(int m, int n, int N, int i, int j) {
        //第三个参数长度是N + 1,因为对于每个格子来说，剩余的步数不同，结果也不一样
        int[][][] memo = new int[m][n][N + 1];
        for (int[][] grid : memo)
            for (int[] row : grid)
                Arrays.fill(row, -1);
        return helper(m, n, N, i, j, memo);
    }

    private int helper(int m, int n, int N, int i, int j, int[][][] memo) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;
        if (memo[i][j][N] != -1) return memo[i][j][N];
        if (N == 0) return 0;
        memo[i][j][N] = ((helper(m, n, N - 1, i - 1, j, memo) + helper(m, n, N - 1, i + 1, j, memo)) % mod
                + (helper(m, n, N - 1, i, j - 1, memo) + helper(m, n, N - 1, i, j + 1, memo)) % mod) % mod;
        return memo[i][j][N];
    }

    public static void main(String args[]) {
        System.out.println(new OutOfBoundaryPaths().findPaths(2, 2, 2, 0, 0));
    }
}
