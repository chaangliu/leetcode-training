package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * NQUEENS
 * Created by DrunkPiano on 2017/3/4.
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */

public class NQueens {
    /**
     * 题意：打印出N皇后的所有解法。
     * 解法：backtrack，技巧是用一个一维coord[]数组表示合适的坐标
     * 20190228 review
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(res, 0, n, new int[n]);
        return res;
    }

    private void dfs(List<List<String>> res, int row, int N, int[] coord) {
        if (row >= N) { // 找到了一个解
            addAnswer(res, coord);
        } else {
            // 在第row行的每个位置尝试插入Q
            for (int col = 0; col < N; col++) {
                coord[row] = col;// 代表在第row行的第col列放置皇后
                if (checkValid(row, coord)) {
                    dfs(res, row + 1, N, coord);
                }
            }
        }
    }

    private void addAnswer(List<List<String>> res, int[] coord) {
        List<String> item = new ArrayList<>();
        for (int i = 0; i < coord.length; i++) {
            int x = coord[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < coord.length; j++) {
                sb.append(j == x ? "Q" : ".");
            }
            item.add(sb.toString());
        }
        res.add(item);
    }

    // 判断刚插入的[row, coord[row]]这个坐标是否合法
    public boolean checkValid(int row, int[] coord) {
        for (int i = 0; i < row; i++) {
            // 同一列 || 斜线
            if (coord[i] == coord[row] || Math.abs(i - row) == Math.abs(coord[i] - coord[row])) return false;
        }
        return true;
    }


    /**
     * original post
     * <p>
     * public List<List<String>> solveNQueens(int n) {
     * <p>
     * List<List<String>> res = new ArrayList<>();
     * if (n <= 0)
     * return res;
     * <p>
     * dfs(res, 0, n, new int[n]);
     * return res;
     * }
     * <p>
     * public boolean dfs(List<List<String>> result, int row, int n, int[] col) {
     * if (row == n) {
     * List<String> cell = new ArrayList<>();
     * //打印一个解
     * for (int i = 0; i < row; i++) {
     * StringBuilder sb = new StringBuilder();
     * for (int j = 0; j < row; j++) {
     * if (col[i] == j) {
     * sb.append("Q");
     * } else {
     * sb.append(".");
     * }
     * }
     * cell.add(sb.toString());
     * }
     * result.add(new ArrayList<String>(cell));
     * return true;
     * }
     * for (int i = 0; i < n; i++) {
     * col[row] = i;
     * if (checkValid(row, col)) {
     * if (dfs(result, row + 1, n, col))
     * return true;
     * }
     * }
     * return false;
     * }
     * <p>
     * public boolean checkValid(int row, int[] col) {
     * for (int i = 0; i < row; i++) {
     * if (col[i] == col[row] || Math.abs(i - row) == Math.abs(col[i] - col[row]))//同一列 || 斜线
     * {
     * return false;
     * }
     * }
     * return true;
     * }
     **/

    public static void main(String args[]) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.solveNQueens(4));
    }
}
