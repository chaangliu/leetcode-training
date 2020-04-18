package dfs;

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 * Example 1:
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 * 0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * Note:
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 * 20200417
 */
public class SwiminRisingWater {
    /**
     * 题意：给你一个二维矩阵，里面有一些平台，你需要从左上角也游到右下角，游泳不耗时间，但是必须水位同时淹没四周，才能游向四周。问最早什么时候能游到右下。
     * 解法：我一看就觉得应该用binary search + dfs(也可以bfs)，中途发现TLE，尝试加memo发现不好加；然后看discuss发现去掉恢复现场就能AC，很神奇。
     * 我一开始也不理解为什么不需要恢复现场，也打出了以下的发问，但是我发现我举得这个例子并不能充当反例；因为当你走到[4,0]的时候，你回到[3,0]，就可以往右走了，而不是把整条路径都标记为visited然后退出。
     * 就像评论区有个老哥说的，你只需要找到一条路径就行，不需要找最好的那条，不能去的那个格子在以后所有时候都不能去了，所以不需要恢复现场。brilliant!!!
     * still can't get it.. what if there is a valid cell in an invalid path, which could have been used in another valid path, but you cannot visit it because it has been marked has visited?
     * e.g.
     * [1 1 9]
     * [1 1 9]
     * [1 9 9]
     * [1 1 1]
     * [9 1 1]
     * at time 1, if you go downwards until reaching the bottom 9 in the first column, then the first column will be marked as visited, and you cannot visited the first column anymore at time 1
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length, lo = 0, hi = n * n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (dfs(grid, 0, 0, new boolean[n][n], mid)) {
                hi = mid;
            } else lo = mid + 1;
        }
        return lo;
    }

    private boolean dfs(int[][] grid, int i, int j, boolean[][] visited, int t) {
        int n = grid.length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        if (grid[i][j] > t) return false; // 必须要同时淹没这两个平台
        if (i == n - 1 && j == n - 1) return true;
        visited[i][j] = true;
        for (int[] d : dirs) {
            int ni = i + d[0], nj = j + d[1];
            if (ni < 0 || ni >= n || nj < 0 || nj >= n || visited[ni][nj] || grid[ni][nj] > t) continue;
            if (dfs(grid, ni, nj, visited, t)) return true;
        }
        // visited[i][j] = false; // 注意这里不需要回复现场了！不然会TLE。no need to go back, because this cell will always be invalid afterwards
        return false;
    }
}
