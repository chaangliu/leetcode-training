package array;

import java.util.ArrayList;
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
 */

public class SpiralMatrix {
    public static void main(String args[]) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        System.out.println(spiralMatrix.spiralOrder(matrix));

    }

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
}
