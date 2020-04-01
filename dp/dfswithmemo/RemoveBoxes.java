package dp.dfswithmemo;

/**
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 * <p>
 * Example 1:
 * Input:
 * <p>
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * Output:
 * 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Note: The number of boxes n would not exceed 100.
 * 20200401
 */
public class RemoveBoxes {
    /**
     * 题意：一串数字，每次可以选择连续k个消掉直到全部消掉，分数+=k^2，问最大得分是多少。
     * 解法：常规的top down dp是不行的，需要借助一个k，表示[l,r]后面有k个数字和A[r]相同。
     */
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        return dfs(0, n - 1, 0, new int[n][n][n], boxes);
    }

    /**
     * 数字范围是[l,r]加上k个相同的数；[r+1,n]中有k个与r相同的元素的情况下的最大值
     **/
    private int dfs(int l, int r, int k, int[][][] memo, int[] boxes) {
        if (l > r) return 0;
        if (memo[l][r][k] > 0) return memo[l][r][k];
        while (l < r && boxes[r] == boxes[r - 1]) { --r; ++k; } // 剪枝
        int res = dfs(l, r - 1, 0, memo, boxes) + (k + 1) * (k + 1); // 初值；r和后面的k可以组成k+1个相同的数字
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                // [i+1][r-1][0]: 相当于把[i+1][r-1]这部分切下来，把第r个数留下来，跟[l][i]拼在一起，这样前面的部分就多了1个与最后一位相同的。
                res = Math.max(res, dfs(i + 1, r - 1, 0, memo, boxes) + dfs(l, i, k + 1, memo, boxes));
            }
        }
        memo[l][r][k] = res;
        return res;
    }
}
