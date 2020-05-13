package dp;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 20200513
 */
public class MaximalRectangle {
    /**
     * 题意：一个只包含0和1的二维数组，问1构成的最大长方形的面积是多少。
     * 解法：可以把这题看成largestRectangleArea的延伸，计算以每一行开始为底看做一个柱状图。柱状图的中间不能断，所以先用一个dp数组处理一下。
     */
    public int maximalRectangle(char[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;
        int m = A.length, n = A[0].length, res = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j] = i == 0 ? A[i][j] - '0' : A[i][j] == '1' ? dp[j] + 1 : 0;// 滚动数组
            }
            res = Math.max(res, largestRectangleArea(dp));
        }
        return res;
    }

    /**
     * 以下代码借助84题largestRectangleArea。
     */
    public int largestRectangleArea(int[] heights) {
        int[] h = new int[heights.length + 1];
        for (int i = 0; i < heights.length; i++) h[i] = heights[i]; // 为了最后让所有stack中的元素都出列，我们往heights尾部push一个0
        Stack<Integer> s = new Stack<>();
        int res = 0;
        s.push(-1); // 为了方便计算区间，先往stack中push一个-1
        for (int i = 0; i < h.length; i++) {
            while (s.empty() || s.peek() >= 0 && h[s.peek()] > h[i]) {
                int out = s.pop();
                int right = i - 1;
                int left = s.peek() + 1;
                res = Math.max(res, (right - left + 1) * h[out]);
            }
            s.push(i);
        }
        return res;
    }
}
