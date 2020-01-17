package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * <p>
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * <p>
 * Created by DrunkPiano on 2017/2/7.
 */

public class SetMatrixZeros {
    /**
     * 题意：给你一个二维数组，如果某个元素是0，就把这一行这一列都设成0. 要求in place。
     * 思路：早期的题很多都是比较面试向，让你找到最佳的解法，比如O(1) space, one pass之类。
     * 这题我看套路区最好的解法是，先正向遍历一遍，如果某个格子是0，就把0投影到top row和left column，然后倒着遍历一遍。这样能保证第一行和第一列最后才被覆盖。
     */
    public void setZeroes(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }


    /**
     * 另外也可以记录0的行列位置，这样比较好操作，不过不是O(1) space了。
     */
    public void setZeroes_(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        HashSet<Integer> rowIndex = new HashSet<>();
        HashSet<Integer> colIndex = new HashSet<>();

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rowIndex.add(i);
                    colIndex.add(j);
                }
            }

        for (int i : rowIndex) {
            for (int j = 0; j < col; j++) matrix[i][j] = 0;
        }
        for (int j : colIndex) {
            for (int i = 0; i < row; i++) matrix[i][j] = 0;
        }
    }

}


/**
 * 1 2 3
 * 4 4 0
 * 7 6 9
 * <p>
 * [0][1]
 **/