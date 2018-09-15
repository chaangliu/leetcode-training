package array;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 * Created by DrunkPiano on 2017/2/8.
 */

public class Searcha2DMatrix {

    public static void main(String args[]) {
        int[][] a = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        Searcha2DMatrix searcha2DMatrix = new Searcha2DMatrix();
        System.out.println(searcha2DMatrix.searchMatrix(a, 3));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int length = matrix.length * matrix[0].length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;
            if (matrix[row][col]==target){
                return true;
            }
            else if(matrix[row][col]<target){
                left = mid+1;
            }
            else right = mid -1;

        }
        return false;
    }
}
