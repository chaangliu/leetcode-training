package array;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/1/7.
 */

public class MaximumSubArray {
    /**
     * 题意：求最大连续子序列的和。
     * 解法：DP。
     * dp[i] = dp[i - 1] > 0 ? dp[i - 1] + A[i] : A[i]
     */
    public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = A[0];
        int res = dp[0];// 已犯错误: 写成了Integer.MIN_VALUE，对于[1] WA
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + A[i] : A[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    /**
     * //    approach2: binary search + recursion
     * //    public int maxSubArray(int[] nums) {
     * //        int maxValue = Integer.MIN_VALUE;
     * //        return findMaxSubArray(nums, 0, nums.length - 1, maxValue);
     * //    }
     */
    public int findMaxSubArray(int nums[], int left, int right, int maxValue) {

        if (left > right)
            return Integer.MIN_VALUE;
        int mid = (left + right) / 2;
        //find lmax
        int lmax = findMaxSubArray(nums, left, mid - 1, maxValue);
        //find rmax
        int rmax = findMaxSubArray(nums, mid + 1, right, maxValue);

        maxValue = Math.max(maxValue, lmax);
        maxValue = Math.max(maxValue, rmax);

        //find middle lmax
        int sum = 0, mlmax = 0;
        for (int i = mid - 1; i >= left; i--) {
            sum += nums[i];
            if (sum > mlmax)
                mlmax = sum;
        }
        sum = 0;
        int mrmax = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > mrmax)
                mrmax = sum;
        }
        maxValue = Math.max(maxValue, mlmax + mrmax + nums[mid]);


        return maxValue;
    }
}
