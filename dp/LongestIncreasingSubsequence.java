package dp;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * Created by DrunkPiano on 2017/4/6.
 */

public class LongestIncreasingSubsequence {
    /**
     * 题意：求最长上升子序列长度。
     * Approach1. DP
     * Time/Space : O(n^2)
     * dp[i]表示[0,i]范围的LIS，但是一定要包含第i个元素。
     * 之所以必须包含第i个元素，是因为每次新增加的数是和nums[i](代码中是j)对比，比如1，2，0，..，4；4>0没有用，必须还要确定0就包含在了LIS里。
     * 这也是为什么用了一个max，保存每趟最优值，因为dp[dp.length - 1]保存的不一定是最大值，毕竟我们不一定要nums[nums.length - 1]
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int dp[] = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;//don't miss this
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {//这里不能写成:dp[i] = nums[i] > nums[j] ? Math.max(dp[j] + 1, dp[i]) : 1;
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * Approach2. Recursion with memo
     * Time/Space : O(n^2)
     */
    public int lengthOfLIS__RECURSION_WITH_MEMO(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }

    public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }


    /**
     * Approach3. DP with Binary Search
     * 这个不算是典型的DP吧，毕竟连转移方程都没有
     */
    public int lengthOfLIS_DP_BS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);//如果没找到，则返回插入位置
            }
            dp[i] = num;
            if (i == len) {
                len++;//如果在结尾插入了新的num, arr长度增加
            }
        }
        return len;
    }

    public static int binarySearch(int[] a, int fromIndex, int toIndex,
                                   int key) {
        return binarySearch0(a, fromIndex, toIndex, key);
    }

    // Like public version, but without range checks.
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static void main(String args[]) {
        int[] nums = {1, 3, 2, 5, 4};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS_DP_BS(nums));
    }
}
