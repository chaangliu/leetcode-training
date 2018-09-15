package array;

/**
 * 2,3,-1,4 return 6
 * Created by DrunkPiano on 2017/4/11.
 */

public class MaximumProductArray {
    //dp[index][正负]
    //dp[i][0] = dp[i-1][0] * nums[i] (nums[i]>=0)    or    dp[i-1][1] * nums[i](nums[i]<0)
    //dp[i][1] = dp[i-1][1] * nums[i] (nums[i]>=0)    or    dp[i-1][0] * nums[i](nums[i]<0)
//    public int maxProduct(int[] nums) {
//        if (nums.length==0) return 0;
//        int dp[][] = new int[nums.length][2];
//        dp[0][0] = nums[0];
//        dp[0][1] = nums[0];
//        int res = nums[0] ;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] >= 0) {
//                dp[i][0] = dp[i - 1][0] * nums[i];
//                dp[i][1] = dp[i - 1][1] * nums[i];
//            } else {
//                dp[i][1] = dp[i - 1][1] * nums[i];
//                dp[i][0] = dp[i - 1][0] * nums[i];
//            }
//
//            dp[i][0] = Math.max(dp[i][0] , nums[i]);
//            dp[i][1] = Math.min(dp[i][0] , nums[i]);
//            res = Math.max(res,dp[i][0]);
//        }
//        return res;
//    }

    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int res = nums[0];

        //nums[i]>=0 ：
        //dpPos[i] = dpPos[i-1] * nums[i]   这里dpPos只存放positive max的值
        //dpNeg[i] = dpNeg[i-1] * nums[i]   这里dpMin只存放negative min的值
        //nums[i]<0 ：
        //dpNeg[i] = dpPos[i-1] * nums[i]   这里dpPos只存放positive max的值
        //dpPos[i] = dpNeg[i-1] * nums[i]   这里dpMin只存放negative min的值

        int maxPos = nums[0];
        int minNeg = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                maxPos = maxPos * nums[i];
                minNeg = minNeg * nums[i];
            } else {
                int minNegCopy = minNeg;
                minNeg = maxPos * nums[i];
                maxPos = minNegCopy * nums[i];
            }
            maxPos = Math.max(maxPos, nums[i]);
            minNeg = Math.min(minNeg, nums[i]);
            res = Math.max(maxPos, res);
        }
        return res;
    }

    public static void main(String args[]) {
        int a[] = {-4, -3};
        MaximumProductArray ins = new MaximumProductArray();
        ins.maxProduct(a);
    }

}
