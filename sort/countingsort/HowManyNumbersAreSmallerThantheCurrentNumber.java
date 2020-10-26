package sort.countingsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 * 示例 1：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 20201026
 */
public class HowManyNumbersAreSmallerThantheCurrentNumber {
    /**
     * 题意：求数组中所有比当前数字小的数字的个数。
     * 解法：计数排序（我觉得有点像桶排序bucket sort？）
     * 计数排序是一个非基于比较的排序算法，该算法于1954年由 Harold H. Seward 提出。它的优势在于在对一定范围内的整数排序时，它的复杂度为Ο(n+k)（其中k是整数的范围），快于任何比较排序算法。
     * 思路是，因为数字范围是[0,100], 可以用长度101的cnt数组记录每个数字出现的次数，然后cnt从前往后累加；
     * 输入res的时候读取cnt[nums[i] - 1]就行了。
     * 时间：Ο(n+k)
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] cnt = new int[101];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ret;
    }

    /**
     * 我想到的计数排序，额外用了一个map记录数字出现的index；主要还是因为没想到cnt[nums[i] - 1]
     */
    public int[] smallerNumbersThanCurrent_(int[] nums) {
        int[] freq = new int[101], res = new int[nums.length];
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            freq[num]++;
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        int prev = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                for (Integer index : map.get(i)) {
                    res[index] = prev;
                }
                prev += freq[i];
            }
        }
        return res;
    }
}
