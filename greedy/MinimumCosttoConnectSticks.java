package greedy;

import java.util.PriorityQueue;

/**
 * You have some sticks with positive integer lengths.
 * <p>
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
 * <p>
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sticks = [2,4,3]
 * Output: 14
 * Example 2:
 * <p>
 * Input: sticks = [1,8,3,5]
 * Output: 30
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= sticks.length <= 10^4
 * 1 <= sticks[i] <= 10^4
 * 20190825
 */
public class MinimumCosttoConnectSticks {
    /**
     * 把所有stick焊接到一根，焊接成本等于每次焊接长度相加。
     * 想了一下就发现，最优方案肯定是先焊接最短的stick，因为这些stick后面会累加。
     * 这样就有点像huffman树了。
     */
    public int connectSticks(int[] sticks) {
        if (sticks.length == 1) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int s : sticks) queue.offer(s);
        int res = 0;
        while (queue.size() >= 2) {
            int val = queue.poll() + queue.poll();
            res += val;
            queue.offer(val);
        }
        return res;
    }
}
