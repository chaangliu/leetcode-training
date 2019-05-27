package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 * <p>
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.
 * Example 1:
 * <p>
 * Input: [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 * Example 2:
 * <p>
 * Input: [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,2,1,2,1]
 * Note:
 * <p>
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * <p>
 * 20190527
 */
public class DistantBarcodes {
    /**
     * Approach1. odd even insertion
     * 这题我一开始用了个Map和队列循环插入，每次插完就放到队尾区，但最后发现结尾会有重复。
     * 看别人做法发现这题跟之前做过的那道TaskScheduler很像，也是两种做法，从多到少插入或者heap。
     * 例子：
     * 111122233
     * 1 1 1 1 2
     * 121213132
     *
     * 【相似题目】
     * 621. Task Scheduler，358. Rearrange String（LOCKED），767. Reorganize String
     */
    //    public int[] rearrangeBarcodes(int[] barcodes) {
    //      用Java写起来很麻烦，C++/PYTHON比较容易
    //    }

    /**
     * Approach2. priority queue
     * 跟上面的做法略有不同，不用间隔一位插入数字了，但是要每次poll完之后更新pq，跟621做法一样。
     * 时间O(n log n)
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        //1. 统计出现次数
        if (barcodes == null || barcodes.length == 0) return new int[0];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : barcodes) map.put(i, map.getOrDefault(i, 0) + 1);

        //2. 对Map.Entry建堆(不用自己构造Pair了，直接用map.entrySet())，出现次数多的Entry放堆顶
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue() == 0 ? a.getKey() - b.getKey() : b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) pq.offer(entry);
        int[] res = new int[barcodes.length];
        int i = 0;
        while (!pq.isEmpty()) {
            int k = 2;
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
            while (k-- > 0 && !pq.isEmpty()) {
                Map.Entry<Integer, Integer> node = pq.poll();
                node.setValue(node.getValue() - 1);
                res[i++] = node.getKey();
                list.add(node);
            }
            for (Map.Entry<Integer, Integer> entry : list) {
                if (entry.getValue() > 0) pq.offer(entry);
            }
        }
        return res;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{1, 1, 1, 1, 2, 2, 2, 3, 3};
        new DistantBarcodes().rearrangeBarcodes(nums);
    }
}
