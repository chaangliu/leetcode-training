package array;

/**
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * Example:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * Output:  [1,2,4,7,5,3,6,8,9]
 * <p>
 * Explanation:
 * Note:
 * <p>
 * The total number of elements of the given matrix will not exceed 10,000.
 */
public class DiagonalTraverse {
    /**
     * 题意：给你一个二维数组，让你按照图中的方式打印里面的数字。
     * 解法：这种题一般就是找i和j的增减规律，和遇到边界的处理情况；但是写起来很难一次通过，调试了几次才完事。
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = 0, cnt = 0;
        int[] res = new int[m * n];
        int flag = -1;
        boolean skip = false;
        while (cnt < m * n) {
            if (i < m && j < n)
                res[cnt++] = matrix[i][j];
            if (i == 0 && flag == -1 && !skip) {
                j += 1;
                flag = 1;
                skip = true;
                continue;
            }
            if (j == 0 && flag == 1 && !skip) {
                i += 1;
                flag = -1;
                skip = true;
                continue;
            }
            skip = false;
            i += flag;
            j -= flag;
        }
        return res;
    }

    /**
     * 另一种写法
     */
    public int[] findDiagonalOrder_(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int r = 0, c = 0, m = matrix.length, n = matrix[0].length, arr[] = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) { // moving up
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else {                // moving down
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return arr;
    }
}
