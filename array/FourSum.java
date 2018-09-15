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
//                        while (low < high) {
//                            if (nums[low + 1] == nums[low]) {
//                                low++;
//                            }
//                            if (nums[high - 1] == nums[high]) {
//                                high--;
//                            }
//                        }
                        //只变一个肯定不行的，要两个同时移动

                        low++;
                        high--;
                        while (low < high && nums[low] == nums[low - 1]) low++;
                        while (low < high && nums[high] == nums[high + 1]) high--;


                        while (low+1  < high && nums[low] == nums[low + 1]) low++;
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

    public static void main(String args[]) {
        int[] nums = {-3,-2,-1,0,0,1,2,3,100};
        System.out.println(fourSum(nums, 0).toString());
    }
}
