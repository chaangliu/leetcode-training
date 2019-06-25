package binarysearch;

/**
 * (This problem is an interactive problem.)
 * <p>
 * You may recall that an array A is a mountain array if and only if:
 * <p>
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.
 * You can't access the mountain array directly.  You may only access the array using a MountainArray interface:
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * Example 1:
 * <p>
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 * <p>
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 * Constraints:
 * <p>
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 * 20190625
 */
public class FindInMountainArray {
    /**
     * 题目在一个peak array里寻找target。
     * 做法：binary search
     * 凡是peak array的题目，目前都是套用binary search的模板ii。先找到peak，然后在左边和右边做二分。
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        //1. find peak(模板ii)
        int l = 0, r = mountainArr.length() - 1, peak;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        peak = l;
        l = 0;
        r = peak;
        //2. binary search on left side(模板i)
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int m = mountainArr.get(mid);
            System.out.println("mid is" + m);
            if (m == target) {
                return mid;
            } else if (m > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        //2. binary search on right side(也可以复用上面的代码)
        l = peak;
        r = mountainArr.length() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int m = mountainArr.get(mid);
            if (m == target) {
                return mid;
            } else if (m > target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * // This is MountainArray's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface MountainArray {
     * public int get(int index) {}
     * public int length() {}
     * }
     */
    abstract class MountainArray {
        public int get(int index) {
            return 0;
        }

        public int length() {
            return 0;
        }
    }
}
