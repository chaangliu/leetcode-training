package dfs;

import java.util.PriorityQueue;

/**
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
 * Example 1:
 * Input: [[5,4,5],[1,2,6],[7,4,6]]
 * Output: 4
 * Explanation:
 * The path with the maximum score is highlighted in yellow.
 * Example 2:
 * Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * Output: 2
 * Example 3:
 * Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 * Output: 3
 * Note:
 * 1 <= R, C <= 100
 * 0 <= A[i][j] <= 10^9
 * 2019/07/02 from biweekly contest
 */
public class PathWithMaximumMinimumValue {
    /**
     * 这题是找一条从左上角到右下角的一条「Value」最大的路径，value的定义是这条路径上的最小值。
     * 普通的DFS解法(包含backtracking状态回归)会TLE。
     * 这里参考了这个人的python解法：https://blog.csdn.net/qq_17550379/article/details/94393346
     * 利用最大堆+dfs，这样能保证第一次到达右下角就是最优解。
     * 举例子，对于[[5,4,5],[1,2,1],[7,4,6]]，
     * 走到4后，堆中有5，2；poll 5，再次会poll 2，不走1。类似贪心。
     * <p>
     * 另外这题还可以用二分，cui神直播用了。
     */
    int r = 0, c = 0, res = 0;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] A) {
        r = A.length;
        c = A[0].length;

        boolean[][] visited = new boolean[r][c];
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o2[0] - o1[0]));
        queue.offer(new int[]{A[0][0], 0, 0});
        dfs(queue, A, visited);
        return res;
    }

    private void dfs(PriorityQueue<int[]> queue, int[][] A, boolean[][] visited) {
        int[] cell = queue.poll();
        int pre = cell[0], x = cell[1], y = cell[2];
        if (x == A.length - 1 && y == A[0].length - 1) {
            res = Math.max(res, pre);//在出口处比较一次大小
            System.out.println("res is " + res);
            return;
        }
        for (int[] dir : dirs) {
            int nx = dir[0] + x, ny = dir[1] + y;
            if (nx < 0 || nx >= A.length || ny < 0 || ny >= A[0].length || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            System.out.println("[visiting] " + x + ", " + y);
            queue.offer(new int[]{Math.min(pre, A[nx][ny]), nx, ny});
        }
        dfs(queue, A, visited);
    }
}
