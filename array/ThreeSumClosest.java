package array;


import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * Created by DrunkPiano on 2016/12/11.
 */

public class ThreeSumClosest {
    /**
     * 题意：在一个数组里找3个数，它们的sum最接近target。返回sum。
     * 解法：跟three sum类似，先排序再双指针。要维护一个minDist记录sum和target之间的最小距离.
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int low, high, res, minDist;
        res = nums[0] + nums[1] + nums[nums.length - 1];
        minDist = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            low = i + 1;
            high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    if (target - sum < minDist) {
                        minDist = target - sum;
                        res = sum;
                    }
                    low++;
                } else {
                    if (sum - target < minDist) {
                        minDist = sum - target;
                        res = sum;
                    }
                    high--;
                }
            }
        }
        return res;
    }
}
