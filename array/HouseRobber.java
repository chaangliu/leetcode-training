package array;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * Created by DrunkPiano on 2017/4/10.
 * 20190518review
 * 20200215review
 */

public class HouseRobber {
    /**
     * 题意：打劫家舍，限制是不能打劫两个连续的house，问最大收益。
     * bottom up dp:
     * dp[i]表示截至i为止能获取的最大收益。
     * dp[i] = max(dp[i-2] + A[i], dp[i-1])
     **/
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);// 已犯错误: 把dp[1]定义成了nums[1]
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }


    /**
     * top down with memo做法
     */
    int[] memo;

    public int rob_(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return rob(nums, nums.length - 1);
    }

    private int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
        memo[i] = result;
        return result;
    }
}
