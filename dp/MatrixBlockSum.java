package dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 * Example 1:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * Example 2:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 * 20200112
 */
public class MatrixBlockSum {
    /**
     * 题意：给你一个matrix，对于每个坐标，把周围K距离的数字加起来存放在当前格子里。
     * 一开始用了BFS，然后超时了。时间复杂度O(m * n * K^2)
     * 然后写了个prefix sum，不熟悉，花了很久，复杂度O(m * n * K)。
     * 看讨论区，其实跟304题一模一样，面积的prefix sum；可以直接计算正方形面积的，昨天我也想到了，但是没敢写。复杂度O(m * n)。
     * 下面是我的答案，不太好。后面贴上rock的答案。
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                mat[i][j] = mat[i - 1][j] + mat[i][j];
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] topLeft = new int[2], bottomRight = new int[2];
                topLeft[0] = Math.max(0, i - K);
                topLeft[1] = Math.max(0, j - K);
                bottomRight[0] = Math.min(m - 1, i + K);
                bottomRight[1] = Math.min(n - 1, j + K);
                int sum = 0;
                for (int k = topLeft[1]; k <= bottomRight[1]; k++) {
                    sum += mat[bottomRight[0]][k] - (topLeft[0] == 0 ? 0 : mat[topLeft[0] - 1][k]);
                }
                res[i][j] = sum;
            }
        }
        return res;
    }


    /**
     * rock的答案，O(m * n)
     * If you not familiar with range/prefix sum, please refer to:
     * 304. Range Sum Query 2D - Immutable
     * 307. Range Sum Query - Mutable
     * 308. Range Sum Query 2D - Mutable: Premium
     */
    public int[][] matrixBlockSum_(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] rangeSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + mat[i][j];
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                int r1 = Math.max(0, i - K), c1 = Math.max(0, j - K), r2 = Math.min(m, i + K + 1), c2 = Math.min(n, j + K + 1);
                ans[i][j] = rangeSum[r2][c2] + rangeSum[r1][c1] - rangeSum[r2][c1] - rangeSum[r1][c2];
            }
        return ans;
    }

    /**
     * BFS: TLE
     */
    public int[][] matrixBlockSum_bfs(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                int k = K, sum = mat[i][j];
                HashSet<Integer> visited = new HashSet<>();
                visited.add(1000 * i + j);
                while (!q.isEmpty() && k-- > 0) {
                    for (int sz = q.size(); sz > 0; sz--) {
                        int[] node = q.poll();
                        for (int[] d : dir) {
                            int x = node[0] + d[0], y = node[1] + d[1];
                            if (x < 0 || x >= m || y < 0 || y >= n || visited.contains(1000 * x + y)) continue;
                            visited.add(1000 * x + y);
                            q.offer(new int[]{x, y});
                            sum += mat[x][y];
                        }
                    }
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}
