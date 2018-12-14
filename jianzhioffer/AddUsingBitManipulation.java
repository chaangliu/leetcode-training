package jianzhioffer;


/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class AddUsingBitManipulation {
    //用位运算。5(0101) + 7(0111)为例。 水过。
    public int Add(int num1, int num2) {
        while (num2 != 0) {//进位==0的时候停止。 可以是负数。
            System.out.println(num2);
            //不进位计算各位的和，对于二进制就是异或 -> 1010
            int sum = num1 ^ num2;
            //各位相与
            int carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    public static void main(String args[]) {
        new AddUsingBitManipulation().Add(5, 7);
    }

}
