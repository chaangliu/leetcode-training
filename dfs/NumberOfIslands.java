package dfs;

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

    public static void main(String args[]) {
        char[][] nums = {{'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '0'}};
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        numberOfIslands.numIslands(nums);
    }


}
