package dp.dfswithmemo;

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
 * In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
 * Example 1:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * Example 2:
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * Example 3:
 * <p>
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * Follow up:
 * Can you do it in O(n) time?
 * 20190821
 */
public class WiggleSubsequence {
    /**
     * 题意：求最长的wiggle子序列的长度。
     * approach1 dfs with memo，回溯，如果不加memo：复杂度O(n!)，调用了n!次calculate，会TLE
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        Integer[][] memo = new Integer[nums.length][2];
        return 1 + Math.max(calculate(nums, 0, true, memo), calculate(nums, 0, false, memo));
    }

    private int calculate(int[] nums, int start, boolean isUp, Integer[][] memo) {
        if (memo[start][isUp ? 0 : 1] != null) return memo[start][isUp ? 0 : 1];
        int maxcount = 0;
        for (int i = start + 1; i < nums.length; i++) {// subsequence, 所以for循环+递归
            if ((isUp && nums[i] > nums[start]) || (!isUp && nums[i] < nums[start]))
                maxcount = Math.max(maxcount, 1 + calculate(nums, i, !isUp, memo));
        }
        memo[start][isUp ? 0 : 1] = maxcount;
        return maxcount;
    }

    /**
     * approach2 O(n^2)dp，反而是最容易理解的一个
     */
    public int wiggleMaxLength__2D(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] up = new int[nums.length];//up[i]:以i结尾的上升的wiggle序列最大长度
        int[] down = new int[nums.length];//down[i]:以i结尾的下降的wiggle序列最大长度
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);//在i处上升了，并且在j之前是下降的
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    /**
     * approach3 O(n)dp，
     * 与上面不同的是，这里up/down保存的不是end with i的wiggle序列的最大长度，
     * 而是以up/down中间某个位置结尾的wiggle序列最大长度
     * 可以进一步优化成O(1)空间；
     * 另外，有greedy解法
     */
    public int wiggleMaxLength__DP_1D(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}
