package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * <p>
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 * <p>
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [1,1],[1,1]
 * Explanation: The first 2 pairs are returned from the sequence:
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [1,3],[2,3]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 * <p>
 * 20190724
 * 相似题目: merge k sorted list
 */
public class FindKPairsWithSmallestSums {
    /**
     * 题目：从两个有序数组里取一个u,v，求k个最小的u+v的pair。
     * 我一开始感觉能用2 pointers，但发现pointer有时候需要重置。
     * 看答案，觉得这题的技巧在于，为什么先把nums1的k个数跟nums2里的数匹配，并且记录nums2里的数的index呢？
     * 为什么不每次用的时候把另一个数组里的数的index+1呢？我感觉应该是因为那样需要多维护一个index，看看下面的类比：
     * 对于 [1,7,11,16] 和 [2,9,10,15] ，可以看成4个sorted list：
     * (1,2) -> (1,9) -> (1,10) -> (1,15)
     * (7,2) -> (7,9) -> (7,10) -> (7,15)
     * (11,2) -> (11,9) -> (11,10) -> (11,15)
     * (16,2) -> (16,9) -> (16,10) -> (16,15)
     * 所以先把k个sorted list的head add到最小堆里，把poll出来的node撸下来后，把node.next加入queue，操作跟 merge k sorted list那题一样。
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return res;
        PriorityQueue<Tuple> queue = new PriorityQueue<>((o1, o2) -> o1.u + o1.v - o2.u - o2.v);
        for (int i = 0; i < nums1.length && i < k; i++) {
            queue.offer(new Tuple(nums1[i], nums2[0], 0));
        }
        while (k-- > 0 && !queue.isEmpty()) {
            Tuple t = queue.poll();
            List<Integer> item = new ArrayList<>();
            item.add(t.u);
            item.add(t.v);
            res.add(item);
            if (t.idx_v + 1 < nums2.length) queue.offer(new Tuple(t.u, nums2[t.idx_v + 1], t.idx_v + 1));
        }
        return res;
    }

    class Tuple {
        int u;
        int v;
        int idx_v;

        Tuple(int u, int v, int idx_v) {
            this.u = u;
            this.v = v;
            this.idx_v = idx_v;
        }
    }
}
