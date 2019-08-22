package binarysearch;

/**
 * Implementing the class MajorityChecker, which has the following API:
 * <p>
 * MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
 * int query(int left, int right, int threshold) has arguments such that:
 * 0 <= left <= right < arr.length representing a subarray of arr;
 * 2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
 * Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times, or -1 if no such element exists.
 * Example:
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // returns 1
 * majorityChecker.query(0,3,3); // returns -1
 * majorityChecker.query(2,3,2); // returns 2
 * Constraints:
 * 1 <= arr.length <= 20000
 * 1 <= arr[i] <= 20000
 * For each query, 0 <= left <= right < len(arr)
 * For each query, 2 * threshold > right - left + 1
 * The number of queries is at most 10000
 * 20190815
 */
public class OnlineMajorityElementInSubarray {
    /**
     * 这题是寻找众数，问题是会调用10000次，如何加速查找？
     * 方法一是先对index做分组，然后对每个数字的index做二分查找，寻找lower_bound和upper_bound，看这个区间长度是否>threshold
     * 一个trick是，不用顺序遍历map.entry,而是随机挑选，挑选20次，不命中的概率就只有1/2^20了。
     */
    //    class MajorityChecker {
    //        public:
    //        unordered_map<int, vector<int>> idx;
    //
    //        MajorityChecker(vector<int> &arr) {
    //            for (auto i = 0; i < arr.size(); ++i) idx[arr[i]].push_back(i);
    //        }
    //
    //        int query(int left, int right, int threshold) {
    //            for (auto &i : idx) {
    //                if (i.second.size() < threshold) continue;
    //                auto l = lower_bound(begin(i.second), end(i.second), left);
    //                auto r = upper_bound(begin(i.second), end(i.second), right);
    //                if (r - l + 1 > threshold) return i.first;
    //            }
    //            return -1;
    //        }
    //    };

    /**
     * 方法二，线段树，明天学一下，结合307题
     */
}
