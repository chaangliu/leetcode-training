package binarysearch.templatei;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Created by DrunkPiano on 2016/12/24.
 * //20181027 review
 * //20190629 review
 * //20200104 review
 */

public class SearchInRotatedSortedArray {
    /**
     * 题意：在一个发生翻折的sorted array里搜索一个数。
     * 这题要找到一个ascending的区间进行搜索（与lo或者hi对比都行），*[关键]是要判断target是在这个区间内的，否则交给下次迭代。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[lo]) {// 这个条件说明[low, mid]之间是递增的；不能是>，否则无法通过[3,1]，因为只有>的话，3,1会被认为是递增的
                // 关键，这里跟普通的二分搜索的区别是，你需要把target的两端都限定
                // 这个if必须在前，因为只有一段是固定的，else才能去不确定的那另一边搜
                if (target >= nums[lo] && target < nums[mid]) hi = mid;
                    // target在[low,mid]之外，那么target一定落在mid右边
                else lo = mid + 1;
            } else {// (mid,high]之间是递增的
                // 我们只能保证它在[mid, hi]之间是递增的，所以判断一下target如果在这个范围内就处理，否则把需求交给下次迭代
                if (target > nums[mid] && target <= nums[hi]) lo = mid;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 第二种写法，与hi对比
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
