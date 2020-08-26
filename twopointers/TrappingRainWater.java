package twopointers;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 20200108
 */
public class TrappingRainWater {
    /**
     * 题意：给你一些bar，问最多能困住多少水。google老题。
     * 解法：单调栈，遇到bar先判断下栈顶是不是比当前矮，如果是，那就找找左边界。
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
     **/
    public int trap(int[] height) {
        if (height == null) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop(); // 水池底部（必须平整，不然不好算面积）
                // 如果栈顶元素一直相等，那么全都pop出去，只留第一个。
                while (!stack.isEmpty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    // stackTop此时指向的是此次接住的雨水的左边界的位置。右边界是当前的柱体，即i。
                    // Math.min(height[stackTop], height[i]) 是左右柱子高度的min，减去height[curIdx]就是雨水的高度。
                    // i - stackTop - 1 是雨水的宽度。
                    res += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return res;
    }

    /**
     * 解法：two pointers，每次收缩短的那一边，这样能保证另一边能cover住。
     * 参考：https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
     */
    public int trap_(int[] h) {
        int lMax = 0, rMax = 0, res = 0, n = h.length;
        for (int l = 0, r = n - 1; l < r; ) {
            lMax = Math.max(lMax, h[l]); // 更新bar的高度
            rMax = Math.max(rMax, h[r]);
            if (lMax < rMax) {
                res += lMax - h[l];
                l++; // 这一步的逻辑是，短的bar向长的bar靠拢，这样总能保证另一侧是能被挡住的
            } else {
                res += rMax - h[r];
                r--;
            }
        }
        return res;
    }
}
