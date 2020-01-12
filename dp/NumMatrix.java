package dp;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 * 20200112
 */
class NumMatrix {
    /**
     * 题意：实现快速查询某个矩形里面数组的sum。
     * 经典题了，后面很多题目都会用到。查询时间O(1)，巧妙利用面积之差。
     * 相似题目：	1314. Matrix Block Sum
     */
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }

    /**
     * 查询时间O(m)
     * 首先如果想通过输入四个数立刻就得出结果，那需要4维dp，4维数组，肯定不行。
     * 做法是完全模仿上一题，只不过上一题只有一行，这一题有很多行；
     * 对每一行分别做上一题的操作，最后进行row2 - row1次相加。
     */
    //    public NumMatrix(int[][] matrix) {
    //        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
    //        dp = new int[matrix.length][matrix[0].length];
    //        dp[0][0] = matrix[0][0];
    //        for (int i = 0; i < matrix.length; i++) {
    //            dp[i][0] = matrix[i][0];
    //            for (int j = 1; j < matrix[0].length; j++) {
    //                //if (i == 0 && j == 0) continue;
    //                dp[i][j] = matrix[i][j] + dp[i][j - 1];
    //            }
    //        }
    //        System.out.println("last is " + dp[dp.length - 1][dp[0].length - 1]);
    //    }
    //
    //    public int sumRegion(int row1, int col1, int row2, int col2) {
    //        int res = 0;
    //        for (int i = row1; i <= row2; i++) {
    //            res += dp[i][col2] - ((col1 >= 1) ? dp[i][col1 - 1] : 0);
    //        }
    //        return res;
    //    }

}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */