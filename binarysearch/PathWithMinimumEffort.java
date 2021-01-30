package binarysearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * 示例 1：
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 */
public class PathWithMinimumEffort {
    /**
     * 题意：一个二维数组，每个格子上的数字代表高度，让你从左上角走到右下角，使得这个过程中经过的最大高度差绝对值最小。求这个最小的高度绝对值。
     * 解法：枚举高度，binary search + bfs。有点像分巧克力那题。
     * same as 1631. Path With Minimum Effort 也可以用Dijkstra或并查集。
     */
    public int minimumEffortPath(int[][] heights) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = 1000000, res = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] visited = new boolean[m * n]; // x * n + y就可以定位一个格子
            visited[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx * n + ny] = true;
                    }
                }
            }
            if (visited[m * n - 1]) { // 能到达右下角
                res = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
