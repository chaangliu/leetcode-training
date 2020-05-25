package dp;

/**
 * Given two arrays nums1 and nums2.
 * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
 * A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
 * Example 1:
 * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * Output: 18
 * Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
 * Their dot product is (2*3 + (-2)*(-6)) = 18.
 * Example 2:
 * <p>
 * Input: nums1 = [3,-2], nums2 = [2,-6,7]
 * Output: 21
 * Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
 * Their dot product is (3*7) = 21.
 * Example 3:
 * <p>
 * Input: nums1 = [-1,-1], nums2 = [1,1]
 * Output: -1
 * Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
 * Their dot product is -1.
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 * 20200524
 */
public class MaxDotProductofTwoSubsequences {
    /**
     * 题意：给你两个数组，求两个子序列能得到的最大点积(dot product)。
     * 解法: DP. 这题是周赛第四题，前三题用了不到半小时，第四题debug了一个多小时也没过。。
     * 其实我刚拿到就觉得像LCS那题，于是尝试着构造了一下；但这题的情况比LCS多一些，比如两个子序列都至少包含一个数字之类的，所以一直WA。
     * 最后写了很长的代码，但还是有WA的地方。最后看了答案，跟自己思路其实差不多，只是我的要乱很多，WA无从分析了。真的非常的可惜。
     * tiankong分析的情况：
     * A[i] * A[j]
     * A[i] * A[j] + f(i - 1, j - 1)
     * f(x - 1, y)
     * f(x, y - 1)
     * f(x - 1, y - 1)
     * 要把这些情况都覆盖才行。
     */
    public int maxDotProduct(int[] A, int[] B) {
        int n = A.length, m = B.length, dp[][] = new int[n][m];
        for (int i = 0; i < n; ++i) {
            dp[i] = new int[m];
            for (int j = 0; j < m; ++j) {
                dp[i][j] = A[i] * B[j];
                if (i > 0 && j > 0) dp[i][j] += Math.max(dp[i - 1][j - 1], 0);
                if (i > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if (j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }
        return dp[n - 1][m - 1];
    }

    public int maxDotProduct__MYCODE__WA(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        Integer[][] dp = new Integer[len1 + 1][len2 + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = nums1[i - 1] * nums2[j - 1];
                } else {
                    if (nums1[i - 1] * nums2[j - 1] > 0) {
                        if (dp[i - 1][j - 1] == null) {
                            dp[i][j] = nums1[i - 1] * nums2[j - 1];
                        } else {
                            dp[i][j] = Math.max(0, dp[i - 1][j - 1]) + nums1[i - 1] * nums2[j - 1];
                            if (dp[i - 1][j] != null) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                            if (dp[i][j - 1] != null) dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                        }
                    } else {
                        if (dp[i - 1][j] == null && dp[i][j - 1] == null) {
                            dp[i][j] = nums1[i - 1] * nums2[j - 1];
                        } else if (dp[i - 1][j] == null) {
                            dp[i][j] = Math.max(dp[i][j - 1], nums1[i - 1] * nums2[j - 1]);
                        } else if (dp[i][j - 1] == null) {
                            dp[i][j] = Math.max(dp[i - 1][j], nums1[i - 1] * nums2[j - 1]);
                        } else {
                            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                        }
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
