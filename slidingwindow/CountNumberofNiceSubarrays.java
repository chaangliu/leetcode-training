package slidingwindow;

/**
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 * Example 1:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 * Constraints:
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * 20191103
 */
public class CountNumberofNiceSubarrays {
    /**
     * 题意：求一个数组里面正好含有k个奇数的子数组的数量。
     * 周赛第二题。这题我第一时间想到了sliding window，但是发现过不了第三个case；
     * 然后发现这题很像SubarraysWithKDifferentIntegers那题，于是就模仿那题写了下通过了。
     * 对「子数组」这个概念要敏感了，就用atMost(k) - atMost(k-1)
     */
    public int numberOfSubarrays(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }

    private int helper(int[] nums, int k) {
        int l = 0, r = 0;
        int odds = 0, res = 0;
        while (r < nums.length) {
            if ((nums[r] & 1) == 1) {
                odds++;
            }
            r++;
            if (odds > k) {
                while (l < r && (nums[l] & 1) != 1) l++;
                odds--;
                l++;
            }
            res += r - l + 1;//当前的r能产生这么多新的奇数个数不超过k的subarray
        }
        return res;
    }
}
