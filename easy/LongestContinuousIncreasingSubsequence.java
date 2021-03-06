package easy;

/**
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 * Note: Length of the array will not exceed 10,000.
 * <p>
 * 20190520
 */
public class LongestContinuousIncreasingSubsequence {
    /**
     * 题意：求最长递增子串。
     * 解法：dp[i] = A[i] > A[i - 1] ? dp[i - 1] + 1 : 1
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 1, tmp = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                tmp++;
                res = Math.max(tmp, res);
            } else {
                tmp = 1;
            }
        }
        return res;
    }
}
