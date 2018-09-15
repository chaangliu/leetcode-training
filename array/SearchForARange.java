package array;

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
 */

public class SearchForARange {
    public static int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, 0, nums.length - 1, target);

        int leftRidge = index;
        int rightRidge = index;
        int[] result = new int [] {-1, -1};
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