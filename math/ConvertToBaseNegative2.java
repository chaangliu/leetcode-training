package math;

import java.util.Stack;

/**
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 * The returned string must have no leading zeroes, unless the string is "0".
 * Example 1:
 * Input: 2
 * Output: "110"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 2
 * Example 2:
 * <p>
 * Input: 3
 * Output: "111"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 * Example 3:
 * <p>
 * Input: 4
 * Output: "100"
 * Explantion: (-2) ^ 2 = 4
 * <p>
 * 20190331
 */
public class ConvertToBaseNegative2 {
    /**
     * 这题周赛百度了一下AC了。。Math题有一定思维难度
     * 参考：这道题显然就是一个十进制转化为-2进制的题目。我们可以想一想10进制如何转化为2进制的。
     * 那么我们也可以模拟10进制转化为-2进制的过程。就是一个数n/-2然后取其余数。但是有一点要注意，余数要为正的。
     * 所以我们要处理一下数据，把计算出来的余数取其绝对值，同时将这个数减去余数然后再除以-2的话就可以算出正确的结果啦。以下是ac的代码。
     * 原文：https://blog.csdn.net/qq_36924696/article/details/81233519
     */
    public String baseNeg2(int N) {
        if (N == 0) return "0";
        //余数
        int r;
        Stack<Integer> stack = new Stack<>();
        while (N != 0) {
            //N有可能是负数所以r有可能是负数
            r = Math.abs(N % -2);
            stack.push(r);
            //这里-r不能省去，因为N可能是负数，这样-r就会让N的绝对值变得更大(可能进位)
            N = (N - r) / (-2);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 10进制转2进制，跟2进制转10进制反过来，每次把最后一位取余，然后/2代表左移1位
     * 101 => 1 * 2^2 + 0 * 2^1 + 1 * 2^0
     */
    private int decimalToBinary(int n) {
        int res = 0;
        while (n > 0) {
            res = res * 10 + n % 2;
            //事实上这里等同于先减去余数(r)再/2，不过结果没区别所以省略掉-r的操作
            n /= 2;
        }
        return res;
    }


    /**
     * leetcode上比较fancy的解法，按位相与。先看转成二进制：
     */
    public String base2(int N) {
        StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);//判断当前位是否为1，也可理解为模2，模4，模8..(因为要不停右移1bit)
            N = N >> 1;
        }
        return res.length() > 0 ? res.reverse().toString() : "0";
    }

    //         原码           反码        补码         右移一位   结果（原码）
    //    -1 = 1000 0001，   1111 1110， 1111 1111，  1111 1111   -1
    //    -2 = 1000 0010，   1111 1101， 1111 1110，  1111 1111   -1
    //    -3 = 1000 0011，   1111 1100， 1111 1101，  1111 1110   -2
    //    -4 = 1000 0100，   1111 1011， 1111 1100，  1111 1110   -2
    //    -5 = 1000 0101，   1111 1010， 1111 1011，  1111 1101   -3
    //    -6 = 1000 0110，   1111 1001， 1111 1010，  1111 1101   -3
    public String baseNeg2__(int N) {
        StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);//判断当前位是否为1，也可理解为模2，模4，模8..(因为要不停右移1bit)
            N = -(N >> 1);
        }
        return res.length() > 0 ? res.reverse().toString() : "0";
    }
}
