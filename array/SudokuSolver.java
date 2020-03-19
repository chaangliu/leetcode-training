package array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * A sudoku puzzle...
 * ...and its solution numbers marked in red.
 * Note:
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 * Created by DrunkPiano on 2017/4/13.
 * 20200106 --review
 */

public class SudokuSolver {

    /**
     * 题意：解决数独问题，把board中代表空白的点填满。
     * 解法：backtracking，技巧是用idx递增，判断valid的时候如果直接用isValidSudoku来解决，简单是简单，但是会TLE。所以我同时用了hashset，但是这样写起来很麻烦，很容易出错。
     * 看了下别人的答案，他们是每次加入一个数之前，先去在原来的board里检查，这样就无需操作hashset了。
     */
    HashSet[] rows = new HashSet[9], cols = new HashSet[9], blocks = new HashSet[9];

    public void solveSudoku(char[][] A) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = A[i][j];
                if (rows[i] == null) rows[i] = new HashSet();
                if (cols[j] == null) cols[j] = new HashSet();
                if (blocks[i / 3 * 3 + j / 3] == null) blocks[i / 3 * 3 + j / 3] = new HashSet();
                if (c != '.') {
                    rows[i].add(c);
                    cols[j].add(c);
                    blocks[i / 3 * 3 + j / 3].add(c);
                }
            }
        }
        dfs(A, 0);
    }

    private boolean dfs(char[][] A, int idx) {
        //if (!isValidSudoku(A)) return false; // 如果直接用isValidSudoku那一题来解决，简单是简单，但是会TLE
        if (idx == 81) return true;
        int row = idx / 9, col = idx % 9, g = row / 3 * 3 + col / 3;
        if (A[row][col] != '.') {
            if (dfs(A, idx + 1)) return true;
        } else
            for (int i = 1; i <= 9; i++) {
                char newChar = (char) (i + '0');
                if (rows[row].contains(newChar) || cols[col].contains(newChar) || blocks[g].contains(newChar)) continue;
                A[row][col] = newChar;
                rows[row].add(newChar);
                cols[col].add(newChar);
                blocks[g].add(newChar);
                if (dfs(A, idx + 1)) return true;
                A[row][col] = '.';
                rows[row].remove(newChar);
                cols[col].remove(newChar);
                blocks[g].remove(newChar);
            }
        return false;
    }


    /**
     * 摘抄一个答案，我看了一圈，觉得大家的答案都不太好，好多人在递归里用两层for循环来枚举。。
     * 这个答案思路还比较清晰，用visited来标识
     */
    public void solveSudoku_(char[][] board) {
        dfs___(board, 0);
    }

    private boolean dfs___(char[][] board, int d) {
        if (d == 81) return true; //found solution
        int i = d / 9, j = d % 9;
        if (board[i][j] != '.') return dfs(board, d + 1);//prefill number skip

        boolean[] flag = new boolean[10];
        validate(board, i, j, flag);
        for (int k = 1; k <= 9; k++) {
            if (flag[k]) {
                board[i][j] = (char) ('0' + k);
                if (dfs___(board, d + 1)) return true;
            }
        }
        board[i][j] = '.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }

    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag, true);
        for (int k = 0; k < 9; k++) {
            if (board[i][k] != '.') flag[board[i][k] - '0'] = false;
            if (board[k][j] != '.') flag[board[k][j] - '0'] = false;
            int r = i / 3 * 3 + k / 3;
            int c = j / 3 * 3 + k % 3;
            if (board[r][c] != '.') flag[board[r][c] - '0'] = false;
        }
    }

    /**
     * 20200319
     * wrote for Elaine
     */
    public void solveSudoku__(char[][] board) {
        long before = System.currentTimeMillis();
        dfs__(board, 0);
        System.out.println("===================\nTime elapsed: " + (System.currentTimeMillis() - before) + " ms.\n===================\n");
    }

    private boolean dfs__(char[][] board, int d) {
        if (d == 81) {
            for (char[] row : board) {
                for (char c : row) System.out.print(c + ", ");
                System.out.println();
            }
            return true; // found solution
        }
        int i = d / 9, j = d % 9;
        if (board[i][j] != '.') return dfs__(board, d + 1);// skip

        HashSet<Integer> used = new HashSet<>();
        checkUsed(board, i, j, used);
        for (int k = 1; k <= 9; k++) {
            if (!used.contains(k)) {
                board[i][j] = (char) ('0' + k);
                if (dfs__(board, d + 1)) return true;
            }
        }
        board[i][j] = '.'; // backtrack
        return false;
    }

    private void checkUsed(char[][] board, int i, int j, HashSet<Integer> used) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] != '.') used.add(board[i][k] - '0');
            if (board[k][j] != '.') used.add(board[k][j] - '0');
            int r = i / 3 * 3 + k / 3;
            int c = j / 3 * 3 + k % 3;
            if (board[r][c] != '.') used.add(board[r][c] - '0');
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuSolver().solveSudoku__(board);
    }
}
