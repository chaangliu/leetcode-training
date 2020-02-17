package easy;

/**
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 
 * 请你统计并返回 grid 中 负数 的数目。
 * 示例 1：
 * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * 输出：8
 * 解释：矩阵中共有 8 个负数。
 * 示例 2：
 * 输入：grid = [[3,2],[1,0]]
 * 输出：0
 * 示例 3：
 * 输入：grid = [[1,-1],[-1,-1]]
 * 输出：3
 * 示例 4：
 * 输入：grid = [[-1]]
 * 输出：1
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 * 20200217
 */
public class CountNegativeNumbersinaSortedMatrix {
    /**
     * 题意：有一个二维数组每行每列都是非递增的，求所有负数个数。
     * 这题虽然是easy但是思路挺棒的，时间可以达到O(m+n)，做法就是从左下角开始找staircase的轮廓。
     */
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length, r = m - 1, c = 0, cnt = 0;
        while (r >= 0 && c < n) {
            if (grid[r][c] < 0) {
                --r;
                cnt += n - c; // there are n - c negative numbers in current row.
            } else {
                ++c;
            }
        }
        return cnt;
    }

    /**
     * 我的代码，虽然剪枝了但仍然是O(m*n)时间
     */
    public int countNegatives_(int[][] A) {
        int m = A.length, n = A[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            if (A[i][0] < 0) {
                System.out.println("加入" + (m - i) * n);
                res += (m - i) * n;
                break;
            }
            for (int j = 0; j < n; j++) {
                if (A[i][j] < 0) {
                    System.out.println("加入" + (n - j));
                    res += n - j;
                    break;
                }
            }
        }
        return res;
    }
}
