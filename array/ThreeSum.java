package array;

import java.util.ArrayList;
import java.util.Arrays;
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
 */

public class ThreeSum {


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();   //answer
//        List<Integer> list_cell = new ArrayList<>();
        Arrays.sort(nums);
        int low, high, target;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
//            System.out.println("result--->" + i);
            low = i + 1;
            high = nums.length - 1;
            target = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    List<Integer> list_cell = new ArrayList<Integer>();//一定要放在这里new。。。而且不能在下面clear，否则连带list_cell没了，list也没了

                    list_cell.add(nums[i]);
                    list_cell.add(nums[low]);
                    list_cell.add(nums[high]);
                    list.add(list_cell);
//                    list_cell.clear();

                    while (low < high && nums[low + 1] == nums[low]) {
                        low++;
                    }
                    while (low < high && nums[high - 1] == nums[high]) {
                        high--;
                    }
                    low++;//critical
                    high--;
                } else if (nums[low] + nums[high] < target) {
                    low++;
                } else {
                    high--;
                }
//                list_cell.clear();
            }
        }
        return list;
    }

    public static void main(String args[]) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-2, 0, 1, 1, 2};
        List<List<Integer>> list = threeSum(nums);
        System.out.println("result--->" + list.toString());
    }

}
