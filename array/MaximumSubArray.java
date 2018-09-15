package array;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/1/7.
 */

public class MaximumSubArray {
//    way1:binary search + recursion
//    public int maxSubArray(int[] nums) {
//        int maxValue = Integer.MIN_VALUE;
//        return findMaxSubArray(nums, 0, nums.length - 1, maxValue);
//    }

    public int findMaxSubArray(int nums[], int left, int right, int maxValue) {

        if (left > right)
            return Integer.MIN_VALUE;
        int mid = (left + right) / 2;
        //find lmax
        int lmax = findMaxSubArray(nums, left, mid - 1, maxValue);
        //find rmax
        int rmax = findMaxSubArray(nums, mid + 1, right, maxValue);

        maxValue = Math.max(maxValue, lmax);
        maxValue = Math.max(maxValue, rmax);

        //find middle lmax
        int sum = 0, mlmax = 0;
        for (int i = mid - 1; i >= left; i--) {
            sum += nums[i];
            if (sum > mlmax)
                mlmax = sum;
        }
        sum = 0;
        int mrmax = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > mrmax)
                mrmax = sum;
        }
        maxValue = Math.max(maxValue, mlmax + mrmax + nums[mid]);


        return maxValue;
    }


    //second way , Dynamic Programming
    public int maxSubArray(int[] nums) {
        int sum = nums[0], maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static void main(String args[]) {
        int nums[] = {-2, -5, -3, -4, -8, -2, -6, -5, -4};
        MaximumSubArray maximumSubArray = new MaximumSubArray();
        int max = maximumSubArray.maxSubArray(nums);
        System.out.println(max);


        System.out.println(maximumSubArray);
    }


//    public int maxSubArray(int[] nums) {
//        if (nums.length == 0) return 0;
//        int local = nums[0];
//        int global = nums[0];
//        //local[i] = local[i-1] < 0 ? nums[i]: local[i-1]+nums[i]
//
//        for (int i = 1; i < nums.length; i++) {
//            local = local > 0 ? local + nums[i] : nums[i];
//            global = Math.max(local, global);
//        }
//        return global;
//    }

//    public int maxSubArray(int[] nums) {
//        if (nums.length == 0) return 0;
//        int dp[] = new int[nums.length];
//        dp[0] = nums[0];
//        //local[i] = local[i-1] < 0 ? nums[i]: local[i-1]+nums[i]
//
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = dp[i-1]  > 0 ?nums[i] :
//        }
//    }


}
