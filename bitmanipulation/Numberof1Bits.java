package bitmanipulation;

/**
 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
 * Example 1:
 * <p>
 * Input: 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 * <p>
 * Input: 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 * <p>
 * Input: 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 * Note:
 * <p>
 * Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above the input represents the signed integer -3.
 * 20191008
 */
public class Numberof1Bits {
    /**
     * 题意:求n的二进制中有多少个数字1.
     * 剑指offer15题。一个常见的技巧， n & n - 1去掉最右边的1。
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & n - 1;//1011 & 1010 = 1010,这个操作每次把最右边的1变成0
            res++;
        }
        return res;
    }

    /**
     * naive解法，bit shifting，复杂度高
     */
    public static int hammingWeight_(int n) {
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }
}
