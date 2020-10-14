package bitmanipulation;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 * <p>
 * Input: a = -2, b = 3
 * Output: 1
 * 20191009
 */
public class SumofTwoIntegers {
    /**
     * 题意:不用加减符号做加法。剑指offer有。
     * 做法是bit manipulation，以3+2为例，经过观察，发现加法可以拆解：先用xor做不进位的计算，然后用&计算哪些位数需要进位，直到没有进位。
     * 3 ^2        =>  001
     * (3&2)<<1    =>  100
     * 第二轮carry就是0了，没有进位，于是结束。
     */
    public int getSum(int num1, int num2) {
        while (num2 != 0) {//进位==0的时候停止，表示无法再改变num1了。 可以是负数。
            // 不进位计算各位的和，对于二进制就是异或
            int sum = num1 ^ num2;
            // 各位相与，然后左移一位（只有两个bit都是1的时候，它们左边的一位才需要+1），表示哪些位需要进位，下一轮处理
            int carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }
}
