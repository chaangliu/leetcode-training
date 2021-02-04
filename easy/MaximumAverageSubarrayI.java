package easy;

/**
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 */
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] A, int k) {
        /**
         * 题意：求k窗口中最大的平均数。
         * 解法：sliding window，注意不能用Double.MIN_VALUE，Double.MIN_VALUE不为负数
         */
        double res = Double.NEGATIVE_INFINITY, sum = 0; // 注意，这儿不能用Double.MIN_VALUE，Double.MIN_VALUE不为负数！
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            cnt++;
            sum += A[i];
            if (cnt > k) {
                sum -= A[i - k];
            }
            if (cnt >= k) {
                res = Math.max(res, sum / k);
            }
        }
        return res;
    }
}
