package dp.dfswithmemo;

/**
 * Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 * Example 1:
 * Input: [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 * Example 2:
 * Input: [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
 * Example 3:
 * Input: [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 * Note:
 * 3 <= A.length <= 50
 * 1 <= A[i] <= 100
 * 20191026
 */
public class MinimumScoreTriangulationofPolygon {
    /**
     * 题意：给你一个N边形，顺时针给你每个点的权值，把这个N边形分割成N - 2个三角形；然后把每个三角形顶点的权值相乘，求所有三角形三点相乘之和的最小值。
     * 这题我记得是去年某次周赛的第三题，做出来的人不多，我照例是两题投；那天是个工作日，breathe老师做出来了，最终排名200，我默默地低下了头。
     * 这题我拿到之后模拟了一下，感觉需要把连续的三个点取出来计算，然后把中间的点去掉，重复计算。然后不知道怎么递归了。
     * TOP DOWN思路：找两个相邻的点i,j当做底边，顶点k在(i,j)之间游走，这个三角形会把N边形分割成两个部分，然后递归地计算这两部分的最小值。
     * 重点是，你需要把首尾两个点i和j作为递归的入口(底边)，这样才能递归起来；不用担心，所有分割的方式最终都需要有一个三角形以i,j为底边。
     **/
    public int minScoreTriangulation(int[] A) {
        return dfs(new int[A.length][A.length], A, 0, A.length - 1);
    }

    /**
     * 返回以i,j为底边的三角形能把i到j范围内的多边形分成的最小值
     */
    private int dfs(int[][] memo, int[] A, int i, int j) {
        if (memo[i][j] > 0) return memo[i][j];
        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            res = Math.min(res, dfs(memo, A, i, k) + dfs(memo, A, k, j) + A[i] * A[j] * A[k]);
        }
        memo[i][j] = res == Integer.MAX_VALUE ? 0 : res;
        return memo[i][j];
    }

    /**
     * 下面有三种BOTTOM UP的写法，总之都是把[i,j]之间的距离从2开始扩大。可以枚举i,j；也可以枚举区间长度len
     * <p>
     * BOTTOM UP, 区间DP,枚举区间长度
     */
    public int minScoreTriangulation__(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }

    /**
     * BOTTOM UP, 从前往后枚举i,j
     */
    public int minScoreTriangulation___(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int j = 2; j < n; ++j) {
            for (int i = j - 2; i >= 0; --i) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }


    /**
     * BOTTOM UP, 从后往前枚举i, j
     * (区间?)DP，同样比TOP DOWN理解起来要难
     * 相似题目：312 Burst Balloons；矩阵连乘
     */
    public int minScoreTriangulation__DP(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; ++i)
            for (int j = i + 1; j < n; ++j) {
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j],
                            dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
                }
            }
        return dp[0][n - 1];
    }
}