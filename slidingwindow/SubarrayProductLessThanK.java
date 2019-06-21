package slidingwindow;

/**
 * Your are given an array of positive integers nums.
 * <p>
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 * <p>
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 * <p>
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 * 20190621
 */
public class SubarrayProductLessThanK {
    /**
     * 这题是求连续数字组成的小于k的product一共有多少种。
     * 一开始感觉像DP，但是想了下dp记录个数还是当前最大值？用二维的话j(pro)范围又太大。似乎不行。
     * 好的解法是sliding window，
     * 我一开始思考的时候感觉r指针一直往右边expand的话不满足左边再shrink的时候会漏掉一些解，
     * 实际上发现可以在r指针expand的时候就顺带把所有的解集加进来，比如5,2 -> 5,2,6，res增加的数量是6,62和6,2,5三个(j - i + 1)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int res = 0, i = 0, pro = 1;
        for (int j = 0; j < nums.length; j++) {
            pro *= nums[j];//expand
            while (i <= j && pro >= k) {//shrink
                pro /= nums[i++];
            }
            res += j - i + 1;
        }
        return res;
    }
}
