package dfs;

/**
 * Created by DrunkPiano on 2017/4/16.
 */

public class NQueensII {

    /**
     * 题意：求n皇后的解法有多少个。
     * 解法：跟1一样backtrack。
     */
    int num = 0;

    public int totalNQueens(int n) {
        int[] col = new int[n];
        dfsHelper(n, col, 0);
        return num;
    }

    private void dfsHelper(int n, int[] coord, int row) {
        if (row == n) {
            num++;
            return;
        }
        for (int col = 0; col < n; col++) {
            coord[row] = col; // 代表在第row行的第col列放置皇后
            if (checkValid(row, coord)) {
                dfsHelper(n, coord, row + 1);
            }
        }
    }

    private boolean checkValid(int row, int[] col) {
        for (int i = 0; i < row; i++)
            //  斜线 || 同一列
            if (Math.abs(i - row) == Math.abs(col[i] - col[row]) || col[i] == col[row]) {
                return false;
            }
        return true;
    }
}
