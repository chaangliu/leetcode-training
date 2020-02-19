package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * <p>
 * Created by DrunkPiano on 2017/4/5.
 */

public class NumberOfIslands {
    /**
     * 题意：二维数组包含1，0；1是陆地0是水域。可以假设数组之外都是水。问岛屿的数量有多少。
     * 解法：DFS或者BFS。
     */
    private int mRow;
    private int mCol;
    private char[][] mGrid;

    public int numIslands(char[][] grid) {
        mRow = grid.length;
        if (mRow == 0) return 0;
        mCol = grid[0].length;
        mGrid = grid;
        int count = 0;
        for (int i = 0; i < mRow; i++)
            for (int j = 0; j < mCol; j++) {
                if (dfs(i, j)) {
                    count++;
                }
            }
        return count;
    }

    private boolean dfs(int i, int j) {

        if (i >= 0 && i < mRow && j >= 0 && j < mCol && mGrid[i][j] == '1') {
            mGrid[i][j] = '4';
            dfs(i + 1, j);
            dfs(i - 1, j);
            dfs(i, j + 1);
            dfs(i, j - 1);
            return true;
        } else return false;
        //为什么不需要恢复现场
    }

    /**
     * bfs solution, 20190213
     * 这题我一开始想不到bfs为什么要用到queue，但是后来仔细想了想bfs的实施，它循环起来的条件就应该是queue不为空，这样才能一圈一圈地向外扩散；所以说BFS跟queue是绑定的
     */
    class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int res = 0;
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    queue.add(new Pair(i, j));
                    bfs(grid, queue);
                    res++;
                }
            }
        return res;
    }

    private void bfs(char[][] grid, Queue<Pair> queue) {
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (pair.i - 1 >= 0 && grid[pair.i - 1][pair.j] == '1') {
                queue.add(new Pair(pair.i - 1, pair.j));
                grid[pair.i - 1][pair.j] = '0';
            }
            if (pair.i + 1 < grid.length && grid[pair.i + 1][pair.j] == '1') {
                queue.add(new Pair(pair.i + 1, pair.j));
                grid[pair.i + 1][pair.j] = '0';
            }
            if (pair.j - 1 >= 0 && grid[pair.i][pair.j - 1] == '1') {
                queue.add(new Pair(pair.i, pair.j - 1));
                grid[pair.i][pair.j - 1] = '0';
            }
            if (pair.j + 1 < grid[0].length && grid[pair.i][pair.j + 1] == '1') {
                queue.add(new Pair(pair.i, pair.j + 1));
                grid[pair.i][pair.j + 1] = '0';
            }
        }
    }

    //btw，这题还可以用union find来做


    public static void main(String args[]) {
        char[][] nums = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'}};
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println(numberOfIslands.numIslands(nums));
    }
}
