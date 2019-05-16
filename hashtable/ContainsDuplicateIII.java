package hashtable;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * <p>
 * 20190516
 */
public class ContainsDuplicateIII {
    /**
     * TreeSet解法，O(nLog(k))，主要利用ceiling和floor做二分查找，查找边界
     * <p>
     * long的问题比较烦人
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            //ceil: >=给定数的最小数（利用二分搜索）
            //floor: <=给定数的最大数
            if (treeSet.floor((long) nums[i] + (long) t) != null && treeSet.floor((long) nums[i] + (long) t) >= (long) nums[i] - (long) t) {
                return true;
            }
            treeSet.add(new Long(nums[i]));
            if (i >= k) {
                treeSet.remove(new Long(nums[i - k]));
            }
        }
        return false;
    }
}
