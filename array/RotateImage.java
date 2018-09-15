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
 */

public class RotateImage {

    /**
     * step1: reverse through the anticlockwise diagonal line
     * step2: reverse through the middle line of y axis
     *
     * @param matrix the matrix to rotate
     */
    public void rotate(int[][] matrix) {
        int temp;
        int n = matrix.length;
        if (n < 0) return;
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

    //    public static void main(String args[]) {
////        int[][] matrix = {{1, 2, 3, 4}, {3, 4, 4}, {3}, {3}, {3}, {3}, {3}, {3}, {3}};
//        int[][] matrix = {{1,2},{3,4}};
//        RotateImage rotateImage = new RotateImage();
//        rotateImage.rotate(matrix);
////        System.out.println(rotateImage.n);
//    }
    public static void main(String args[]) {
//        int a[][] = {{1, 2}, {3, 4, 5, 6}, {7, 8, 9}};
        int a[][] = {{1, 2}, {3, 4}};
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.println("a[" + i + "][" + j + "]=" + a[i][j]);
            }
        }
    }
}
