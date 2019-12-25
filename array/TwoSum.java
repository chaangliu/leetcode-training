package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2 sum.
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * Created by DrunkPiano on 2016/12/4.
 * 20191225
 */

public class TwoSum {
    /**
     * 题意：找两个数的index，相加等于target。
     * 解法：one pass，用一个map保存已有的数字和index。
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    //注意这道题不能用two pointers，因为用two pointers必定要排序（首先就已经慢了。。），但是这题要求的是index。
//    public int[] twoSum_(int[] nums, int target) {
//        if (nums == null || nums.length == 0) return new int[]{};
//        Arrays.sort(nums);
//        int low = 0, high = nums.length - 1;
//        while (low < high) {
//            int sum = nums[low] + nums[high];
//            if (sum < target) {
//                low++;
//            } else if (sum > target) {
//                high--;
//            } else {
//                return new int[]{nums[low], nums[high]};
//            }
//        }
//        return new int[]{};
//    }

    public static void main(String args[]) {
        int[] nums = {3, 2, 4};
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        result = twoSum(nums, target);
//        int []result = new TwoSum().twoSum_(nums, 9);
        System.out.println(result[0] + " , " + result[1]);
    }
}
