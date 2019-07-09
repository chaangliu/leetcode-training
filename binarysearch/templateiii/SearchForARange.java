package binarysearch.templateiii;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * Created by DrunkPiano on 2016/12/29.
 * 20190709 review
 */

public class SearchForARange {

    /**
     * 这题被归类到二分模板3，没看出来哪里像。感觉像模板2。
     */
    public int[] searchRange_(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int low = 0, hi = nums.length - 1;
        int[] rs = new int[2];
        //left side
        while (low < hi) {
            int mid = low + (hi - low) / 2;
            if (target > nums[mid]) {//在找到target之前low一直右移
                low = mid + 1;
            } else {
                //隐含条件：target即便 == nums[mid]，hi也继续左移（可以用于找重复数字有多少个）。
                //比如对于[7,8,8,8,9]找8，第一轮A[2] = 8，下一轮mid = (0+2)/2 = 1，还是在2左边。但是不能mid - 1 = 1，那样下一轮mid就是0了。这种low < high的循环，是在low == high时退出，不要漏解
                hi = mid;
            }
        }
        if (target == nums[low]) {
            rs[0] = low;
        } else {
            rs[0] = -1;
        }
        //right side
        hi = nums.length - 1;
        while (low < hi) {
            int mid = (low + (hi - low) / 2) + 1;
            if (target < nums[mid]) {
                hi = mid - 1;
            } else {
                low = mid;
            }
        }
        if (target == nums[hi]) {
            rs[1] = hi;
        } else {
            rs[1] = -1;
        }
        return rs;
    }


    public static int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, 0, nums.length - 1, target);

        int leftRidge = index;
        int rightRidge = index;
        int[] result = new int[]{-1, -1};
        if (index == -1) return result;
        while (leftRidge > 0 && nums[leftRidge - 1] == nums[leftRidge]) {
            leftRidge--;
        }
        while (rightRidge < nums.length - 1 && nums[rightRidge + 1] == nums[rightRidge]) {
            rightRidge++;
        }
        result = new int[]{leftRidge, rightRidge};
        return result;
    }

    public static int binarySearch(int[] nums, int left, int right, int target) {
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int nums[] = {5, 7, 7, 8, 8, 10};
        int[] result = searchRange(nums, 8);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}

//or: http://blog.csdn.net/sbitswc/article/details/31172383

//http://fisherlei.blogspot.jp/2013/01/leetcode-search-for-range.html