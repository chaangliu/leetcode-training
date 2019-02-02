package dp;

import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * <p>
 * Input: [1, 2, 3, 5]
 * <p>
 * Output: false
 * <p>
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * <p>
 * 20190202
 */
public class PartitionEqualSubsetSum {
    /**
     * 01背包问题，二维DP
     * 动态转移方程：dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) return false;
        //dp[i][j] representing whether j can be gotten from first i elements; nums要取len + 1
        boolean dp[][] = new boolean[nums.length + 1][sum / 2 + 1];
//        for (int i = 0; i < nums.length; i++) {
//            dp[i][0] = false;
//        }
//        Arrays.fill(dp[0], false);
        dp[0][0] = true;
        for (int i = 1; i < nums.length + 1; i++)
            for (int j = 1; j < sum / 2 + 1; j++) {
                dp[i][j] = dp[i - 1][j] || j - nums[i - 1] >= 0 && dp[i - 1][j - nums[i - 1]];
                if (dp[i][j] && j == sum / 2) return true;
            }
        return false;
    }


    /**
     * 由于只用到了上一行的数组，所以可以用滚动数组优化成1维DP:
     */
    public boolean canPartition__1D(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        Arrays.fill(dp, false);
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[sum];
    }

    public static void main(String args[]) {
        int[] nums = {1, 5, 11, 5};
        new PartitionEqualSubsetSum().canPartition(nums);
    }
}
