package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * dp[i+1] =
 * Created by DrunkPiano on 2017/4/30.
 */

public class SubArraySumequalstoK {
    //    public int subarraySum(int[] nums, int k) {
//        int res = 0;
//        int temp = k;
//        if (nums == null || nums.length == 0) return 0;
//        for (int i = 0; i < nums.length; i++) {
////            temp = k - nums[i];
//            for (int j = i; j >= 0 && temp * k >= 0; j--) {
//                temp = temp - nums[j];
//                if (temp == 0 && k != 0) res++;
//                if (k == 0 && temp == 0 && j != i) res++;
//            }
//            temp = k;
//        }
//        return res;
//    }
////
//    public int subarraySum(int[] nums, int k) {
//        int res = 0;
//        if (nums == null || nums.length == 0) return 0;
//        for (int i = 0; i < nums.length; i++) {
//            int temp = k;
//            for (int j = i; j >= 0; j--) {
//                temp = temp - nums[j];
//                if (temp == 0) res++;
//            }
//        }
//        return res;
//    }

//    class Solution {
//        public:
//        int subarraySum(vector<int>& nums, int k) {
//            unordered_multiset<int> s;
//            s.insert(0);
//            int result = 0, sum = 0;
//            for (const auto &i : nums) {
//                sum += i;
//                result += s.count(sum - k);
//                s.insert(sum);
//            }
//            return result;
//        }
//    };

    //    public int subarraySum(int[] nums, int k) {
//        HashSet<Integer> s  = new HashSet<>();
//        s.add(0);
//        int res = 0 , sum = 0 ;
//        for (int i = 0 ; i < nums.length ; i ++){
//            sum += nums[i] ;
//            res += s.contains(sum - k) ? 1 : 0 ;
//            s.add(sum);
//        }
//        return res;
//    }

        public int subarraySum(int[] nums, int k) {
            int sum = 0, result = 0;
            Map<Integer, Integer> preSum = new HashMap<>();
            preSum.put(0, 1);

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (preSum.containsKey(sum - k)) {
                    result += preSum.get(sum - k);
                }
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
            }
            return result;
        }

//public int subarraySum(int[] nums, int k) {
//    //用一个map记录<迄今为止>subarray的值
//}
    public static void main(String args[]) {
        int[] nums = {1, -1, 1,-1};
//        int[] nums = {1, 2, 1, 2, 1};
//        int[] nums = {-92, -63, 75, -86, -58, 22, 31, -16, -66, -67, 420};
        System.out.println(new SubArraySumequalstoK().subarraySum(nums, 0));
    }

}
