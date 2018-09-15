package array;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * <p>
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 * Created by DrunkPiano on 2017/1/23.
 * <p>
 * <p>
 * <p>
 * status transfer equation:
 * Paths[i][j] = Paths[i-1][j] + Paths[i][j-1]
 */

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            array[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            array[0][i] = 1;
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                array[i][j] = array[i - 1][j] + array[i][j - 1];
            }
        return array[m - 1][n - 1];
    }

    public static void main(String args[]) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println("paths--->" + uniquePaths.uniquePaths(3, 3));
    }
}
