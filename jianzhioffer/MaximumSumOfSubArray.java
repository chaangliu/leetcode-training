package jianzhioffer;

/**
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class MaximumSumOfSubArray {
    //这题就是Leetcode53题。Maximum SubArray。
    //状态转移方程，注意判断的是谁的大小。考虑从[任意起点]到必须包含当前数字的情况，
    //dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i]
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        int max = array[0];
        int[] dp = new int[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + array[i] : array[i];
            max = dp[i] > max ? dp[i] : max;
        }
        return max;
    }

    //滚动数组
    public int FindGreatestSumOfSubArray2(int[] array) {
        if (array == null || array.length == 0) return 0;
        int max = array[0];
        int prev = array[0], cur;
        for (int i = 1; i < array.length; i++) {
            cur = prev > 0 ? prev + array[i] : array[i];
            max = cur > max ? cur : max;
            prev = cur;//不要忘记
        }
        return max;
    }

    public static void main(String args[]) {
        int[] nums = {6, -3, -2, 7, -15, 1, 2, 2};
        new MaximumSumOfSubArray().FindGreatestSumOfSubArray2(nums);
    }
}
