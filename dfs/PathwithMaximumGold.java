package dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * Return the maximum amount of gold you can collect under the conditions:
 * -Every time you are located in a cell you will collect all the gold in that cell.
 * -From your position you can walk one step to the left, right, up or down.
 * -You can't visit the same cell more than once.
 * -Never visit a cell with 0 gold.
 * -You can start and stop collecting gold from any position in the grid that has some gold.
 * Example 1:
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * Example 2:
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 * Constraints:
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 * 20191006
 */
public class PathwithMaximumGold {
    /**
     * 题意：一个二维平面上有个旷工要挖矿，不能去价值为0的矿坑中，并且不能走已经走过的路。可以从任意起点开始，问最多收益。
     * 这题数据范围很小，就用dfs搜索了一下，竟然过了。顺便复习了一下flood fill跟这个的区别。floodfill是涂色用，这个是计算路径sum用。
     */
    int res = 0;

    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0)
                    dfs(grid, i, j, new boolean[grid.length][grid[0].length], 0);
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visited, int cur) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == 0 || visited[i][j]) {
            res = Math.max(res, cur);
            return;
        }
        visited[i][j] = true;
        dfs(grid, i + 1, j, visited, cur + grid[i][j]);
        dfs(grid, i - 1, j, visited, cur + grid[i][j]);
        dfs(grid, i, j + 1, visited, cur + grid[i][j]);
        dfs(grid, i, j - 1, visited, cur + grid[i][j]);
        visited[i][j] = false;
    }

    /**
     * 看了下答案，这题可以不用额外空间的，在原来数组上修改就好了，backtrack的时候改回来。
     * https://leetcode.com/problems/path-with-maximum-gold/discuss/398282/Java-DFS-w-comment-brief-explanation-and-analysis.
     */
    private static final int[] d = {0, 1, 0, -1, 0};

    public int getMaximumGold_(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                ans = Math.max(ans, dfs(grid, i, j, 0));
            }
        }
        return ans;
    }

    private int dfs(int[][] g, int i, int j, int sum) {
        if (i < 0 || i >= g.length || j < 0 || j >= g[0].length || g[i][j] == 0 || g[i][j] > 100) return sum;
        sum += g[i][j];
        g[i][j] += 1000; // mark visited.
        int mx = 0;
        for (int k = 0; k < 4; ++k) { // traverse 4 neighbors to get max value.
            mx = Math.max(mx, dfs(g, i + d[k], j + d[k + 1], sum));
        }
        g[i][j] -= 1000; // change back.
        return mx;
    }


    /**
     * 另一个技巧，用i * n + j 标记visited，空间比二维数组小
     */
    public int getMaximumGold__(int[][] grid) {
        int ans = 0, n = grid[0].length;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(grid, i, j, n, 0, new HashSet<Integer>()));
            }
        }
        return ans;
    }

    private int dfs(int[][] g, int i, int j, int n, int sum, Set<Integer> seen) {
        if (i < 0 || i >= g.length || j < 0 || j >= n || g[i][j] == 0) return sum;
        if (!seen.add(i * n + j)) return sum; // mark (i, j) visited.
        sum += g[i][j];
        int mx = 0;
        for (int k = 0; k < 4; ++k) {
            mx = Math.max(mx, dfs(g, i + d[k], j + d[k + 1], n, sum, seen));
        }
        seen.remove(i * n + j); // reset to unvisited.
        return mx;
    }
}
