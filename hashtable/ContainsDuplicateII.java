package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * <p>
 * 20190515
 */
public class ContainsDuplicateII {
    /**
     * 我想到的方法是Map<Integer, ListNode>，把index都记录下来
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, ArrayList> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        for (Map.Entry entry : map.entrySet()) {
            ArrayList list = (ArrayList) entry.getValue();
            for (int i = 1; i < list.size(); i++) {
                if ((int) list.get(i) - (int) list.get(i - 1) <= k) return true;
            }
        }
        return false;
    }

    /**
     * 巧妙方法
     */
    public boolean containsNearbyDuplicate__(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);//每次添加之前，把set的窗口大小减小到k - 1
            if (!set.add(nums[i])) return true;//如果成功添加，表示k步内有重复
        }
        return false;
    }
}
