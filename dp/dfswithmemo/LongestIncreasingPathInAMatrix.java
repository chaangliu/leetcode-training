package dp.dfswithmemo;


/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Created by DrunkPiano on 14/06/2017.
 */

public class LongestIncreasingPathInAMatrix {
    /**
     * 题意：在matrix中找出最长的递增路径。
     * 解法：明显是DFS + MEMO，我纠结的点是要不要加visited数组，事实证明不用加，因为这题如果反向行走，必然会终止，因为不可能两个方向都上升。
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        Integer[][] memo = new Integer[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, memo, i, j));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, Integer[][] memo, int i, int j) {
        if (memo[i][j] != null) return memo[i][j];
        // int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}}; // 这个放到函数外面定义，可以节省不少时间（11ms -> 8ms）
        int m = matrix.length, n = matrix[0].length;
        int res = 1;
        for (int[] d : dirs) {
            int x = d[0] + i, y = d[1] + j;
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            //visited[x][y] = true;
            res = Math.max(res, matrix[i][j] < matrix[x][y] ? 1 + dfs(matrix, memo, x, y) : 1);
            //visited[x][y] = false;
        }
        memo[i][j] = res;
        return res;
    }


    /**
     * leetcode上的代码
     */
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath_(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}
