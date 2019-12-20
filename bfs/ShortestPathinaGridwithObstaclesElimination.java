package bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 * Example 1:
 * Input:
 * grid =
 * [[0,0,0],
 * [1,1,0],
 * [0,0,0],
 * [0,1,1],
 * [0,0,0]],
 * k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * Example 2:
 * Input:
 * grid =
 * [[0,1,1],
 * [1,1,1],
 * [1,0,0]],
 * k = 1
 * Output: -1
 * Explanation:
 * We need to eliminate at least two obstacles to find such a walk.
 * Constraints:
 * grid.length == m
 * grid[0].length == n
 * 1 <= m, n <= 40
 * 1 <= k <= m*n
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m-1][n-1] == 0
 * 20191220
 */
public class ShortestPathinaGridwithObstaclesElimination {
    /**
     * 题意：一个二维数组，从左下角走到右下角；1代表obstacle，最多踏过k个obstacles。问最短需要走多少步。
     * 解法：BFS; 一开始以为是DP，没想到BFS也能是有状态的；这题是去尝试走上每个格子，如果是1，那么cost++，如果cost仍满足就进入下一层BFS。
     */
    public int shortestPath(int[][] grid, int k) {
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});//x, y, current-cost
        int res = 0;
        int m = grid.length, n = grid[0].length;
        HashMap<Integer, Integer> minCost = new HashMap<>();
        boolean[][][] visited = new boolean[m][n][k + 1];//有状态的坐标，用一个额外的维度表示当前cost
        minCost.put(0, 0);
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; sz--) {
                int[] cell = q.poll();
                int x = cell[0], y = cell[1], cost = cell[2];
                if (x == m - 1 && y == n - 1) return res;
                for (int[] d : dirs) {
                    int c = cost;
                    int newX = x + d[0], newY = y + d[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                        if (grid[newX][newY] == 1) c++;
                        if (c <= k) {
                            if (visited[newX][newY][c]) continue;
                            q.offer(new int[]{newX, newY, c});
                            visited[newX][newY][c] = true;
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }

    /**
     * 优化：上面的做法有可能走回头路，queue会很大；很容易想到，我们走到每个坐标的时候如果cost已经>=以前来过这个坐标时的cost，那就不用加入下一层BFS了。
     * 试了一下，在矩阵规模稍大的情况下，这个优化能把3000多次的poll()减少到750次。
     */
    public int shortestPath_(int[][] grid, int k) {
        int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});//x, y, current-cost
        int res = 0;
        int m = grid.length, n = grid[0].length;
        //if(k>= m + n - 2) return m + n - 2;
        HashMap<Integer, Integer> minCost = new HashMap<>();
        boolean[][][] visited = new boolean[m][n][k + 1];
        minCost.put(0, 0);
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; sz--) {
                int[] cell = q.poll();
                int x = cell[0], y = cell[1], cost = cell[2];
                if (x == m - 1 && y == n - 1) return res;
                for (int[] d : dirs) {
                    int c = cost;
                    int newX = x + d[0], newY = y + d[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                        if (grid[newX][newY] == 1) c++;
                        if (c <= k) {
                            if (c < minCost.getOrDefault(newX * 100 + newY, Integer.MAX_VALUE)) {//已犯错误：写成了<=，这样可能会无限循环，比如全是0的数组
                                q.offer(new int[]{newX, newY, c});
                                minCost.put(newX * 100 + newY, c);
                            }
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
