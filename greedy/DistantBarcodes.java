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
     * 题意：给你一些数字，让你重新排列，使得相邻的数字不相同，任意排列都行。
     * 解法：贪心地先输出出现次数多的数字。
     * 例子：
     * 111122233
     * 121213132
     *【相似题目】
     * 621. Task Scheduler，358. Rearrange String（LOCKED），767. Reorganize String
     * 跟上面的做法略有不同，不用间隔一位插入数字了，但是要每次poll完之后更新pq，跟621做法一样。
     * 时间O(n log n)
     * 对于[1,1,1,2,2,2]comparator一定要有a.getKey() - b.getKey()是否相等的判断， 
     * 否则会出现1,2,2..这样的情况。如果严格按照顺序去offer和poll，就不会出现这样的情况。
     * 比如1，2出现次数都是2，从堆取出来会是1，2排列，放回去再取还是1，2。
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b)-> b.getValue() - a.getValue() != 0 ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
        Map<Integer, Integer> map = new HashMap<>();
        for (int b : barcodes) map.put(b, map.getOrDefault(b, 0) + 1);
        for (Map.Entry<Integer, Integer> e : map.entrySet()) q.offer(e);
        int [] res = new int [barcodes.length]; int index = 0;
        while (!q.isEmpty()) {
            Map.Entry<Integer, Integer> e = q.poll();
            if (e.getValue() > 0) {
                res[index++] = e.getKey();
                e.setValue(e.getValue() - 1);
            }
            if (!q.isEmpty()) {
                Map.Entry<Integer, Integer> e2 = q.poll();
                res[index++] = e2.getKey();
                e2.setValue(e2.getValue() - 1);
                if (e2.getValue() > 0) q.offer(e2);
            }
            if (e.getValue() > 0) q.offer(e);
        }
        return res;
    }
}
