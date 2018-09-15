package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DrunkPiano on 2017/5/3.
 */

public class ContinuousSubarraySum {
    //sums up to n*k where n is also an integer, can k be zero(YES)?
//    public boolean checkSubarraySum(int[] nums, int k) {
//        if (nums.length < 2) return false;
//        if (checkAllZero(nums)) return true;
//        if (k == 0) return checkAllZero(nums);
//        for (int i = 0; i < nums.length - 1; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                if (j - 1 > 0 && sum % k == 0) return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean checkAllZero(int[] nums) {
//        for (Integer i : nums) {
//            if (i != 0) return false;
//        }
//        return true;
//    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
            put(0, -1);
        }};
        int runningSum = 0;
        int mod = -1;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) mod = runningSum % k;
            Integer prev = map.get(mod);
            if (prev != null) {
                if (i - prev > 1)
                    return true;
            } else map.put(mod, i);
        }
        return false;
    }


    public static void main(String args[]) {
        int[] nums = {23, 2, 6, 4, 7};
        System.out.println(new ContinuousSubarraySum().checkSubarraySum(nums, 0));
    }
}
