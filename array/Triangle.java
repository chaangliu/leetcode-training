package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * Created by DrunkPiano on 2017/2/23.
 */

public class Triangle {
    /**
     * 题意：求三角形最小的path sum。
     * 解法：dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1])
     * 如果用滚动数组空间压缩，一定要注意处理覆盖的情况。
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(), res = Integer.MAX_VALUE;
        Integer[][] dp = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int cur = triangle.get(i).get(j);
                if (i == 0) {
                    dp[i][j] = cur;
                } else {
                    int prev;
                    if (j == 0) {
                        prev = dp[i - 1][j];
                    } else if (dp[i - 1][j] == null) {
                        prev = dp[i - 1][j - 1];
                    } else {
                        prev = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                    }
                    dp[i][j] = prev + cur;
                }
                if (i == n - 1) res = Math.min(dp[i][j], res);
            }
        }
        return res;
    }

    /**
     * 滚动数组，O(2n)space，来自https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
     */
    public int minimumTotal_O2n(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            int curr = i % 2; // trick
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;
    }

    public int minimumTotal_O1n(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) { // 从后往前
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }
}
