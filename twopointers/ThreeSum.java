package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * Created by DrunkPiano on 2016/12/8.
 * 20191226 review
 */

public class ThreeSum {
    /**
     * 题意：找出三数之和等于0，任意顺序，不能有重复。
     * 有两种思路，
     * 第一种是模仿2sum，先固定一个pivot，然后后两个用2sum的方法找；缺点是需要用一个Set的空间。
     * 第二种比较好，先排序然后双指针；反正总体要达到O(n^2)所以先排序不影响复杂度。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;
        int n = nums.length;
        HashSet<String> visited = new HashSet<>();//已犯错误：忘记去重，所以后来加上一个set，总体复杂度仍然是O(n^2)，但跑起来速度较慢
        for (int i = 0; i < n; i++) {
            int target = 0 - nums[i];
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                if (set.contains(target - nums[j])) {
                    List<Integer> item = Arrays.asList(nums[i], target - nums[j], nums[j]);
                    Collections.sort(item);
                    if (visited.add(item.toString()))
                        res.add(Arrays.asList(nums[i], target - nums[j], nums[j]));
                } else {
                    set.add(nums[j]);
                }
            }
        }
        return res;
    }

    /**
     * Approach2 two pointers
     * 以一个pivot为基准，从前往后扫描，在后面找两个合适的数；找两个数的的方法就是sorted 2 sum的方案，难点是去重
     */
    public List<List<Integer>> threeSum2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {//pivot后面至少有两个数
            if (i != 0 && num[i] == num[i - 1]) continue; //已犯错误: 这一行的意义是去重[-1,-1,0,1]这种case
            int low = i + 1, high = num.length - 1;
            //在pivot后面寻找两个数
            while (low < high) {
                if (num[low] + num[high] + num[i] == 0) {
                    res.add(Arrays.asList(num[i], num[low], num[high]));
                    //下面几行也是为了防止重复的set
                    while (low < high && num[low] == num[low + 1]) low++;//low < high 重要，否则[0,0,0]过不了
                    while (low < high && num[high] == num[high - 1]) high--;
                    low++;
                    high--;
                } else if (num[low] + num[high] + num[i] < 0) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }

    public static void main(String args[]) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> list = new ThreeSum().threeSum2(nums);
        System.out.println("result--->" + list.toString());
    }
}
