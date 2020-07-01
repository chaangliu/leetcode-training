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
     * DP：
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
     * 从后往前DP：
     * dp[i][j]代表以A[i]和B[j]为开头的最长公共subarray的长度
     * 20200701review
     */
    public int findLength_(int[] A, int[] B) {
        int m = A.length, n = B.length, res = 0;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = (i == m - 1 || j == n - 1) ? 1 : dp[i + 1][j + 1] + 1;
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res;
    }

    /**
     * 滑动窗口：分别枚举A和B的起始偏移量，对剩下的长度做对比。这样很神奇地可以把复杂度降低到O(m+n) * min(m,n)，同样小于O(n^3)
     */
    public int findLength__(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }
}
