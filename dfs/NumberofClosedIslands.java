package dfs;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * <p>
 * Return the number of closed islands.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 * Input: grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * Output: 2
 * Constraints:
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 * 20191119
 */
public class NumberofClosedIslands {
    /**
     * 题意：找出完全被水包围的岛屿数量（出了边缘不算）。
     * 周赛第三题，模仿number of islands那题写法做flood fill。
     * 我的解法：
     */
    public int closedIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dfs(grid, i, j)) res++;
                flag = true;
            }
        }
        return res;
    }

    boolean flag = true;//记录当前岛屿是否未触及边界。

    private boolean dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false;
        if (grid[i][j] != 0) return false;
        grid[i][j] = 4;
        if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
            flag = false;
            //return false;//这里不能加return，否则结果会比正常答案多。因为中断了某个方向的DFS。
        }
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return flag;
    }

    /**
     * 讨论区的解法:
     * 1.先用flood fill从边界开始把边界的岛屿变成海洋
     * 2.然后同样用flood fill数剩下的number of islands
     */
    public int closedIsland__(int[][] g) {
        for (int i = 0; i < g.length; ++i)
            for (int j = 0; j < g[i].length; ++j)
                if (i == 0 || j == 0 || i == g.length - 1 || j == g[i].length - 1)
                    fill(g, i, j);
        int res = 0;
        for (int i = 0; i < g.length; ++i)
            for (int j = 0; j < g[i].length; ++j)
                res += fill(g, i, j) > 0 ? 1 : 0;
        return res;
    }

    int fill(int[][] g, int i, int j) {
        if (i < 0 || j < 0 || i >= g.length || j >= g[i].length || g[i][j] != 0)
            return 0;
        return (g[i][j] = 1) + fill(g, i + 1, j) + fill(g, i, j + 1) + fill(g, i - 1, j) + fill(g, i, j - 1);//等同于赋值和1 + ...
    }

    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    /**
     * 讨论区的另一个解法
     */
    public int closedIsland___(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    if (dfss(grid, i, j)) res++;
                }
            }
        }
        return res;
    }

    public boolean dfss(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;
        if (grid[x][y] == 1) return true;
        grid[x][y] = 1;
        boolean res = true;
        for (int[] d : dir) {
            res = res & dfs(grid, x + d[0], y + d[1]);
        }
        return res;
    }
}
