package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 54.
 * <p>
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * For example,
 * Given the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * <p>
 * Created by DrunkPiano on 2017/1/14.
 * 202001111 --review
 */

public class SpiralMatrix {
    /**
     * 题意：螺旋打印数组。
     * 解法：模拟。注意退出条件。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        int top = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int down = matrix.length - 1;
        while (true) {
            //print out the first row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            if (top > down) break;

            //print out the last column
            for (int i = top; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;

            //print out the last row
            for (int i = right; i >= left; i--) {
                result.add(matrix[down][i]);
            }
            down--;
            if (top > down) break;

            //print out the first column
            for (int i = down; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;
        }
        return result;
    }

    /**
     * 另一种写法
     */
    public List<Integer> spiralOrder_(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) return res;
        int n = matrix.length, m = matrix[0].length;
        int up = 0, down = n - 1;
        int left = 0, right = m - 1;
        while (res.size() < n * m) {
            for (int j = left; j <= right && res.size() < n * m; j++)
                res.add(matrix[up][j]);

            for (int i = up + 1; i <= down - 1 && res.size() < n * m; i++)
                res.add(matrix[i][right]);

            for (int j = right; j >= left && res.size() < n * m; j--)
                res.add(matrix[down][j]);

            for (int i = down - 1; i >= up + 1 && res.size() < n * m; i--)
                res.add(matrix[i][left]);

            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}
