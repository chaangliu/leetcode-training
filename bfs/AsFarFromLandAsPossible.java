package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 * <p>
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * <p>
 * If no land or water exists in the grid, return -1.
 * Example 1:
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation:
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 * Example 2:
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation:
 * The cell (2, 2) is as far as possible from all the land with distance 4.
 * Note:
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 * 20190818
 */
public class AsFarFromLandAsPossible {
    /**
     * 这题是求距离0到1最远的岛屿的曼哈顿距离，其实一眼就能看出就是求bfs层数；
     * 但是我一开始思路是，从每个0出发做bfs，遇到1就返回，维护距离最大值。
     * 会不会超时呢，就想如果超时的话，加一个cache应该就行？
     * cache就是在bfs过程中，走到第n层发现某个格子到陆地的最大距离计算过=m，结果就是n + m。这么做似乎没错(我也第一次这么操作..)，但是仍然会超时
     * 看答案发现思路错了，bfs是先把1都加进去，然后对0进行涂色，看能涂几轮，就是要求的结果，这样只要bfs一次就行了。没想到呀！
     */
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});//这题的关键，一开始把1都放进去，找0。一轮bfs就能完事
                    visited[i][j] = true;
                }
            }
        }
        int level = -1;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pair = queue.poll();
                int x = pair[0], y = pair[1];
                for (int[] d : dir) {
                    int newX = x + d[0], newY = y + d[1];
                    //最大bfs层数就是0到1的远距离
                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !visited[newX][newY] && grid[newX][newY] == 0) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            level++;
        }
        return level <= 0 ? -1 : level;
    }
}
