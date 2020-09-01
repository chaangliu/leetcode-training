package dp;

/**
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 * 示例  1：
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 20200901
 */
public class MaximumLengthofSubarrayWithPositiveProduct {
    /**
     * 题意：给你一个数组，问最长的整数连续subarray长度是多少。
     * 解法：跟maximum product subarray那题很像，dp[i][0]代表截至i为止必须包含A[i]的最长正数子序列长度，dp[i][1]代表截至i为止必须包含A[i]的最长负数子序列长度
     */
    public int getMaxLen(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][2]; // 0: pos 1: neg
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (A[i] > 0) {
                if (i == 0) {
                    dp[0][0] = 1;
                } else {
                    dp[i][0] = dp[i - 1][0] + 1;
                    if (dp[i - 1][1] > 0) dp[i][1] = dp[i - 1][1] + 1; // neg = neg * pos, 注意，必须前一位已有neg，neg长度才能加1
                }
            } else {
                if (i == 0) {
                    dp[0][1] = 1;
                } else {
                    if (dp[i - 1][1] > 0) dp[i][0] = dp[i - 1][1] + 1; // neg = pos * neg, 注意，必须前一位已有neg，pos长度才能加1
                    dp[i][1] = dp[i - 1][0] + 1;
                }
            }
            // System.out.println("i " + i);System.out.println("pos " + dp[i][0]);
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }
}
