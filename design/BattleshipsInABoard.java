package design;

/**
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * Example:
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 * This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 * Follow up:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 * <p>
 * 20190514
 */
public class BattleshipsInABoard {

    /**
     * Approach 1. dfs floodFill，跟number of islands那题一样。大家都能想到。
     */
    private int mRow;
    private int mCol;
    private char[][] mGrid;

    public int countBattleships(char[][] board) {
        mRow = board.length;
        if (mRow == 0) return 0;
        mCol = board[0].length;
        mGrid = board;
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

        if (i >= 0 && i < mRow && j >= 0 && j < mCol && mGrid[i][j] == 'X') {
            mGrid[i][j] = '4';
            dfs(i + 1, j);
            dfs(i - 1, j);
            dfs(i, j + 1);
            dfs(i, j - 1);
            return true;
        } else return false;
    }

    /**
     * Approach2
     * 遍历一遍所有节点，如果左边和上边没有X，就+1。比较巧妙，不过不适用于number of islands那题。
     */
    public int countBattleships__ON(char[][] board) {
        int m = board.length;
        if (m == 0) return 0;
        int n = board[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i - 1][j] == 'X') continue;
                if (j > 0 && board[i][j - 1] == 'X') continue;
                count++;
            }
        }

        return count;
    }
}
