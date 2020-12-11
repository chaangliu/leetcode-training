package hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer k.
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * Return the maximum number of operations you can perform on the array.
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 */
public class MaxNumberofKSumPairs {
    /**
     * 题意：给你一个数组和一个k，让你每次找2个数字凑成k，问最多可以碰多少次。
     * 解法：我用了两个map，因为一个map用keySet()来遍历的话会出现同时读写错误；但这种最后一定要除以二，并且要注意相同数字的情况要res+=2。
     */
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(), tmp = new HashMap<>();
        int res = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        while (!map.isEmpty()) {
            for (int key : map.keySet()) {
                if (map.containsKey(k - key)) {
                    if (key == k - key) {
                        if (map.get(key) >= 2) {
                            if (map.get(key) > 2) tmp.put(key, map.get(key) - 2);
                            res += 2;
                        }
                    } else {
                        if (map.get(k - key) >= 1) {
                            if (map.get(key) > 1) tmp.put(key, map.get(key) - 1);
                            if (map.get(k - key) == 0) tmp.put(k - key, map.get(k - key) - 1);
                            res++;
                        }
                    }
                }
            }
            map = tmp;
            tmp = new HashMap<>();
        }
        return res / 2;
    }

    /**
     * 高效解法：two sum
     * 利用two sum那种形式，一边判断已有的map里有没有能配对的一边往map里put。
     */
    public int maxOperations_(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int result = 0;
        //统计每个数据出现的次数，key为数据，value为次数
        for (int num : nums) {
            // 获取求和的另一个数
            int x = k - num;
            // 从map获取x
            Integer i = map.get(x);
            // 是否有 另一个数据。且统计的数量大于0
            if (i != null && map.containsKey(x) && map.get(x) > 0) {
                result++;//结果+1
                map.put(x, map.get(x) - 1);// 数量减一
                continue;
            }
            //这个数没有被使用，统计数量+1
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        return result;
    }

    /**
     * 不用额外空间的方法：two pointers
     */
    public int maxOperations__(int[] nums, int k) {
        int result = 0;
        //排序
        Arrays.sort(nums);
        //头尾指针
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (k == sum) {//刚好相等。两个指针都往中间移动
                result++;
                i++;
                j--;
            } else if (k > sum) {//两数之和太小，左指针右移，让和变大
                i++;
            } else {//两数之和太大，右指针左移，让和变小
                j--;
            }
        }
        return result;
    }
}
