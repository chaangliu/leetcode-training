package array;

/**
 * Created by DrunkPiano on 2017/1/17.
 */

public class SpiralMatrix2 {
    public static void main(String args[]) {
        SpiralMatrix2 spiralMatrix2 = new SpiralMatrix2();
        int [][] result = spiralMatrix2.createMatrix(3);
        System.out.println(result.toString());
    }

    private int[][] createMatrix(int n) {
        int matrix[][] = new int[n][n];
        int top = 0;
        int right = n - 1;
        int down = n - 1;
        int left = 0;
        int num = 1;// the num to be inserted into the matrix
        while (true) {
            //insert the first row
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num;
                num++;
            }
            top++;
            if (top > down) break;

            //insert the last column
            for (int i = top; i <= down; i++) {
                matrix[i][right] = num;
                num++;
            }
            right--;
            if (left > right) break;

            //insert the last row
            for (int i = right; i >= left; i--) {
                matrix[down][i] = num;
                num++;
            }
            down--;
            if (top > down) break;

            //insert the first column
            for (int i = down; i >= top; i--) {
                matrix[i][left] = num;
                num++;
            }
            left++;
            if (left > right) break;
        }
        return matrix;

    }
}
