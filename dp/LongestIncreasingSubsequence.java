package dp;

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
//[10, 9, 2, 5, 3, 7, 101, 18]
//[1,  1, 1, 2, 2, 3, 4, 4]

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int dp[] = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            //max不能是全局的
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    //这里不能就开始改变dp[i]，否则下一次循环就乱了
                    max = Math.max(dp[j] + 1, max);
                }
            }

            dp[i] = max;

            res = Math.max(dp[i] , res);

        }
        return res;
    }


    public static void main(String args[]){
//        int [] nums  = {10, 9, 2, 5, 3, 7, 101, 18};
        int [] nums  = {1,3,6,7,9,4,10,5,6};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));

    }
}
