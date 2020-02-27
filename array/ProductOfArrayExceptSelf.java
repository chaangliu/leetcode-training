package array;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 * <p>
 * ref: https://blog.csdn.net/wzy_1988/article/details/46916179
 */
public class ProductOfArrayExceptSelf {
    /**
     * 题意：给你一个数组，计算每个index上除了这个index之外所有数字的乘积。不许用除法，不能用额外空间（但可以申请一个O(n)的res数组返回）
     * 解法：从左往右计算左边所有数字的乘积，再从右往左计算右边所有数字的乘积
     * Numbers:     2    3    4     5
     * Lefts:            2  2*3 2*3*4
     * Rights:  3*4*5  4*5    5
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) return null;
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;
    }
}
