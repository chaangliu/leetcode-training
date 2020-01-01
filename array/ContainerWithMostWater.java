package array;

/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * Created by DrunkPiano on 2016/12/7.
 * 20200101 review
 */

public class ContainerWithMostWater {
    /**
     * 题意：给你一些木板，这些木板中能盛的水取决于最外侧两个木板的最小高度*木板间隔。问最大能盛放的水的体积。
     * 这题O(n^2)会超时，正确做法是two pointers, 不用想得太复杂，每次把短的那个pointer往里面移动就行了。
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, res = 0;
        while (l < r) {
            res = Math.max(res, (r - l) * Math.min(height[l], height[r]));//这个要放在l++r--的前面，考虑[1,1]的case
            if (height[l] < height[r]) {
                l++;
            } else r--;
        }
        return res;
    }
}
