package slidingwindow;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * Created by DrunkPiano on 2017/5/4.
 */

public class MinimumSizeSubarraySum {

    /**
     * 题意：找出最短的sum≥ s 的subarray的长度。
     * 解法：sliding window, O(n)
     * 或者，用prefix sum + bianry search，O(n log n)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int j = 0;
        int i = 0;

        while (j < nums.length) {
            sum += nums[j++];

            while (sum >= s) {
                minLen = Math.min(minLen, j - 1 - i + 1);
                sum -= nums[i++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
