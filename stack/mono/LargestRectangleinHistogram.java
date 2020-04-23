package stack.mono;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 20200423
 */
public class LargestRectangleinHistogram {
    /**
     * 题意：找出最大面积的长方形。
     * 解法：单调栈。
     * 以单调增为例，单调栈的性质是：
     * 1. 当你遇到一个比栈顶元素小的元素的时候，出栈的元素(out)右边第一个比它小的元素一定是入栈元素(in);
     * 2. 因为是单调增，所以out出栈之后、in入栈之前，栈顶元素一定是out左边第一个比它小的元素。
     * 经过1和2我们找到了一个区间[left + 1, right - 1]，在这个区间内，out一定是短板，所以就可以计算这个区间*heigth[out]得到面积
     * 这个讲解非常好：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/84-by-ikaruga/
     **/
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

    /**
     * 或者，不重新申请一个数组
     */
    public int largestRectangleArea_(int[] h) {
        Stack<Integer> s = new Stack<>();
        int res = 0;
        s.push(-1);
        for (int i = 0; i <= h.length; i++) {
            int in = i == h.length ? 0 : h[i];
            while (s.empty() || s.peek() >= 0 && h[s.peek()] > in) {
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
