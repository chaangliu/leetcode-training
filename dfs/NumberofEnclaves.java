package dfs;

/**
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 * <p>
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 * <p>
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 * Example 2:
 * <p>
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 * <p>
 * 20190331
 */
public class NumberofEnclaves {

    /**
     * 这题dfs bfs都能做，这里用dfs(floodfill)，但是做题的时候一开始忘记了visited[][]数组，导致dfs无法进行；后来改进才AC
     */
    public int numEnclaves(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i == 0 || i == row - 1 || j == 0 || j == col - 1) && A[i][j] == 1) {
                    dfs(i, j, A, row, col, new boolean[row][col]);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (A[i][j] == 1) res++;
            }
        }
        return res;
    }

    private void dfs(int i, int j, int[][] board, int row, int col, boolean[][] visited) {
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] || board[i][j] != 1) return;
        visited[i][j] = true;
        board[i][j] = 2;
        dfs(i + 1, j, board, row, col, visited);
        dfs(i - 1, j, board, row, col, visited);
        dfs(i, j + 1, board, row, col, visited);
        dfs(i, j - 1, board, row, col, visited);
    }
}
