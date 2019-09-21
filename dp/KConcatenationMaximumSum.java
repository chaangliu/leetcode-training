package dp;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 * <p>
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 * <p>
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 * <p>
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 * Example 1:
 * <p>
 * Input: arr = [1,2], k = 3
 * Output: 9
 * Example 2:
 * <p>
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 * Example 3:
 * <p>
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 * 20190916
 */
public class KConcatenationMaximumSum {
    /**
     * 这题算是一个综合题，基础是要会用DP计算连续子序列和的最大值，同时还要观察总结k非常大的情况。看了同事的题解。我考虑到了sum要分为正、负考虑，但有两点我没有考虑到：
     * 1. sum全是正数的时候要考虑prefix + mid + post的最大值
     * 2. 如何证明sum小于0的时候最多只需要两个连续的？很简单，因为sum<0，所以3个sum必定比2个sum要更小
     */
    public int kConcatenationMaxSum(int[] arr, int k) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum > 0 && k >= 2) {
            long preSum = 0, tmp = 0;
            for (int i = 0; i < arr.length; i++) {
                tmp += arr[i];
                preSum = Math.max(preSum, tmp);
            }
            long postSum = 0;
            tmp = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                tmp += arr[i];
                postSum = Math.max(postSum, tmp);
            }
            long total = (k - 2) * sum + preSum + postSum;
            System.out.println("大于零");
            return (int) (total % 1000000007);
        }
        System.out.println("小于零");
        int[] dp = new int[arr.length * 2];
        int[] nums = new int[arr.length * 2];//这里可以用O(1)空间滚动来求，curSum = curSum < 0? 0 : curSum
        for (int i = 0; i < arr.length; i++) nums[i] = arr[i];
        for (int i = arr.length; i < arr.length * 2; i++) nums[i] = arr[i - arr.length];
        int max = 0;
        dp[0] = nums[0];
        for (int i = 1; i < arr.length * 2; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
