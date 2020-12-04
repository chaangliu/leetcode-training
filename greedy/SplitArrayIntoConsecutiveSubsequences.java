package greedy;

import org.w3c.dom.ProcessingInstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Properties;

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
     * 题意：把数组分成N个子序列，每个都是连续的并且长度至少是3。
     * 这题思路挺难想到的，做法是greedy：
     * 每次只取连续三个，而不是每次把三个都取完，那样会让1,2,3,4,4,5产生落单的现象），然后把第四个记录在另一个map，
     * 后面如果再遇到第四个，优先把它接入到前面已有序列的后面
     * 时间：O(n)
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

    /**
     * 方法2，用Heap记录以x结尾的子序列们的长度，每次遇到一个新数y，贪心地加到最短的以y-1结尾的子序列上。
     * 当 x 在数组中时，如果存在一个子序列以 x-1 结尾，长度为 k，则可以将 x 加入该子序列中，得到长度为 k+1 的子序列。如果不存在以 x-1 结尾的子序列，则必须新建一个只包含 x 的子序列，长度为 1。
     * 当 x 在数组中时，如果存在多个子序列以 x−1 结尾，应该将 x 加入其中的哪一个子序列？由于题目要求每个子序列的长度至少为 3，显然应该让最短的子序列尽可能长，因此应该将 x 加入其中最短的子序列。
     * [1,2,3,3,4,4,5,5]
     * 时间: O(nlogn)
     */
    public boolean isPossible_HEAP(int[] A) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : A) {
            if (!map.containsKey(num)) map.put(num, new PriorityQueue<>());
            if (map.containsKey(num - 1)) {
                int len = map.get(num - 1).poll(); // 以num-1结尾的子序列少了一个（最短的那个）
                if (map.get(num - 1).isEmpty()) map.remove(num - 1); // 如果没有以num - 1结尾的了，就删掉key，方便下次直接添加
                map.get(num).offer(len + 1); // 以num 结尾的子序列多了一个（最短的那个）
            } else {
                map.get(num).offer(1);
            }
        }
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            while (!entry.getValue().isEmpty()) {
                if (entry.getValue().poll() < 3) return false;
            }
        }
        return true;
    }
}
