package jianzhioffer;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 这题是leetcode 264. Ugly Number II。
 */
public class NthUglyNumber {

    //brute force: TLE.
    public int GetUglyNumber_Solution(int index) {
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
