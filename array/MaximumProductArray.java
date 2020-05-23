package array;

/**
 * 2,3,-1,4 return 6
 * Created by DrunkPiano on 2017/4/11.
 */

public class MaximumProductArray {
    /**
     * 题意：给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 解法：跟那个最大连续字数组的和类似。
     * A[i] > 0
     * dp[i][0] = Math.max(A[i] * dp[i - 1][0], A[i]);
     * dp[i][1] = Math.min(A[i] * dp[i - 1][1], A[i]);
     * A[i] < 0
     * dp[i][0] = Math.max(A[i] * dp[i - 1][1], A[i]);
     * dp[i][1] = Math.min(A[i] * dp[i - 1][0], A[i]);
     **/
    public int maxProduct(int[] A) {
        if (A.length == 0) return 0;
        int n = A.length, res = A[0];
        int[][] dp = new int[n][2];// dp[i][0]代表截至当前（必须包含当前）的最大乘积, dp[i][0]代表截至当前（必须包含当前）的最小乘积
        dp[0][0] = A[0];
        dp[0][1] = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] > 0) {
                dp[i][0] = Math.max(A[i] * dp[i - 1][0], A[i]);
                dp[i][1] = Math.min(A[i] * dp[i - 1][1], A[i]);
                res = Math.max(res, dp[i][0]);
            } else {
                dp[i][0] = Math.max(A[i] * dp[i - 1][1], A[i]);
                dp[i][1] = Math.min(A[i] * dp[i - 1][0], A[i]);

                res = Math.max(res, dp[i][0]);
            }
        }
        return res;
    }
}
