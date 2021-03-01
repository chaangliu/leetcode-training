package dp;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 * 20190807
 */
class NumArray {
    /**
     * 题意：给出一个int数组，求i到j之间的sum。
     * 这题很好但是我猜由于有个corner case(nums是null)导致很多人WA所以很多人点了dislike
     * 一开始我想用二维dp，但是MLE了，然后自然而然想到sum[i, j] = sum[0,j] - sum[0, i-1]这种做法
     */
    int[] dp;

    public NumArray(int[] nums) {
        dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) { // 注意这儿如果从i = 1开始需要初始化dp[0]
            dp[i] = (i > 0 ? dp[i - 1] : 0) + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return dp[j] - (i > 0 ? dp[i - 1] : 0);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */