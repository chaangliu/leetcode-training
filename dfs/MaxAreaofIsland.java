package dfs;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * <p>
 * Example 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 * <p>
 * 20190317
 */
public class MaxAreaofIsland {

    /**
     * 这题跟number of island 那题几乎一样，用floodFill。
     * 不过在dfs的时候我思考了五分钟如何计算面积。
     * <p>
     * 同样，这题bfs也可以。
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int[] count = new int[1];
                if (grid[i][j] == 1) res = Math.max(res, floodFill(grid, i, j, count));
            }
        }
        return res;
    }

    /**
     * dfs的过程，这里在进入下层递归之前就检查了合法，也可以在开头统一判断是否越界
     */
    private int floodFill(int[][] grid, int i, int j, int[] count) {
        count[0] += 1;
        grid[i][j] = 999;
        if (i - 1 >= 0 && grid[i - 1][j] == 1) floodFill(grid, i - 1, j, count);
        if (i + 1 < grid.length && grid[i + 1][j] == 1) floodFill(grid, i + 1, j, count);
        if (j - 1 >= 0 && grid[i][j - 1] == 1) floodFill(grid, i, j - 1, count);
        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) floodFill(grid, i, j + 1, count);
        return count[0];
    }
}
