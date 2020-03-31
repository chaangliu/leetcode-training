package dfs.backtracking;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 * <p>
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * Example 1:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 * <p>
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 * <p>
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * Note:
 * <p>
 * 1 <= grid.length * grid[0].length <= 20
 * 20200331
 */
public class UniquePathsIII {
    /**
     * 题意：给你一个grid，1代表起点，2代表终点，0代表能走的，-1代表障碍物，问如果要把所有的0都走一遍，有多少种走法。
     * 解法：dfs，记录走过的0的个数，如果碰到了2, 就判断一下是否0都走过了
     */
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length, zeroCnt = 0, x = 0, y = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) zeroCnt++;
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
            }
        zeros = zeroCnt;
        dfs(grid, zeroCnt, x, y);
        return res;
    }

    int res = 0, zeros = 0;
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private void dfs(int[][] grid, int zeroCnt, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1 || grid[i][j] == 3) return;
        if (grid[i][j] == 1 && zeroCnt != zeros) return; // 已犯错误：这儿如果放到上一行判断，就会造成无法开始dfs，因为初始总是1
        if (grid[i][j] == 2) {
            if (zeroCnt == -1) res++; // 已犯错误：这儿是-1而不是0，考虑[[0,1],[2,-1]]
            return;
        }
        grid[i][j] = 3;
        for (int[] d : dirs) {
            int x = d[0] + i, y = d[1] + j;
            dfs(grid, zeroCnt - 1, x, y);
        }
        grid[i][j] = 0;
    }


    /**
     * lee的代码
     */
    int result = 0, empty = 1, sx, sy, ex, ey;
    public int uniquePathsIII_(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 2) {
                    ex = i;
                    ey = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return result;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0)
            return;
        if (x == ex && y == ey) {
            if (empty == 0) result++;
            return;
        }
        grid[x][y] = -2;
        empty--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        empty++;
    }
}
