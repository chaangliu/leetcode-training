package greedy;

import java.util.HashMap;

/**
 * You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.
 * <p>
 * Example 1:
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 * Example 2:
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * Example 3:
 * Input: [1,2,3,4,4,5]
 * Output: False
 * Note:
 * The length of the input is in range of [1, 10000]
 * 20190622
 */
public class SplitArrayIntoConsecutiveSubsequences {

    /**
     * 题意是把数组分成N个子序列，每个都是连续的并且长度至少是3。
     * 这题思路挺难想到的，做法是greedy：
     * 每次只取连续三个，而不是每次把三个都取完，那样会让1,2,3,4,4,5产生落单的现象），然后把第四个记录在另一个map，
     * 后面如果再遇到第四个，优先把它接入到前面已有序列的后面
     */
    public boolean isPossible(int[] nums) {
        Counter freq = new Counter();
        Counter tail = new Counter();
        for (int num : nums) freq.add(num, 1);
        for (int num : nums) {
            if (freq.get(num) <= 0) continue;
            if (tail.get(num) > 0) {//这里不要用成containsKey
                tail.add(num, -1);
                tail.add(num + 1, 1);
            } else if (freq.get(num) > 0 && freq.get(num + 1) > 0 && freq.get(num + 2) > 0) {
                freq.add(num + 1, -1);
                freq.add(num + 2, -1);
                tail.add(num + 3, 1);
            } else
                return false;
            freq.add(num, -1);//注意这句话要放在最外层，不能仅在出现了连续三个的时候调用
        }
        return true;
    }

    class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {//简化put
            put(k, get(k) + v);
        }
    }

    public static void main(String args[]) {
        int[] a = new int[]{1, 2, 3, 3, 4, 4, 5, 5};
        new SplitArrayIntoConsecutiveSubsequences().isPossible(a);
    }
}
