package array;

/**
 * Follow up for "Unique Paths":
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * Note: m and n will be at most 100.
 * <p>
 * status transfer equation:
 * Paths[i][j] = Paths[i-1][j] + Paths[i][j-1] , array[i][j] = 1;
 * = 0 , array[i][j] = 0;
 */

public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                while (i < m) {
                    obstacleGrid[i][0] = 0;
                    i++;
                }
            } else
                obstacleGrid[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) while (j < n) {
                obstacleGrid[0][j] = 0;
                j++;
            }
            else
                obstacleGrid[0][j] = 1;
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else if (i > 0 && j > 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        return obstacleGrid[m - 1][n - 1];
    }

    public static void main(String args[]) {
        UniquePaths2 uniquePaths = new UniquePaths2();
        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        System.out.println("paths--->" + uniquePaths.uniquePathsWithObstacles(obstacleGrid));
    }
}
