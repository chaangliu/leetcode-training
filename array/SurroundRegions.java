package array;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * Created by DrunkPiano on 2017/4/21.
 * 20200202 --review
 */

public class SurroundRegions {
    /**
     * 题意：把2D棋盘上的被X围住的O都标记成X。
     * 思路：从border是O的cell开始DFS或BFS，标记成T，然后把所有O标记成X，最后把T变回O。
     **/
    public void solve(char[][] A) {
        if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) return;
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            int j = 0, k = n - 1;
            if (A[i][j] == 'O') floodFill(i, j, A);
            if (A[i][k] == 'O') floodFill(i, k, A);
        }
        for (int j = 1; j < n - 1; j++) {
            int i = 0, k = m - 1;
            if (A[i][j] == 'O') floodFill(i, j, A);
            if (A[k][j] == 'O') floodFill(k, j, A);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (A[i][j] == 'O') A[i][j] = 'X';
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (A[i][j] == 'T') A[i][j] = 'O';
    }

    private void floodFill(int i, int j, char[][] A) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        A[i][j] = 'T';//已犯错误 这行写在了确定x,y之后，那样就谁能改变当前的格子了
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1], m = A.length, n = A[0].length;
            if (x < 0 || x >= m || y < 0 || y >= n || A[x][y] != 'O') continue;
            floodFill(x, y, A);
        }
    }
}


