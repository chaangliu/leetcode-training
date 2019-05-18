package array;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * Created by DrunkPiano on 2017/4/10.
 * 20190518review
 */

public class HouseRobber {

    /**
     * dp, 方程dp[i] = max(dp[i - 2] + nums[i] , dp[i - 1])
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // maximum amount till the ith house
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

//    public int rob(int[] nums) {
//        if (nums.length == 0) return 0;
//        int local = nums[0];
//        int global = 0;
//        boolean visited[] = new boolean[nums.length];
//        visited[0] = true ;
//        for (int i = 1; i < nums.length; i++) {
//            if (!visited[i - 1]) {
//                local = local + nums[i];
//                visited[i] = true;
//                global = Math.max(global, local);
//            }
//            //2,4,1
//        }
//        return global;
//    }
}
