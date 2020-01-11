package array;

/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Follow up:
 * Could you do this in-place?
 * <p>
 * Created by DrunkPiano on 2017/1/5.
 * 20200111 --review
 */

public class RotateImage {

    /**
     * 题意：顺时针旋转图像90度。要求inplace。
     * 解法：这题不是很intuitive，所以想不出来很正常。
     * 要记住。通用解法是先垂直对称再对角线对称。
     * 顺时针逆时针取决于上下翻转还是左右翻转。
     * <p>
     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     * <p>
     * anticlockwise rotate
     * first reverse left to right, then swap the symmetry
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
     */
    public void rotate(int[][] matrix) {
        int temp;
        int n = matrix.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i; j++) {
                temp = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;

            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - i][j] = temp;
            }
        }
    }
}
