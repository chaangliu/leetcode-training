package array;

import java.util.HashSet;

/**
 * There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. Initially, all the bulbs are turned off.
 * At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb change color to blue only if it is on and all the previous bulbs (to the left) are turned on too.
 * Return the number of moments in which all turned on bulbs are blue.
 * Example 1:
 * Input: light = [2,1,3,5,4]
 * Output: 3
 * Explanation: All bulbs turned on, are blue at the moment 1, 2 and 4.
 * Example 2:
 * Input: light = [3,2,4,1,5]
 * Output: 2
 * Explanation: All bulbs turned on, are blue at the moment 3, and 4 (index-0).
 * Example 3:
 *
 * Input: light = [4,1,2,3]
 * Output: 1
 * Explanation: All bulbs turned on, are blue at the moment 3 (index-0).
 * Bulb 4th changes to blue at the moment 3.
 * Example 4:
 *
 * Input: light = [2,1,4,3,6,5]
 * Output: 3
 * Example 5:
 *
 * Input: light = [1,2,3,4,5,6]
 * Output: 6
 * Constraints:
 * n == light.length
 * 1 <= n <= 5 * 10^4
 * light is a permutation of  [1, 2, ..., n]
 * 20200308
 */
public class BulbSwitcherIII {
    /**
     * 题意：给你每盏灯点亮的时刻，如果这盏灯亮了，它左边所有灯都亮了，那左边所有灯就变蓝。问最早什么时候都变蓝。
     */
    public int numTimesAllBlue(int[] light) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>(); // lighted
        int l = Integer.MAX_VALUE, r = 0;
        for (int i = 0; i < light.length; i++) {
            int cur = light[i];
            set.add(cur);
            l = Math.min(l, cur);
            r = Math.max(r, cur);
            System.out.println("l, r, size == " + l + ", " + r + ", " + set.size());
            if (l != 1) continue;
            if (r - l + 1 != set.size()) continue;
            res++;
        }
        return res;
    }

    /**
     * lee的O(1) space做法..记录最右的灯泡位置。感觉他总能把问题看得很透彻。
     * 这种思路很像MaxChunksToMakeSorted那题。
     */
    public int numTimesAllBlue_(int[] A) {
        int right = 0, res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            right = Math.max(right, A[i]);
            if (right == i + 1) ++res;
        }
        return res;
    }
}
