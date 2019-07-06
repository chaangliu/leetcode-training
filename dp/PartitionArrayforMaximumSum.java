package dp;

/**
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * <p>
 * Return the largest sum of the given array after partitioning.
 * Example 1:
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 * Note:
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 * 20190706
 */
public class PartitionArrayforMaximumSum {
    /**
     * 思路：每次遇到一个数，试着把最后K位改成A[i]到A[i - k + 1]里面最大的数，k<=K
     */
    public int maxSumAfterPartitioning(int[] A, int K) {
        int N = A.length, dp[] = new int[N];
        for (int i = 0; i < N; ++i) {
            int max = 0;
            for (int k = 1; k < K && i - k + 1 >= 0; k++) {
                max = Math.max(max, A[i - k + 1]);//i 从 0开始，所以i - (k - 1)代表最后一位
                dp[i] = Math.max(dp[i], (i - k >= 0 ? dp[i - k] : 0) + k * max);
            }
        }
        return dp[N - 1];
    }

    /**
     * 下面是我模仿1105. Filling Bookcase Shelves书架放书那题思路写的，写起来下标处理起来比较绕；同样，那题也可以用这题的写法来做。
     * //1,15,7,9,2,5,10
     * //dp[i]表示第前i个数的最大可能结果
     * //1. A[i]放到新的一层：dp[i] = dp[i - 1] + A[i]
     * //2. A[i]放到上一层：  dp[i] = max(dp[i - 2] + 3 * max(A[i - 2],A[i - 1], A[i]), dp[i - 1] + 2 * max(A[i - 1], A[i]) )
     */
    public int maxSumAfterPartitioning_(int[] A, int K) {
        int dp[] = new int[A.length + 1];
        for (int i = 1; i < A.length + 1; i++) {
            dp[i] = dp[i - 1] + A[i - 1];
            int max = A[i - 1], count = 0;//上一层的书数量
            for (int j = i - 1; j > 0 && i - count - 1 >= 0; j--) {
                if (++count >= K) break;
                max = Math.max(A[j - 1], max);
                dp[i] = Math.max(dp[i], dp[i - count - 1] + (count + 1) * max);//已犯错误：dp[i - count - 1]写成了dp[i - count]，调了半天
                //dp[i] = Math.max(dp[i], dp[j - 1] + (count + 1) * max);//袁老师建议我把i - count - 1写成j - 1
            }
        }
        return dp[dp.length - 1];
    }
}
