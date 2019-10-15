package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
 * <p>
 * If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,1,1,5,3,3,5]
 * Output: 7
 * Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
 * Example 2:
 * <p>
 * Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * Output: 13
 * Example 3:
 * <p>
 * Input: nums = [1,1,1,2,2,2]
 * Output: 5
 * Example 4:
 * <p>
 * Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
 * Output: 8
 * Constraints:
 * <p>
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 20191015
 */
public class MaximumEqualFrequency {
    /**
     * 题意：从一个数字数组中找出前n个数，满足这n个数中去掉一个之后剩下的数字出现次数都相当。返回n。
     * 刚拿到感觉很容易（其实有很多case要讨论），模拟：每次遍历到所有已有数字occurrence都相等并且当前的下一个位置index时候，更新n = index + 1
     * 关键是怎么用O(1)时间去判断所有已有数字的occurrence都相等？我觉得可以维护两个Map，第一个的k->v是number->occurrence,第二个是occurence->list of numbers，其中第二个map的key对应的list的size是0的时候就remove
     * 这么想着很简单，但是有很多case我都没有考虑到，WA了很多次。能短时间内cover到所有case不容易。
     * 以下是一些满足的情况：
     * 情况一：只出现一个数字。
     * 情况二：出现很多数字，但是都次数都是1。
     * 情况三：只有一个数字出现一次，其他都出现多次，且次数相等。
     * 情况四：所有数字都出现多次，只有一个特殊多了一次其他次数都相等。
     **/
    public int maxEqualFreq(int[] nums) {
        if (nums.length == 1) return 1;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, HashSet<Integer>> map2 = new HashMap<>();
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map1.put(num, map1.getOrDefault(num, 0) + 1);
            int newFreq = map1.get(num);
            map2.putIfAbsent(newFreq, new HashSet<>());
            map2.get(newFreq).add(num);//1->[2, ], 2->[2, ]，向新的freq添加数字
            if (map2.containsKey(newFreq - 1)) {
                HashSet oldSet = map2.get(newFreq - 1);
                if (oldSet == null) continue;
                if (oldSet.contains(num)) {
                    oldSet.remove(num);//老set移除数字
                }
                if (oldSet.size() == 0) {
                    map2.remove(newFreq - 1);//移除老freq
                }
            }
            if (map2.size() == 1) {
                if (map2.containsKey(1))
                    res = i + 1;
                else {
                    for (Map.Entry<Integer, HashSet<Integer>> entry : map2.entrySet()) {
                        if (entry.getValue().size() == 1) res = i + 1;
                    }
                }
            } else if (map2.size() == 2) {
                //[1,1,2,2,2,3,3,3|..]
                //2->[1];3->[2,3,4..]
                //[1,1,1,2,2,2]
                //3->[1], 2->[2]
                int freq = -1, x = -1;
                for (Map.Entry<Integer, HashSet<Integer>> entry : map2.entrySet()) {
                    if (x == -1 && freq != -1 && (freq - 1 == 0 || freq - 1 == entry.getKey() || entry.getValue().size() == 1)) {
                        res = i + 1;
                    }
                    if (freq == -1 && x != -1 && (entry.getValue().size() == 1 && (entry.getKey() == 1 || entry.getKey() - 1 == x))) {
                        res = i + 1;
                    }
                    if (freq == -1 && x == -1) {
                        if (entry.getValue().size() == 1) {
                            freq = entry.getKey();
                        } else {
                            x = entry.getKey();
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 讨论区的答案，记录最大的freq maxF，分三种情况
     * all elements appear exact once.
     * all elements appear max_F times, except one appears once.
     * all elements appear max_F-1 times, except one appears max_F.
     */
    public int maxEqualFreq_(int[] A) {
        int[] cnt = new int[100001], freq = new int[100001];
        int maxF = 0, res = 0;
        for (int i = 0; i < A.length; i++) {
            int num = A[i];
            cnt[num]++;
            freq[cnt[num] - 1]--;
            freq[cnt[num]]++;
            maxF = Math.max(maxF, cnt[num]);
            if (maxF * freq[maxF] == i || (maxF - 1) * (freq[maxF - 1] + 1) == i || maxF == 1)
                res = i + 1;
        }
        return res;
    }
}
