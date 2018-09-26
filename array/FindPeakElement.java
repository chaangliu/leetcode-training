package array;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 */
public class FindPeakElement {

    //这题关键是，如果有多个peak，返回任意一个就行。所以只需要知道nums[i] > nums[i + 1]的那个i。
    //#1 O(n) Scan
//    public int findPeakElement(int[] nums) {
//        if (nums == null) return -1;
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] > nums[i + 1]) return i;
//        }
//        return nums.length - 1;
//    }


    //#2 recursive binary search
    public int findPeakElement(int[] nums) {
        if (nums == null) return -1;
        return helper(nums, 0, nums.length - 1);
    }

    //因为不是找一个确定的值，所以终止条件是首位相遇(low == high)。
    //注意这里不能mid + 1、mid -1了，那样会漏解。此题我仍然有个疑问，为什么一定是low = mid + 1而不能是high = mid -1？似乎与int的除法取整方式有关。应该是个固定套路吧。
    private int helper(int[] nums, int low, int high) {
        if (low == high) return low;
        int mid = low + (high - low) / 2;
        if (nums[mid] > nums[mid + 1]) {//mid + 1不会越界
            return helper(nums, low, mid);
        }
        return helper(nums, mid + 1, high);
    }



    public static void main(String args[]) {
        int[] arr = {1, 2};
        System.out.println(new FindPeakElement().findPeakElement(arr));
        System.out.println();
    }

//    //#3 iterative binary search
//    public int findPeakElement(int[] nums) {
//        int l = 0, r = nums.length - 1;
//        while (l < r) {
//            int mid = (l + r) / 2;
//            if (nums[mid] > nums[mid + 1])
//                r = mid;
//            else
//                l = mid + 1;
//        }
//        return l;
//    }

}
