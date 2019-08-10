package array.twopointers;

/**
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 * <p>
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 * <p>
 * Given an array A of integers, return the length of the longest mountain.
 * <p>
 * Return 0 if there is no mountain.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 * <p>
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 * Note:
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * Follow up:
 * <p>
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 * 20190810
 * ------------------------------------------------------------------------------------------
 * Updated list of problems that involved 1 or 2 passes from left to right/right to left:
 * 53 Maximum Subarray
 * 121 Best Time to Buy and Sell Stock
 * 152 Maximum Product Subarray
 * 238 Product of Array Except Self
 * 739 Daily Temperatures
 * 769 Max Chunks to Make Sorted
 * 770 Max Chunks to Make Sorted II
 * 821 Shortest Distance to a Character
 * 845 Longest Mountain in Array
 */
public class LongestMountainInArray {
    /**
     * 类似926题，可以说是2 pointers或dp。
     * 分别从左往右和从右往左记录A[i]为止(必须包含A[i]在内)最长的上坡路
     * 那么最长的山峰就是l[i - 1] + r[i]中最大的那个
     **/
    public int longestMountain(int[] A) {
        int len = A.length;
        if (len <= 2) return 0;
        int[] l = new int[len], r = new int[len];
        l[0] = 1;
        r[len - 1] = 1;
        for (int i = 1; i < len; i++) {
            l[i] = A[i - 1] < A[i] ? l[i - 1] + 1 : 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            r[i] = A[i + 1] < A[i] ? r[i + 1] + 1 : 1;
        }
        int res = 0;
        for (int i = 1; i <= len - 2; i++) {
            //这边corner case要注意，比如[1,2,3],[1,0,1]这种。
            res = Math.max(res, A[i] > A[i - 1] && A[i] > A[i + 1] ? l[i] + r[i + 1] : 0);
        }
        return res;
    }

    /**
     * O(1)space, one pass的解法，跟上面的思路有点不一样，
     * 就是分别记录上坡下坡长度，如果来到了谷底的下一个位置，就把上坡下坡长度重置为0
     * （我一开始也觉得能这么做，但是感觉写起来有点麻烦就用了上面的解法- -！
     */
    public int longestMountain_ONEPASS(int[] A) {
        int res = 0, up = 0, down = 0;
        for (int i = 1; i < A.length; ++i) {
            if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) up = down = 0;
            if (A[i - 1] < A[i]) up++;
            if (A[i - 1] > A[i]) down++;
            if (up > 0 && down > 0 && up + down + 1 > res) res = up + down + 1;
        }
        return res;
    }
}
