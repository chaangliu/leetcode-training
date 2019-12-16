package binarysearch;

/**
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 * Example 1:
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 * Example 2:
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 * Example 3:
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 * Example 4:
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * Output: 2
 * Constraints:
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 * 20191216
 */
public class MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {
    /**
     * 题意：在一个m * n的矩阵里面找一个最大边长的square，里面的数字的sum加起来<=threshold。
     * 解法：prefix-sum + binary search, O(MN * LOG(MIN(M,N)))
     * 前缀和的这种做法，之前某道题出现过，重复面积的；但这次有没想起来。
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int lo = 0, hi = Math.min(m, n) + 1;//我发现这题的二分很典型，hi边界必须要+1，因为hi也很可能valid，如果不+1, lo < hi不满足就退出了。
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            System.out.println("mid is " + mid + " low is " + lo + " hi is " + hi);
            if (valid(mid, prefix, threshold)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return hi - 1;//这里如果side-length 2是valid的，最后会在3退出，所以-1
    }

    /**
     * 枚举每一个side-length是mid的square，如果满足threshold立刻返回true。如果都不满足，返回false。
     **/
    private boolean valid(int mid, int[][] prefix, int threshold) {
        for (int i = mid; i < prefix.length; i++) {
            for (int j = mid; j < prefix[0].length; j++) {
                if (prefix[i][j] - prefix[i - mid][j] - prefix[i][j - mid] + prefix[i - mid][j - mid] <= threshold) {
                    System.out.println(mid + " is valid");
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * one pass, O(MN)
     */
    public int maxSideLength_(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] sums = new int[n + 1][m + 1];
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sums[i + 1][j + 1] = sums[i + 1][j] + sums[i][j + 1] - sums[i][j] + mat[i][j];
                if (i - max >= 0 && j - max >= 0 &&
                        sums[i + 1][j + 1] - sums[i - max][j + 1] - sums[i + 1][j - max] + sums[i - max][j - max] <= threshold
                ) {
                    max += 1;
                }
            }
        }
        return max;
    }
}
