package dp;

import java.util.Arrays;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * <p>
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * <p>
 * If there is no way to make arr1 strictly increasing, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 * Example 2:
 * <p>
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
 * Example 3:
 * <p>
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 * Constraints:
 * <p>
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 * <p>
 * 20190910
 */
public class MakeArrayStrictlyIncreasing {
    /**
     * 这题我是照着WNJXYK的讲解思路做的，这个操作说实话我是想不到，另外现在仍然觉得思路不是很清晰，有点难理解，没有完全懂。我觉得对DP这种TAG做专题训练很值得。
     * dp[i][j]代表arr1[i]换成arr2[j]满足单调递增需要的最小操作数，dp[i][j] == -1 代表无法达成单调增，j == m 代表不换，j < m代表换成arr2[j]
     **/
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int[][] dp = new int[arr1.length][arr2.length + 1];
        Arrays.sort(arr2);
        for (int[] arr : dp) Arrays.fill(arr, -1);
        int n = arr1.length, m = arr2.length;
        dp[0][m] = 0;
        for (int i = 0; i < m; i++) dp[0][i] = 1;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i][j] == -1) continue; //第i位换成arr2[j]无法单调增
                int val = j == m ? arr1[i] : arr2[j]; //当前位置如果是m，就不换；否则换成_j
                if (arr1[i + 1] > val) {// i + 1位置不需要换，已经单调增
                    if (dp[i + 1][m] == -1 || dp[i + 1][m] > dp[i][j]) {
                        dp[i + 1][m] = dp[i][j];
                    }
                }

                int upperIndex = upper_bound(arr2, val);//如果把i + 1位置换掉，那一定是贪心地找最小的大于val的那个数
                if (upperIndex < m) {
                    if (dp[i + 1][upperIndex] == -1 || dp[i + 1][upperIndex] > dp[i][j] + 1) {
                        dp[i + 1][upperIndex] = dp[i][j] + 1;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= m; i++) {
            if (dp[n - 1][i] > -1) {
                res = Math.min(res, dp[n - 1][i]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int upper_bound(int[] arr, int val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > val) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
