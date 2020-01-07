package dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * A partially filled sudoku which is valid.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Example 1:
 * Input:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 * <p>
 * Input:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 * Created by DrunkPiano on 2017/4/13.
 * 20190107 --review
 */

public class ValidSudoku {
    /**
     * 题意：给你一个board，里面可能有0-9，或者点(代表empty)，问你这个board是否是一个合理的数独board。
     * 今天又做这道题，题目没读懂，把它当成sudoku solver那题了，我还在想怎么这题变成Medium了。
     * 然后看了讨论区的答案，发现Stefan Porchman非常巧妙的one pass解法，encode + hashset
     */
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }

    /**
     * 原始解法
     */
    public boolean isValidSudoku__(char[][] board) {
        if (board.length != 9 || board[0].length != 9) return false;
        int[] temp = new int[9];
        //判断每一行
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (temp[board[i][j] - '1'] != 0) {
                        return false;
                    }
                    temp[board[i][j] - '1'] = 1;
                }
            }
            temp = new int[9];
        }

        //判断每一列
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    if (temp[board[j][i] - '1'] != 0) {
                        return false;
                    }
                    temp[board[j][i] - '1'] = 1;
                }
            }
            temp = new int[9];
        }

        //判断9个小units
        for (int block = 0; block < 9; block++) {
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (temp[board[i][j] - '1'] != 0) {
                            return false;
                        }
                        temp[board[i][j] - '1'] = 1;
                    }
                }
            }
            temp = new int[9];
        }
        return true;

    }
}
