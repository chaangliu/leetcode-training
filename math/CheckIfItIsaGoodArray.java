package math;

/**
 * Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.
 * Return True if the array is good otherwise return False.
 * Example 1:
 * Input: nums = [12,5,7,23]
 * Output: true
 * Explanation: Pick numbers 5 and 7.
 * 5*3 + 7*(-2) = 1
 * Example 2:
 * Input: nums = [29,6,10]
 * Output: true
 * Explanation: Pick numbers 29, 6 and 10.
 * 29*1 + 6*(-3) + 10*(-1) = 1
 * Example 3:
 * Input: nums = [3,6]
 * Output: false
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 20191104
 */
public class CheckIfItIsaGoodArray {
    /**
     * 题意：给你一个数组，取任意几个，每个可以乘以任意系数，问能否相加得到1.能的话返回true。
     * 解法：求所有数字的GCD。如果GCD达到了1就可以返回。
     * 定理：中国剩余定理。试想对于两个数x和y，显然ax + by = 1的条件就是gcd(x,y) = 1。那么对于多个数字也同样如此。
     */
    public boolean isGoodArray(int[] nums) {
        int res = nums[0];
        for (int num : nums) {
            res = gcd(res, num);
            if (res == 1) return true;
        }
        return false;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
