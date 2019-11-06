package dp;

/**
 * Given an integer array arr, in one move you can select a palindromic subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that subarray from the given array. Note that after removing a subarray, the elements on the left and on the right of that subarray move to fill the gap left by the removal.
 * Return the minimum number of moves needed to remove all numbers from the array.
 * Example 1:
 * Input: arr = [1,2]
 * Output: 2
 * Example 2:
 * Input: arr = [1,3,4,1,5]
 * Output: 3
 * Explanation: Remove [4] then remove [1,3,1] then remove [5].
 * Constraints:
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 20
 * 20191106
 */
public class PalindromeRemoval {
    /**
     * 题意：给你一个数组，每次可以移除其中的一个palindrome字符串，移除之后剩下的就左移。问最少移动多少次可以把字符串移空。
     * 双周赛第四题。
     * 做法：区间DP。边界我有点迷糊。。区间最大长度是n。
     * 转移方程比较复杂，大概是dp[i][j] = min(dp[1] + dp[i + 1][j], A[i] == A[i + 1] ? 1 + dp[i + 2][j], A[i] == A[i + k(k>=2)] ? dp[i + 1][k -1 ] + dp[k + 1][j])
     * 参考：https://www.acwing.com/solution/LeetCode/content/5795/
     */
    public int minimumMoves(int[] A) {
        int n = A.length;
        int[][] dp = new int[n + 1][n + 1];//dp[i][j] represents the minimum removals from i to j
        for (int i = 0; i <= n; i++) dp[i][i] = 1;
        for (int len = 2; len <= n; len++) {//区间最大长度是n
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = dp[i + 1][j] + 1;
                if (A[i] == A[i + 1]) {
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i + 2][j]);
                }
                for (int k = i + 2; k <= j; k++) {
                    if (A[i] == A[k]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
