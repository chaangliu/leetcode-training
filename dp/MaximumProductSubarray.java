package dp;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * 20190427
 */
public class MaximumProductSubarray {
    /**
     * 题意：求最长乘积子串。
     * 解法：注意这里记录最大最小值就行，不要强求正负。另外不要对A[i]正负分情况讨论，否则情况会比较复杂。
     **/
    public int maxProduct_(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    /**
     * O(1) space写法
     * 连续subarray，很容易想到存储【必须包含】当前位数字时的最优值，因为后面负数可能变成正数，所以保存最大值和最小值
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = nums[0], min = res, max = res;
        for (int i = 1; i < nums.length; i++) {

            //一次for循环结束之后我们想让max保存max和min中【相对】大的那个数；所以如果这里是负数，就把max和min先交换
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }

            max = Math.max(nums[i], nums[i] * max);
            min = Math.min(nums[i], nums[i] * min);
            res = Math.max(res, max);
        }
        return res;
    }
}
