package jianzhioffer;


/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 * leetcode题号：371. Sum of Two Integers
 */
public class AddUsingBitManipulation {
    /**
     * 3 ^2        =>  001
     * (3&2)<<1    =>  100
     */
    public int Add(int num1, int num2) {
        while (num2 != 0) {//进位==0的时候停止，表示无法再改变num1了。 可以是负数。
            System.out.println(num2);
            //不进位计算各位的和，对于二进制就是异或
            int sum = num1 ^ num2;
            //各位相与，然后左移一位，表示哪些位需要进位，下一轮处理
            int carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    public static void main(String args[]) {
        new AddUsingBitManipulation().Add(3, 2);
    }

}
