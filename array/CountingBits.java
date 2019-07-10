package array;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * <p>
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/4/10.
 */

public class CountingBits {
    /**
     * DP + Math
     */
    public int[] countBits(int num) {
        int dp[] = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i / 2] + i % 2;//这里也可以写成i >> 1，理解成右移1位；或者i & (i-1)，理解为把最后一位赋0
        }
        return dp;
    }
}
