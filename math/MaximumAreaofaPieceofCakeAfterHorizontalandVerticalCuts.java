package math;

import java.util.Arrays;

/**
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
 * 20200531
 */
public class MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts {
    /**
     * 题意：切蛋糕，给你蛋糕宽高、横竖刀的位置，问最大的一块是多大。
     * 解法：画一下就知道，O(n)就可以求出。但是这题我浪费了好久，因为有个很大的case一直wa，最后别人提示才知道忘了mod 1e9 + 7.
     */
    public int maxArea(int h, int w, int[] hCuts, int[] vCuts) {
        Arrays.sort(hCuts);
        Arrays.sort(vCuts);
        int max_h = Math.max(hCuts[0], h - hCuts[hCuts.length - 1]);
        int max_v = Math.max(vCuts[0], w - vCuts[vCuts.length - 1]);
        for (int i = 0; i < hCuts.length - 1; ++i)
            max_h = Math.max(max_h, hCuts[i + 1] - hCuts[i]);
        for (int i = 0; i < vCuts.length - 1; ++i)
            max_v = Math.max(max_v, vCuts[i + 1] - vCuts[i]);
        return (int) ((long) max_h * max_v % 1000000007);
    }
}
