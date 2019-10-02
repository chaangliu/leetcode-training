package bitmanipulation;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 00000010100101000001111010011100
 * Output: 00111001011110000010100101000000
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 * <p>
 * Input: 11111111111111111111111111111101
 * Output: 10111111111111111111111111111111
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10101111110010110010011101101001.
 * <p>
 * <p>
 * Note:
 * <p>
 * Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 * 20191002
 */
public class ReverseBits {
    /**
     * 这题题意是把输入的整数按位reverse一下输出，java的话要把n当成无符号数，也就是右移要用>>>
     * 简单方式是对32位都依次reverse一下。
     * 还有种做法，每次交换16/8/4/2/1长度的bit；感觉好难写，不具有代表性
     */
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;//res当前位与n的最低位相同
            n >>>= 1;//n 无符号右移
            if (i < 31) {//res的最后一位不要左移
                res <<= 1;
            }
        }
        return res;
    }
}
