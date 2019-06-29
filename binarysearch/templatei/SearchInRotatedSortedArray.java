package binarysearch.templatei;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Created by DrunkPiano on 2016/12/24.
 */

public class SearchInRotatedSortedArray {
    //20181027 review
    //20190629 review

    /**
     * 这题要找到一个ascending的区间进行搜索。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[lo]) {//这个条件说明[low, mid]之间是递增的
                //target在[low,mid]之间
                if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                    //target在[low,mid]之外，那么target一定落在mid右边
                else lo = mid + 1;
            } else {//否则说明[mid,high]之间是递增的
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 第二种写法
     */
    public int search__(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] <= nums[hi]) {//这个条件说明[mid,high]之间是递增的
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            } else {//否则说明[low,mid]之间是递增的
                if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        int a[] = {4, 5, 6, 7, 0, 1, 2};
//        System.out.println(search(a, 1));
        int res = new SearchInRotatedSortedArray().search(a, 0);
        System.out.print(res);
    }
}
