package dp;

import java.util.Arrays;

/**
 * Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.
 Return the minimum sum of a falling path with non-zero shifts.
 Example 1:
 Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 Output: 13
 Explanation:
 The possible falling paths are:
 [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 The falling path with the smallest sum is [1,5,7], so the answer is 13.
 Constraints:
 1 <= arr.length == arr[i].length <= 200
 -99 <= arr[i][j] <= 99
 20191215
 */
public class MinimumFallingPathSumII {
    /**
     * 题意：一个n * n的square数组，从上到下找一个路径，相邻两行不能选用同一列的字母。
     * 双周赛第四题。应该算不是hard了。这题一看就知道是DP，模仿I的思路写就行了。我用了一个算是技巧的方式，排序，自认为挺巧妙的。
     */
    public int minFallingPathSum(int[][] arr) {
        int r = arr.length, c = arr[0].length;
        int[] dp = arr[0].clone();
        Arrays.sort(dp);
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i - 1][j] == dp[0]) {
                    arr[i][j] += dp[1];//取第二大的数。
                } else {
                    arr[i][j] += dp[0];
                }
                if (j == c - 1) {
                    dp = arr[i].clone();
                    Arrays.sort(dp);
                }
            }
        }
        return dp[0];
    }
}
