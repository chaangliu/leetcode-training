package dp.knapsack;

/**
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * <p>
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 * <p>
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * <p>
 * Note:
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * Example 1:
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 * <p>
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 * Example 2:
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 * <p>
 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 * <p>
 * 20190409
 */
public class OnesAndZeroes {
    /**
     * 【题意】使用m个0和n个1，在Array中寻找最多能组成多少个字符串。
     * 【解法】01背包问题，二维dp。
     * dp[i][j][k]代表对于j个0和k个1，能在前i个资源里面构成的最多的资源数量
     * 转移方程：
     * dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - count[0]][k - count[1]] + 1)
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i < strs.length + 1; i++) {//对于每个资源
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int[] count = calc(strs[i - 1]);//计算资源中0和1的个数
                    if (count[0] <= j && count[1] <= k) {
                        //dp[i - 1][j][k]：不使用ith资源，使用j个0和k个1
                        //dp[i - 1][j - count[0]][k - count[1]] + 1: 必须使用ith资源
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - count[0]][k - count[1]] + 1);//不使用或必须使用ith资源
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private int[] calc(String str) {
        int[] res = new int[2];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                res[0]++;
            } else {
                res[1]++;
            }
        }
        return res;
    }

    /**
     * 可以三维转二维(因为对于前i个资源只需要知道前i - 1个资源的利用情况)
     */
    public int findMaxForm__2D(String[] strs, int m, int n) {
        int l = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        int[] nums = new int[]{0, 0};
        for (String str : strs) {
            nums = calc(str);
            for (int j = m; j >= nums[0]; j--) {//这边是倒过来遍历，否则dp[j][k]会是上一行利用过的，会出现资源重复使用的情况
                for (int k = n; k >= nums[1]; k--) {
                    if (j >= nums[0] && k >= nums[1]) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - nums[0]][k - nums[1]] + 1);
                    } else {
                        dp[j][k] = dp[j][k];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
