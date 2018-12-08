package jianzhioffer;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 这题是leetcode 264. Ugly Number II。
 */
public class NthUglyNumber {

    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int dp[] = new int[index];
        dp[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < index; i++) {
            int min = Math.min(factor2, Math.min(factor3, factor5));
            dp[i] = min;
            if (min == factor2) {
                factor2 = 2 * dp[++index2]; // 1 * 2, 2* 2, 3 * 2...
            }
            //注意这里不能加else......
            if (min == factor3) {
                factor3 = 3 * dp[++index3];
            }
            if (min == factor5) {
                factor5 = 5 * dp[++index5];
            }
        }
        return dp[index - 1];
    }


    //brute force: TLE.
    public int GetUglyNumber_Solution_TLE(int index) {
        if (index <= 0) return -1;
        if (index == 1) return 1;
        int res = 0;
        while (index != 0) {
            res++;
            if (isUglyNumber(res)) index--;
        }
        return res;
    }

    private boolean isUglyNumber(int num) {
        if (num <= 0) return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }
}
