package math;

public class FindNthDigit {
    class Solution {
        /**
         * 题意：数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。求第n位对应的数字。
         * 解法：找规律；0~9有10个，10~99有90个，100~999有900个..
         * 分三步：
         * 1. 找到这个数字对应的数是几位数，用 size 表示；
         * 2. 确定这个对应的数在size位数中的次序count;
         * 3. 确定返回值是 target 中的哪个数字。
         * 参考：https://leetcode-cn.com/problems/nth-digit/solution/xiang-jie-zhao-gui-lu-by-z1m/
         **/
        public int findNthDigit(int n) {
            long num = n;
            long size = 1;
            long max = 9;
            while (n > 0) {
                // 第一步，找这个数字是几位数
                if (num - max * size > 0) {
                    num = num - max * size;
                    size++;
                    max = max * 10;
                } else {
                    long count = num / size; // 剩余的数字除以/size，得到数字处于size位数的第几个
                    long left = num % size; // 确定返回值是是target中的第几位数字，
                    if (left == 0) { // 如果 left = 0，表示目标数字是 number - 1 中的最后一个数字
                        return (int) (((long) Math.pow(10, size - 1) + count - 1) % 10);
                    } else {
                        return (int) (((long) Math.pow(10, size - 1) + count) / ((long) Math.pow(10, (size - left))) % 10);
                    }
                }
            }
            return 0;
        }
    }
}
