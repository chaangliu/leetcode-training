package array;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 * <p>
 * Then, we choose a subset S of these items, such that:
 * <p>
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 * Example 1:
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 * Example 2:
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 * Example 3:
 * <p>
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * Output: 16
 * Explanation: The subset chosen is the first and fourth item.
 * Example 4:
 * <p>
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 * Note:
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 * 20190616 contest#2
 */
public class LargestValuesFromLabels {
    /**
     * 本周contest第二题，读题读了好久。思路倒是不难，用PriorityQueue。
     */
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.val - o1.val;
            }
        });
        for (int i = 0; i < values.length; i++) {
            q.offer(new Pair(values[i], labels[i]));
        }
        Map<Integer, Integer> labelCountMap = new HashMap<>();
        int res = 0;
        while (num_wanted > 0 && !q.isEmpty()) {
            Pair p = q.poll();
            if (!labelCountMap.containsKey(p.label)) {
                labelCountMap.put(p.label, 0);
            }
            if (labelCountMap.get(p.label) >= use_limit) continue;
            res += p.val;
            num_wanted--;
            labelCountMap.put(p.label, labelCountMap.get(p.label) + 1);
        }
        return res;
    }

    class Pair {
        int val;
        int label;

        Pair(int v, int l) {
            val = v;
            label = l;
        }
    }
}
