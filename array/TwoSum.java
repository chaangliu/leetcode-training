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
 */

public class TwoSum {
    static int[] result = new int[2];

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, nums[i]};
                }
            }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSumHashMapVer(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSumWay3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum2018(int[] nums, int target) {
        if (nums == null || nums.length == 0) return result;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null && i != map.get(target - nums[i])) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            }
        }
        return result;
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
