package array;

import java.util.HashMap;

/**
 * Created by DrunkPiano on 2017/5/5.
 */

public class MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                maxLen = i;
            }
            else if (map.get(sum-k)!=null){
                maxLen = Math.max(maxLen , i - map.get(sum - k ));
            }
            else if (!map.containsKey(sum)){
                map.put(sum , i );
            }
        }
        return maxLen ;
    }
}
