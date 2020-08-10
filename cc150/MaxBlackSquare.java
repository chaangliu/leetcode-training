package cc150;

/**
 * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
 * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
 * 输入:
 * [
 *    [1,0,1],
 *    [0,0,1],
 *    [0,0,1]
 * ]
 * 输出: [1,0,2]
 * 解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
 */
public class MaxBlackSquare {
    /**
     * 题意：给你一个正方形，求由0组成的最大子正方形，用[r,c,len]表示，rc代表左上角的坐标。
     * 解法：dp[i][j][k]代表[i,j]位置向右(k=0)和向下(k=1)的黑边长度。
     */
    public int[] findSquare(int[][] matrix) {
        int[] res = new int[3];
        int n = matrix.length;
        if (n == 0) return new int[0];
        if (n == 1) {
            if (matrix[0][0] == 0) return new int[]{0, 0, 1};
            else return new int[0];
        }
        int[][][] dp = new int[n][n][2]; // 0,向右1,向下
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (matrix[r][c] == 1) {
                    dp[r][c][0] = dp[r][c][1] = 0;
                } else {
                    if (r == n - 1) dp[r][c][1] = 1; else dp[r][c][1] = dp[r + 1][c][1] + 1;
                    if (c == n - 1) dp[r][c][0] = 1; else dp[r][c][0] = dp[r][c + 1][0] + 1;
                    int len = Math.min(dp[r][c][0], dp[r][c][1]); // 当前位置向右向下的黑边长度
                    System.out.println("r, c , len is " + r + ", " + c + ", " + len);
                    while (len >= res[2]) {
                        if (dp[r + len - 1][c][0] >= len && dp[r][c + len - 1][1] >= len) { // *关键：从下边len处向右检查，从右边len处向下检查
                            res = new int[] {r, c, len};
                            break;
                        }
                        len--;
                    }
                }
            }
        }
        return res;
    }
}
