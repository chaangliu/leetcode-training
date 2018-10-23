package jianzhioffer;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Fibonacci {
    //approach 1 直接递归
//    public int Fibonacci(int n) {
//        if (n == 0) return 0;
//        if (n == 1) return 1;
//        return Fibonacci(n - 1) + Fibonacci(n - 2);
//    }

    //approach 2 dp。
    //（它的动态转移方程，和count stairs、青蛙跳台阶一样，dp[i] = dp[i - 1] + dp[i - 2]）
//    public int Fibonacci(int n) {
//        if (n < 0) return -1;
//        if (n == 0) return 0;//这个不要漏掉..不然会out of bounds
//        int[] dp = new int[n + 1];
//        dp[0] = 0;
//        dp[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[n];
//    }

    //approach 2 的改进，滚动数组
    public int Fibonacci(int n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        if (n == 1) return 1;
        int tmp0 = 0;//或者用长度2的数组
        int tmp1 = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = tmp0 + tmp1;
            tmp0 = tmp1;
            tmp1 = res;
        }
        return res;
    }
}
