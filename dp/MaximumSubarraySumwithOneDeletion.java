package dp;

/**
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 * Note that the subarray needs to be non-empty after deleting one element.
 * Example 1:
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 * Example 2:
 * <p>
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 * Example 3:
 * <p>
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 * 20190908
 */
public class MaximumSubarraySumwithOneDeletion {
    /**
     * 这题挺好的，也是这两天做的6题里唯一比较有价值的，就差那么一点了。。
     * 我的思路是，一开始想到，能不能贪心地去掉做小的数字，转换成最长连续子序列的和那题来做？
     * 想到，不能greedy，因为你无法预知后面又没有更好的适合删除数字的情况，所以自然就想到了动态规划；
     * 进一步的，状态转移方程，记录一个迄今为止已经删除过的rmRum和迄今为止没有删除过的sum。
     * 如果不考虑中途重新起头，转移方程是：
     * rmSum = max(sum, rmSum + arr[i])//1. 当前位置删掉 2. 之前删过
     * sum = sum + arr[i]//当前位置不删
     * <p>
     * 那如果sum需要从当前位置开始，怎么办呢？我模仿最长连续子序列和那题，想到if (arr[i] >= Math.max(sum, rmSum))时sum重新赋值arr[i]
     * 但这样并不能通过一些case，这个只是必要不充分条件，有可能后面还有更适合删除的数字，例如[11,-10,-11,8,7,-6,9,4,11,6,5,0]，rmSum到8时等于9，但是其实我们需要从8开始
     * 发么办？上面应该也能想到，既然是rmSum有可能误删，那我应该取没有删除过的和sum，与arr[i]对比取最小值，来决定是否中途开始
     * sum = max(sum + arr[i], arr[i])//1. 当前位置一直不删 2. 从当前位置另起头
     * <p>
     * 我觉得做这种有TAG的题目的练习，比做垃圾题有效得多啊
     */
    public int maximumSum(int[] arr) {
        if (arr.length == 1) return arr[0];
        int rmSum = 0;
        int sum = arr[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            rmSum = Math.max(sum, rmSum + arr[i]);
            //sum = sum + arr[i];//一直没删过的情况
            sum = Math.max(sum + arr[i], arr[i]);
            res = Math.max(res, Math.max(Math.max(rmSum, sum), arr[i]));
        }
        return res;
    }


    /**
     * 别人的代码
     * // dp[i][0] when it comes to arr[i], with no deletion
     * // dp[i][1] when it comes to arr[i], with one deletion
     * // dp[i][0] = max(dp[i - 1][0] + arr[i], arr[i])
     * // dp[i][1] = max(dp[i - 1][1] + arr[i], dp[i - 1][0])
     */
    public int maximumSum__(int[] arr) {
        int[][] dp = new int[arr.length][2];
        int max = arr[0];
        dp[0][0] = arr[0];
        dp[0][1] = Math.max(0, arr[0]);
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
            max = Math.max(max, dp[i][0]);
            max = Math.max(max, dp[i][1]);
        }
        return max;
    }

    /**
     * 我的原始代码，WA
     */
    public int maximumSum__WA(int[] arr) {
        if (arr.length == 1) return arr[0];
        int rmSum = 0;
        int sum = arr[0];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            rmSum = Math.max(sum, rmSum + arr[i]);
            //sum = sum + arr[i];//一直没删过的情况
            sum = Math.max(sum + arr[i], arr[i]);
            res = Math.max(res, Math.max(Math.max(rmSum, sum), arr[i]));
            //            if (arr[i] >= Math.max(sum, rmSum)) {//这里不能照抄最长连续子序列的做法来更新sum。。
            //                sum = arr[i];
            //                rmSum = 0;
            //            }
        }
        return res;
    }
}
