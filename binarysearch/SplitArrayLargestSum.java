package binarysearch;

/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 * <p>
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * Output:
 * 18
 * <p>
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * <p>
 * 20190406
 */
public class SplitArrayLargestSum {
    /**
     * approach1. binary search
     * 题意：给你一个数组，把它分成m份，问每份的sum最大值最小是多少。
     * 其实这题可以想想brute force怎么写，就是枚举n，然后看能不能把array分成m份，每份的sum都小于n。
     * 进而，我们可以经过不断二分，n正好等于最大的那个分区的sum。还是有点思维难度的。
     * approach2. dp(略)
     */
    public int splitArray(int[] nums, int m) {
        int max = 0;
        long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int) sum;
        long l = max, r = sum;//初始状态让左边等于nums里最大的那个数，考虑nums.length == 1的情况
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (valid(mid, nums, m)) {//如果能把nums分成m份并且每份的和都<=mid，说明mid大了
                r = mid;
            } else {
                l = mid + 1;//加大力度
            }
        }
        return (int) l;//是l，不是r；经过二分搜索，l跳出循环的时候刚好就是最大区间的sum
    }

    private boolean valid(long mid, int[] nums, int m) {
        int sum = 0, count = 1;//这边count是1，不是0
        for (int num : nums) {
            sum += num;
            if (sum > mid) {//这里不是>=，所以最后一个区正好sum == mid，也return true
                sum = num;
                count++;
                if (count > m) return false;
            }
        }
        return true;
    }

}
