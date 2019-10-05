package dp;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * Created by DrunkPiano on 2017/4/6.
 */

public class LongestIncreasingSubsequence {
    /**
     * dp[i] represents the length of the longest increasing subsequence possible considering the array elements upto the ith
     * index only ,by necessarily including the ith element.
     * <p>
     * dp[i]表示[0,i]范围的LIS，但是一定要包含第i个元素。
     * 之所以必须包含第i个元素，是因为每次新增加的数是和nums[i](代码中是j)对比，比如1，2，0，..，4；4>0没有用，必须还要确定0就包含在了LIS里。
     * 这也是为什么用了一个max，保存每趟最优值，因为dp[dp.length - 1]保存的不一定是最大值，毕竟我们不一定要nums[nums.length - 1]
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int dp[] = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;//don't miss this
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String args[]) {
        //int [] nums  = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));

    }
}
