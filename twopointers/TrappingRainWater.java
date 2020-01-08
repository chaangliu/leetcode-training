package twopointers;

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
     * 解法：two pointers，每次收缩短的那一边，这样能保证另一边能cover住。
     * 参考：https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
     **/
    public int trap(int[] h) {
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
