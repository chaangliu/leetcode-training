package bitmanipulation;

public class MinimumNumbersofFunctionCallstoMakeTargetArray {
    /**
     * 题意：给你一个与 nums 大小相同且初始值全为 0 的数组 arr ，你每次可以把某一位数字+1，或者把所有数字*2；问最小需要多少次才能得到nums 。
     * 观察：对于所有数字的二进制，1一定是要单独加上去的，因为同时*2只会左移，末尾补0.
     * 所以统计1bit的数量，加上最多需要左移多少次。
     **/
    public int minOperations(int[] nums) {
        int ones = 0, maxLen = 0;
        for (int i : nums) {
            int len = 0;
            while (i > 0) {
                if ((i & 1) == 1) ones++;
                i >>= 1;
                len++;
            }
            maxLen = Math.max(len, maxLen);
        }
        return ones + maxLen - 1; // maxLen需要减去1；因为最多需要移动的次数就是maxLen-1；100，maxLen是3但只需要从1左移两次。
    }
}
