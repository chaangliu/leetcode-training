package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum QuestionEditorial Solution  My Submissions
 * Total Accepted: 97288
 * Total Submissions: 381586
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * <p>
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note: The solution set must not contain duplicate quadruplets.
 * <p>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],id
 * idddasda
 * [-2,  0, 0, 2]
 * ]
 * Created by DrunkPiano on 2016/12/12.
 */

public class FourSum {
    /**
     * 题意：求4sum的解集。
     * 解法：没有捷径，在3sum外面加一层； 固定两个pivot，然后两个双指针移动。
     * 时间: O(n^3)
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list_result = new ArrayList<>();
        if (nums == null || nums.length < 4) return list_result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int low = j + 1;
                int high = nums.length - 1;
//                int sum = nums[i] + nums[j] + nums[low] + nums[high];
                while (low < high) {
                    List<Integer> list_cell = new ArrayList<>();
                    if (nums[i] + nums[j] + nums[low] + nums[high] == target) {
                        list_cell.add(nums[i]);
                        list_cell.add(nums[j]);
                        list_cell.add(nums[low]);
                        list_cell.add(nums[high]);
                        list_result.add(list_cell);
                        low++;
                        high--;
                        while (low < high && nums[low] == nums[low - 1]) low++;
                        while (low < high && nums[high] == nums[high + 1]) high--;
                        while (low + 1 < high && nums[low] == nums[low + 1]) low++;
                    } else if (nums[i] + nums[j] + nums[low] + nums[high] > target) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }
        return list_result;
    }


    /**
     * kSum 的通用解法
     */
    int len = 0;

    public List<List<Integer>> fourSum_(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (index >= len) {
            return res;
        }
        if (k == 2) {
            int i = index, j = len - 1;
            while (i < j) {
                //find a pair
                if (target - nums[i] == nums[j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(target - nums[i]);
                    res.add(temp);
                    //skip duplication
                    while (i < j && nums[i] == nums[i + 1]) i++;
                    while (i < j && nums[j - 1] == nums[j]) j--;
                    i++;
                    j--;
                    //move left bound
                } else if (target - nums[i] > nums[j]) {
                    i++;
                    //move right bound
                } else {
                    j--;
                }
            }
        } else {
            for (int i = index; i < len - k + 1; i++) {
                //use current number to reduce ksum into k-1sum
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k - 1, i + 1);
                if (temp != null) {
                    //add previous results
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);//把当前数加到第0位。如果不按顺序可以加到末位
                    }
                    res.addAll(temp);
                }
                while (i < len - 1 && nums[i] == nums[i + 1]) {
                    //skip duplicated numbers
                    i++;
                }
            }
        }
        return res;
    }

    public static void main(String args[]) {
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3, 100};
        System.out.println(fourSum(nums, 0).toString());
    }
}
