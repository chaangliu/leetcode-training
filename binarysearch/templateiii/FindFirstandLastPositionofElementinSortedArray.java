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

public class FindFirstandLastPositionofElementinSortedArray {

    /**
     * * 注：这题以前叫做Search for a range, 后来改名了。
     * 题意：给你一个排好序的数组，让你找出现target的第一个和最后一个index。如果不存在target，返回[-1,-1]
     * 这题真是要吹爆花花的总结，用起来非常方便。就按照c++ lower_bound, upper_bound的那一套来做就行了。
     */
    public int[] searchRange(int[] A, int target) {
        int[] res = new int[2];
        if (A.length == 0) return new int[]{-1, -1};
        int lo = 0, hi = A.length;
        while (lo < hi) {// lower_bound = 第一个>=target的位置
            int mid = lo + (hi - lo) / 2;
            if (A[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        res[0] = lo < A.length && A[lo] == target ? lo : -1;// 已犯错误：1. 忘记了可能搜不到 2. 忘记了可能lo == A.length，比如[3,3]4
        lo = 0;
        hi = A.length;
        while (lo < hi) {// upper_bound = 第一个>target的位置
            int mid = lo + (hi - lo) / 2;
            if (A[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        res[1] = lo - 1 >= 0 && A[lo - 1] == target ? lo - 1 : -1;// 已犯错误： 忘记了lo-1可能<0
        return res;
    }
}