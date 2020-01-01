package dp;

import java.util.List;

/**
 * You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
 * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
 * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 * In case there is no path, return [0, 0].
 * Example 1:
 * Input: board = ["E23","2X2","12S"]
 * Output: [7,1]
 * Example 2:
 * Input: board = ["E12","1X1","21S"]
 * Output: [4,2]
 * Example 3:
 * Input: board = ["E11","XXX","11S"]
 * Output: [0,0]
 * Constraints:
 * 2 <= board.length == board[i].length <= 100
 * 20200101
 */
public class NumberofPathswithMaxScore {
    /**
     * 题意：给你一个棋盘，包含数字和字母，从右下角S走到左上角E，X的话不能走；问最大能收集到的数字的sum和能达到sum的路径总数。
     * 我的做法也比较容易理解，就是用一个dp数组记录当前最大数字，path数组记录当前路径总数。但是corner case还是有些的。
     * 也可以用一个[i][j][2]数组来做，也就是两层，空间一样大的。
     * path的计算方法是取三个来源的和，就像跳台阶那样。
     * 写了将近一个小时才ac。
     */
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][] dp = new int[n][n];
        int mod = (int) 1e9 + 7;
        int[][] path = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c = board.get(i).charAt(j);
                if (c == 'E') {
                    dp[i][j] = Math.max(Math.max(dp[i][j + 1], dp[i + 1][j]), dp[i + 1][j + 1]);
                    if (dp[i][j] == dp[i][j + 1]) path[i][j] = (path[i][j] + path[i][j + 1]) % mod;
                    if (dp[i][j] == dp[i + 1][j]) path[i][j] = (path[i][j] + path[i + 1][j]) % mod;
                    if (dp[i][j] == dp[i + 1][j + 1])
                        path[i][j] = (path[i][j] + path[i + 1][j + 1]) % mod;
                } else if (c == 'X' || c == 'S') {
                    dp[i][j] = 0;
                    if (c == 'X') path[i][j] = 0;
                    else path[i][j] = 1;
                } else {
                    int digit = c - '0';
                    int tmp = 0;
                    if (i + 1 < n && j + 1 < n && dp[i + 1][j + 1] >= tmp) tmp = dp[i + 1][j + 1];
                    if (i + 1 < n && dp[i + 1][j] >= tmp) tmp = dp[i + 1][j];
                    if (j + 1 < n && dp[i][j + 1] >= tmp) tmp = dp[i][j + 1];
                    dp[i][j] = tmp + digit;
                    if (j + 1 < n && tmp == dp[i][j + 1]) {
                        path[i][j] = (path[i][j] + path[i][j + 1]) % mod;
                    }
                    if (i + 1 < n && tmp == dp[i + 1][j])
                        path[i][j] = (path[i][j] + path[i + 1][j]) % mod;
                    if (i + 1 < n && j + 1 < n && tmp == dp[i + 1][j + 1])
                        path[i][j] = (path[i][j] + path[i + 1][j + 1]) % mod;
                    if (path[i][j] == 0) dp[i][j] = 0;//已犯错误：如果没办法到这里，那么sum应该归零，case是example3那种
                }
            }

        }
        return new int[]{dp[0][0], path[0][0]};
    }

    /**
     * 摘抄一个别人的答案，他是从当前格子去左边上边和左上的格子去递推
     */
    private static final int[][] DIRS = new int[][]{{0, -1}, {-1, 0}, {-1, -1}};

    public int[] pathsWithMaxScore__(List<String> board) {
        int m = board.size(), n = board.get(0).length();
        int[][] dpSum = new int[m][n];
        int[][] dpCnt = new int[m][n];
        dpCnt[m - 1][n - 1] = 1; // start at the bottom right square
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (dpCnt[r][c] == 0) continue; // can't reach to this square
                for (int[] dir : DIRS) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nc >= 0 && board.get(nr).charAt(nc) != 'X') {
                        int nsum = dpSum[r][c];
                        if (board.get(nr).charAt(nc) != 'E')
                            nsum += board.get(nr).charAt(nc) - '0';
                        if (nsum > dpSum[nr][nc]) {
                            dpCnt[nr][nc] = dpCnt[r][c];
                            dpSum[nr][nc] = nsum;
                        } else if (nsum == dpSum[nr][nc]) {
                            dpCnt[nr][nc] = (dpCnt[nr][nc] + dpCnt[r][c]) % 1000000007;
                        }
                    }
                }
            }
        }
        return new int[]{dpSum[0][0], dpCnt[0][0]};
    }
}
