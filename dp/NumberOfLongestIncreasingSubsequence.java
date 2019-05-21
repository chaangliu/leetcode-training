package dp;

/**
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 * <p>
 * 20190521
 */
public class NumberOfLongestIncreasingSubsequence {
    /**
     * 这题让我想起distinct subsequences那题，那题一开始想用求LCS数量的方案来做。思路仍然很难，应该是HARD.
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        //len[i]以nums[i]结尾的LIS的长度
        //cnt[i]以nums[i]结尾的LIS数量
        int[] len = new int[n], cnt = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++) {//以nums[i]为基准，跟前面每个数j对比
                if (nums[i] <= nums[j]) continue;
                if (len[i] == len[j] + 1) {
                    cnt[i] += cnt[j];
                } else if (len[i] < len[j] + 1) {//LIS变长
                    cnt[i] = cnt[j];
                    len[i] = len[j] + 1;
                }
            }
            if (max_len == len[i]) res += cnt[i];
            if (max_len < len[i]) {
                max_len = len[i];
                res = cnt[i];
            }
        }
        return res;
    }

    public static void main(String rags[]){
        int [] nums = new int[]{1,3,5,4,7};
        new NumberOfLongestIncreasingSubsequence().findNumberOfLIS(nums);
    }
}
