package dp.dfswithmemo;

public class PaintHouseIII {
    /**
     * 题意：给你一排房子，和涂成某种颜色的价格，让你涂成target个社区。社区的概念是，连续相同颜色的房子就是一个社区。问最小花费。
     * 解法：拿到之后我的思路是，前i个房子涂成target种颜色的最小花费，top down，从最后一个房子开始计算就行了。
     * 但是这种思路不对，首先，少了一个维度，就是上一个房子的颜色。另外，递归不应该从m-1开始，因为你需要第i+1个房子的状态。
     * 所以这题应该让dfs的i（房子序号）从0开始，返回上一个房子的颜色是prevColor，且i后面所有的房子涂成target种颜色的最小花费。
     * 或者也可以模拟从后往前涂色，dfs返回后一个颜色是prevColor，前面[0,i-1]个房子涂成target种颜色需要的最小花费。
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int res = dfs(houses, cost, m, n, 0, target, 0, new Integer[m + 1][target + 1][n + 1]);
        return res == 100000 ? -1 : res;
    }

    /**
     * 返回：上一个房子的颜色是prevColor，且i后面所有的房子涂成target种颜色的最小花费。
     **/
    private int dfs(int[] houses, int[][] cost, int m, int n, int i, int target, int prevColor, Integer[][][] memo) {
        if (i >= m || target < 0) return target == 0 ? 0 : 100000; // 只有target == 0才合法
        if (memo[i][target][prevColor] != null) return memo[i][target][prevColor];
        if (houses[i] != 0) return dfs(houses, cost, m, n, i + 1, target - (houses[i] == prevColor ? 0 : 1), houses[i], memo);
        int res = 100000;// 这儿不能写成Integer.MAX_VALUE，否则会越界
        for (int c = 1; c <= n; c++) {
            // 先涂当前的房子，后面的房子交给dfs去计算
            res = Math.min(res, cost[i][c - 1] + dfs(houses, cost, m, n, i + 1, target - (c == prevColor ? 0 : 1), c, memo));
        }
        return memo[i][target][prevColor] = res;
    }


    /**
     * 从后往前涂色的写法
     */
    public int minCost_(int[] houses, int[][] cost, int m, int n, int target) {
        int res = dfs_(houses, cost, m, n, m - 1, target, 0, new Integer[m + 1][target + 1][n + 1]);
        return res == 100000 ? -1 : res;
    }

    private int dfs_(int[] houses, int[][] cost, int m, int n, int i, int target, int prevColor, Integer[][][] memo) {
        if (i < 0 || target < 0) return target == 0 ? 0 : 100000;
        if (memo[i][target][prevColor] != null) return memo[i][target][prevColor];
        if (houses[i] != 0) return dfs_(houses, cost, m, n, i - 1, target - (houses[i] == prevColor ? 0 : 1), houses[i], memo);
        int res = 100000;
        for (int c = 1; c <= n; c++) {
            // 先涂当前的房子，前面的房子交给dfs去计算
            res = Math.min(res, cost[i][c - 1] + dfs_(houses, cost, m, n, i - 1, target - (c == prevColor ? 0 : 1), c, memo));
        }
        return memo[i][target][prevColor] = res;
    }
}
