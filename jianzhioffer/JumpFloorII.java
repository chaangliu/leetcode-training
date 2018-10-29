package jianzhioffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class JumpFloorII {
    //approach 1, 常规思路： dp[i] = dp[i - 1] + dp[i - 2] + .. dp[0]
    public int JumpFloorII(int target) {
        int dp[] = new int[target + 1];
        if (target < 1) return 1;
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < target + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] += dp[j];
            }
        }
        return dp[target];
    }

    //approach 2, math
//    https://www.nowcoder.com/questionTerminal/22243d016f6b47f2a6928b4313c85387
//    因为n级台阶，第一步有n种跳法：跳1级、跳2级、到跳n级
//    跳1级，剩下n-1级，则剩下跳法是f(n-1)
//    跳2级，剩下n-2级，则剩下跳法是f(n-2)
//    所以f(n)=f(n-1)+f(n-2)+...+f(1)
//    因为f(n-1)=f(n-2)+f(n-3)+...+f(1)
//    所以f(n)=2*f(n-1)
}
