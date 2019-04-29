package dfs;

/**
 * Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that location.
 * <p>
 * Two squares belong to the same connected component if and only if they have the same color and are next to each other in any of the 4 directions.
 * <p>
 * The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
 * <p>
 * Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that square with the given color, and return the final grid.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
 * Output: [[3, 3], [3, 2]]
 * Example 2:
 * <p>
 * Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
 * Output: [[1, 3, 3], [2, 3, 3]]
 * Example 3:
 * <p>
 * Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
 * Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length <= 50
 * 1 <= grid[0].length <= 50
 * 1 <= grid[i][j] <= 1000
 * 0 <= r0 < grid.length
 * 0 <= c0 < grid[0].length
 * 1 <= color <= 1000
 * <p>
 * 20190429
 */
public class ColoringABorder {
    /**
     * 这题题干很难读懂，意思其实是把连通分量的边缘涂成指定颜色，中间保留原来颜色
     * <p>
     * 用DFS或BFS
     */
    void dfs(int[][] g, int r, int c, int cl) {
        if (r < 0 || c < 0 || r >= g.length || c >= g[r].length || g[r][c] != cl) return;
        //先把所有指定颜色都flip成它的负值
        g[r][c] = -cl;
        dfs(g, r - 1, c, cl);
        dfs(g, r + 1, c, cl);
        dfs(g, r, c - 1, cl);
        dfs(g, r, c + 1, cl);
        //再把不在border上的cell flip回正值
        if (r > 0 && r < g.length - 1 && c > 0 && c < g[r].length - 1
                && cl == Math.abs(g[r - 1][c]) && cl == Math.abs(g[r + 1][c]) && cl == Math.abs(g[r][c - 1]) && cl == Math.abs(g[r][c + 1]))
            g[r][c] = cl;
    }

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        dfs(grid, r0, c0, grid[r0][c0]);
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j) grid[i][j] = grid[i][j] < 0 ? color : grid[i][j];
        return grid;
    }
}
