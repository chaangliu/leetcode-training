package jianzhioffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class JumpFloor {
    //dp[i] = dp[i - 1] + dp[i - 2]
    //**注意，这里最好用滚动数组**

//    public int JumpFloor(int target) {
//        if (target < 1) return 1;
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for (int i = 2; i < target + 1; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[target];
//    }


    //dp - 滚动数组
    public int JumpFloor(int target) {
        if (target <= 0) return 1;
        int tmp0 = 1;
        int tmp1 = 1;
        int tmp2 = 1;
        for (int i = 2; i < target + 1; i++) {
            tmp2 = tmp1 + tmp0;
            tmp0 = tmp1;
            tmp1 = tmp2;
        }
        return tmp2;
    }
}
