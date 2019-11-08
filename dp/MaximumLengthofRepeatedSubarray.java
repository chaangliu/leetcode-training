package dp;

/**
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * Example 1:
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 * Note:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * 20191108
 */
public class MaximumLengthofRepeatedSubarray {
    /**
     * 题意：求两个数组中的最长公共subarray(LCS-subarray形式)
     * 首先，如果brute force的话，需要O(n^3)，其中最内层循环是重复子过程，于是：
     * dp[i][j]代表以A[i]和B[j]结尾的最长公共subarray的长度
     */
    public int findLength(int[] A, int[] B) {
        int n1 = A.length, n2 = B.length;
        int[][] dp = new int[n1][n2];
        int res = 0;
        dp[0][0] = A[0] == B[0] ? 1 : 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                dp[i][j] = A[i] == B[j] ? (i == 0 || j == 0) ? 1 : dp[i - 1][j - 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    /**
     * 从后往前：
     */
    public int findLength__(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i + 1][j + 1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }
}
