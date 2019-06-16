package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * <p>
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 * <p>
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Note:
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 * 20190616 contest#3
 */
public class ShortestPathInBinaryMatrix {
    /**
     * contest第三题，看到了「最短」就想到bfs，同样是8个方向的dfs，技巧也是用2标记visited；粗心debug了一会儿，最后AC时12:03了
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int level = 1;
        if (grid[0][0] == 1) return -1;//忘记处理corner case
        int N = grid.length;
        q.offer(new int[]{0, 0});
        int dir[][] = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}};
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] coord = q.poll();
                int x = coord[0], y = coord[1];
                if (x == grid.length - 1 && y == grid[0].length - 1) return level;
                for (int[] d : dir) {
                    int nx = x + d[0], ny = y + d[1];//y写成x了 debug好久
                    if (nx >= N || nx < 0 || ny >= N || ny < 0 || grid[nx][ny] == 2 || grid[nx][ny] == 1) continue;
                    grid[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
            level++;
        }
        return -1;
    }
}
