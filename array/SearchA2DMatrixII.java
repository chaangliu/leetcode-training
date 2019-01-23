package array;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 * <p>
 * 20190123
 */
public class SearchA2DMatrixII {
    //O(m+n)，这题跟剑指offer第一题一样
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        //左下角(array[length - 1][0])
        int start_x = matrix.length - 1;//行数
        int start_y = 0;//列数
        while (start_x >= 0 && start_y < matrix[0].length) {
            if (matrix[start_x][start_y] == target) return true;
            if (matrix[start_x][start_y] < target) {
                start_y++;
            } else {
                start_x--;
            }
        }
        return false;
    }
}
