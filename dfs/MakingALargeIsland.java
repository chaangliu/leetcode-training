package dfs;

/**
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 * Notes:
 *
 * 1 <= grid.length = grid[0].length <= 50.
 * 0 <= grid[i][j] <= 1.
 * 20200411
 */
public class MakingALargeIsland {
    /**
     * 题意：给你一个二维数组，最多把一个0变成1，问最大的1组成的岛屿是多大。
     * 解法：DFS，从0开始。我的想法差不多，但我我想用floodFill那种涂色，但是发现不知道怎么统计岛屿大小（再遍历一遍是不行的，因为你不知道哪个岛屿最大）
     * 借鉴讨论区的解法，从0开始深搜，每搜一格就在函数里+1（而不是在参数里+1，那样是找的最深递归次数）
     */
    public int largestIsland(int[][] grid) {
        int max = 0, m = grid.length, n = grid[0].length;
        boolean hasZero = false; //To check if there is any zero in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    max = Math.max(max, dfs(i, j, grid, new boolean[m][n]));
                    if (max == m * n) return max;
                    grid[i][j] = 0;
                    hasZero = true;
                }
            }
        }
        return hasZero ? max : m * n;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) return 0;
        visited[i][j] = true;
        return 1 + dfs(i - 1, j, grid, visited) + dfs(i + 1, j, grid, visited) + dfs(i, j + 1, grid, visited) + dfs(i, j - 1, grid, visited);
    }
}
