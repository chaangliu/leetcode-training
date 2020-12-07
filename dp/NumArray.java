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
    int dp[];
    int len;

    public (int[] nums) {
        if (nums == null || nums.length == 0) return;
        len = nums.length;
        dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? dp[j] : dp[j] - dp[i - 1];
    }
    /**
     * 二维dp，MLE
     **/
    //     int dp [][];
    //     int len = 0;
    //     public NumArray(int[] nums) {
    //         len = nums.length;
    //         dp = new int[len][len];
    //         for(int j = 0 ; j < len ; j ++){
    //             dp[j][j] = nums[j];
    //             for(int i = 0 ; i < j;  i ++){
    //                 if(i == 0){
    //                     dp[i][j] = dp[i][j - 1] + nums[j];
    //                 }else{
    //                     dp[i][j] = dp[i - 1][j] - nums[i - 1];   
    //                 }
    //             }   
    //         }
    //     }

    //     public int sumRange(int i, int j) {
    //         return dp[i][j];
    //     }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */