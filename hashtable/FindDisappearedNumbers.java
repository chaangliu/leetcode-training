package hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 */
public class FindDisappearedNumbers {
    /**
     * 题意：给你一个长度为n的数组，里面所有的数字都在[1,n]内，但是有些没出现过。请找出那些没出现过的。
     * 按理说如果要时间达到O(n)的话需要一个额外的set，但是正好可以用原数组实现。
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i : nums) {
            nums[(i - 1) % nums.length] += nums.length; // not (i % nums.length) - 1
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length) res.add(i + 1);
        }
        return res;
    }
}
