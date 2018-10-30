package jianzhioffer;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class RectCover {
    //dp [n] = dp[n-1] + dp[n - 2]；同样是斐波那契数列。
    //可以这么想，第一次摆放一块 2*1 的小矩阵，则摆放方法总共为f(target - 1)；第一次摆放一块1*2的小矩阵，则摆放方法总共为f(target-2)
    //**注意，这里最好用滚动数组**

//    public int RectCover(int target) {
//        if (target <= 0) return 0;
//        int[] dp = new int[target + 1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for (int i = 2; i < target + 1; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//        return dp[target];
//    }

    //dp - 滚动数组
    public int RectCover(int target) {
        if (target <= 0) return 0;
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
