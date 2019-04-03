package array;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 * <p>
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 * <p>
 * Created by DrunkPiano on 14/05/2017.
 */

public class ShortestUnsortedContinuousSubarrays {

    /**
     * approach1. Sorting，时间O(nlogn)
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] temp = nums.clone();
        Arrays.sort(temp);
        int start = 0, end = nums.length - 1;
        while (start < nums.length && temp[start] == nums[start]) {
            start++;
        }
        while (end > start && temp[end] == nums[end]) {
            end--;
        }
        return end + 1 - start;
    }

    /**
     * O(n)做法，之所以要借助单调栈，是因为存在3,4,5,0,1,2这种情况，不能简单地从两端开始遍历
     */
    public int findUnsortedSubarray___STACK(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int l = nums.length, r = 0;//l从右端开始，r从左端开始
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());//3，4，5，0，1，2这种，遇到0（index是3），会把stack弹空找一个最左需要调整的数
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    /**
     * O(n)time O(1)Space做法
     */
    public int findUnsortedSubarray___(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n - 1], max = A[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n - 1 - i]);
            if (A[i] < max)
                end = i;//从左往右找出最后一个需要调整的数的index
            if (A[n - 1 - i] > min)
                beg = n - 1 - i;//从右往左找出最左一个需要调整的数的index
        }
        return end - beg + 1;
    }


    public int findUnsortedSubarray__(int[] A) {
        int start = -1, end = -2, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            if (A[i] < max) {
                if (start == -1) start = i - 1;
                end = i;
                min = Math.min(min, A[i]);
            } else max = A[i];
        }

        for (int i = 0; i < A.length; i++)
            if (A[i] > min) {
                start = i;
                break;
            }

        return end - start + 1;
    }


    public static void main(String args[]) {
//		int [] nums = {2, 6, 4, 8, 10, 9, 15};
//        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums = {3, 4, 5, 0, 1, 2};
        System.out.println(new ShortestUnsortedContinuousSubarrays().findUnsortedSubarray___(nums));
    }
}
