package math;

public class PatchingArray {
    /**
     * 题意：给你一个数组nums,和一个数字n，从[1,n]中选择一些整数插入到数组里，要求子数组的和能够覆盖[1,n]。
     * 解法：math，摘自https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-tan-xin-suan-fa-b4bwr/
     */
    public int minPatches(int[] nums, int n) {
        //累加的总和
        long total = 1;
        //需要补充的数字个数
        int count = 0;
        //访问的数组下标索引
        int index = 0;
        while (total <= n) {
            if (index < nums.length && nums[index] <= total) {
                //如果数组能组成的数字范围是[1,total)，那么加上nums[index]
                //就变成了[1,total)U[nums[index],total+nums[index])
                //结果就是[1,total+nums[index])
                total += nums[index++];
            } else {
                //添加一个新数字，并且count加1
                total <<= 1;
                count++;
            }
        }
        return count;
    }
}
