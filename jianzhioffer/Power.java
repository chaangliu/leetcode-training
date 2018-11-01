package jianzhioffer;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Power {
    //approach 1. straightforward, time = O(n)
//    public double Power(double base, int exponent) {
//        double res = 1;
//        if (exponent < 0 && base == 0) {
//            return Integer.MAX_VALUE;
//        } else {
//            for (int i = 0; i < Math.abs(exponent); i++) {
//                res *= base;
//            }
//            return exponent > 0 ? res : 1 / res;
//        }
//    }

    //递归：n为偶数，a^n=a^n/2*a^n/2;n为奇数，a^n=（a^（n-1）/2）*（a^（n-1/2））*a, time = O(log n)
    //a. 我的代码（AC）
    public double Power(double base, int exponent) throws Exception {
        int n = Math.abs(exponent);
        if (n == 0) return 1;
        if (n == 1) return base;
        if (n < 0 && base == 0) throw new Exception("分母为0");
        double res = (n & 1) == 0 ? Power(base, n / 2) * Power(base, n / 2) //n/2可以改写成n>>1
                : Power(base, (n - 1) / 2) * Power(base, (n - 1) / 2) * base;
        return exponent > 0 ? res : 1 / res;
    }

    //b. 网上的代码
    public class Solution {
        public double Power(double base, int exponent) {
            int n = Math.abs(exponent);
            if (n == 0)
                return 1;
            if (n == 1)
                return base;
            double result = Power(base, n >> 1);//n>>1也就是n / 2
            result *= result;
            if ((n & 1) == 1)
                result *= base;//奇数的话，额外多乘一次
            if (exponent < 0)
                result = 1 / result;
            return result;
        }
    }
}
