package array;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Created by DrunkPiano on 2016/12/24.
 */

public class SearchInRotatedSortedArray {

    //    public static int search(int nums[], int target) {
//        int len = nums.length;
//        int left = 0, right = len - 1;
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (target < nums[left] || target > nums[right]) return -1;
//
//            if (nums[mid] == target) return mid;
//            if (nums[left] < nums[right]) {
//                if (nums[mid] < target) left = mid + 1;
//                else right = mid - 1;
//            }
//            else if (nums[mid] > nums[left]) {
//                if (target >= nums[left] && target < nums[mid])
//                    right = mid - 1;
//                else left = mid + 1;
//            } else {
//                if (target < nums[mid] && target <= nums[right])
//                    left = mid + 1;
//                else right = mid - 1;
//            }
//        }
//        return -1;
//    }
//    public static int search(int nums[], int target) {
//        int len = nums.length;
//        int left = 0, right = len - 1;
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (nums[mid] == target)
//                return mid;
//            if (nums[mid] >= nums[left]) {
//                if (target >= nums[left] && target < nums[mid])
//                    right = mid - 1;
//                else left = mid + 1;
//            } else {
//                if (target > nums[mid] && target <= nums[right])
//                    left = mid + 1;
//                else right = mid - 1;
//            }
//        }
//        return -1;
//    }

    public static int search(int nums[], int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else left = mid + 1;
            } else {
                if (target > nums[mid] && target < nums[right])
                    left = mid + 1;
                else right = mid - 1;
            }
        }
    }

    public static void main(String args[]) {
        int a[] = {3, 1};
        System.out.println(search(a, 1));
    }
}
