package array;

/**
 * Created by DrunkPiano on 2017/5/4.
 */

public class MinimumSizeSubarraySum {

    /**
     * O(n^2)
     */
    public int minSubArrayLen0(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    //Math.min..
                    minLen = j - i + 1 < minLen ? j - i + 1 : minLen;
                    break;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * O(n)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int j = 0;
        int i = 0;

        while (j < nums.length) {
            sum += nums[j++];

            while (sum >= s) {
                minLen = Math.min(minLen, j - 1 - i + 1);
                sum -= nums[i++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen ;
    }
}
