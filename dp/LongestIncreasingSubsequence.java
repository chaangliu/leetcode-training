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
     * dp[i]表示[0,i]范围的LIS长度，但是一定要包含第i个元素。
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     * 之所以必须包含第i个元素，是因为每次新增加的数是和nums[i](代码中是j)对比，比如1，2，0，..，4；4>0没有用，必须还要确定0就包含在了LIS里。
     * 这也是为什么用了一个max，保存每趟最优值，因为dp[dp.length - 1]保存的不一定是最大值，毕竟我们不一定要nums[nums.length - 1]
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;//don't miss this
            for (int j = 0; j < i; j++) {
                dp[i] = nums[i] > nums[j] ? Math.max(dp[j] + 1, dp[i]) : dp[i];
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
     * 思路是，遍历nums，对于每个数字，找到第一个比它大的数字的index，替换掉，这样就能得到一个更为平缓的slope。
     * 关键在于，要定义一个len作为binary search的end，因为这个dp数组一开始都是0，只有前面几个不是0，不符合单调性，你会发现lo = mid+1会把你带到数组的结尾。
     * 相似题目: cc150的马戏团人塔。
     * 例如，对于case：
     * [10, 9, 2, 5, 3, 7, 101, 18],
     * 每次搜索后的结果：
     * 10, 0, 0, 0, 0, 0, 0, 0,
     * 9, 0, 0, 0, 0, 0, 0, 0,
     * 2, 0, 0, 0, 0, 0, 0, 0,
     * 2, 5, 0, 0, 0, 0, 0, 0,
     * 2, 3, 0, 0, 0, 0, 0, 0,
     * 2, 3, 7, 0, 0, 0, 0, 0,
     * 2, 3, 7, 101, 0, 0, 0, 0,
     * 2, 3, 7, 18, 0, 0, 0, 0,
     */
    public int lengthOfLIS_DP_BS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length]; // dp[i]记录长度为i的LIS的结尾的最小数字
        int len = 0; // 当前LIS的最大长度
        // dp[0] = nums[0];
        for (int num : nums) {
            int i = lower_bound(dp, num, len); // 第一个 >= num的数字在dp中的index，case: [0,0..], num=1 => 返回0
            dp[i] = num;
            if (i == len) len++;
            // for (int j : dp) System.out.print(j + ", ");
            // System.out.println();
        }
        return len;
    }

    /**
     * 返回第一个>=target的数的index
     **/
    private int lower_bound(int[] A, int target, int hi) {
        int lo = 0;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (A[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    public static void main(String args[]) {
        new LongestIncreasingSubsequence().lengthOfLIS_DP_BS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }
}
