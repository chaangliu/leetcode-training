package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Example 2:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 * <p>
 * 20190227
 */
public class _01Matrix {
    /**
     * 题意：找出二维数组中每个0到1最近的距离。
     * 解法：BFS
     */
    //approach1: bfs for every cell, 不能用multi-end bfs
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;
        int row = matrix.length, col = matrix[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0) bfs(i, j, matrix, res);
            }
        return res;
    }

    private void bfs(int i, int j, int[][] matrix, int[][] res) {
        int row = matrix.length, col = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int dist = 0;
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            dist++;//new layer
            int size = queue.size();//已犯错误，这里size一定要提前定义，不能放到for语句中
            for (int s = 0; s < size; s++) {//for
                int[] cord = queue.poll();
                int x = cord[0], y = cord[1];
                if (x - 1 >= 0 && matrix[x - 1][y] == 0 || x + 1 < row && matrix[x + 1][y] == 0 || y - 1 >= 0 && matrix[x][y - 1] == 0 || y + 1 < col && matrix[x][y + 1] == 0) {
                    res[i][j] = dist;
                    return;//已犯错误，忘记及时return
                } else {
                    if (x - 1 >= 0) queue.offer(new int[]{x - 1, y});
                    if (y - 1 >= 0) queue.offer(new int[]{x, y - 1});
                    if (x + 1 < row) queue.offer(new int[]{x + 1, y});
                    if (y + 1 < col) queue.offer(new int[]{x, y + 1});
                }
            }
        }
    }

    //approach2：可用DP
}
