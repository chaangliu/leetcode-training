package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Note: The algorithm should run in linear time and in O(1) space.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 * 20190719
 */
public class MajorityElementII {
    /**
     * 先回顾一下Majority Element I,
     * Boyer-Moore Majority Vote algorithm
     * one pass, 维护count的做法
     */
    public int majorityElementI(int[] num) {

        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) {
                count++;
            } else
                count--;

        }
        return major;
    }

    /**
     * Majority Element II,
     * 改版的Boyer-Moore Majority Vote algorithm，O(n)
     * 维护两个count
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) return res;
        int major1 = nums[0], major2 = -1, count1 = 1, count2 = 0;//count1 初始化为1，代表major1必定是nums[0]，后面优先覆盖major2
        for (int val : nums) {
            //[1]major1 或 major2的数量正在增加
            if (val == major1)
                count1++;
            else if (val == major2)//初始情况这个条件虽然成立，但是由于有else，所以优先累计count1
                count2++;
                //[2]major1 或 major2被第三个数抵消掉了，替换
            else if (count1 == 0) {
                major1 = val;
                count1++;
            } else if (count2 == 0) {
                major2 = val;
                count2++;
            }
            //[3]major1 或 major2正在被第三个数抵消
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int val : nums) {//最后还要检查一遍，不然对于[3,2]或者[3,2,3]这种过不了
            if (val == major1)
                count1++;
            else if (val == major2)
                count2++;
        }
        if (count1 > nums.length / 3) res.add(major1);
        if (count2 > nums.length / 3) res.add(major2);
        return res;
    }

    public static void main(String args[]) {
        new MajorityElementII().majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
    }
}
