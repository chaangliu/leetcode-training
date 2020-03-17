package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n engineers numbered from 1 to n and two arrays: speed and efficiency, where speed[i] and efficiency[i] represent the speed and efficiency for the i-th engineer respectively. Return the maximum performance of a team composed of at most k engineers, since the answer can be a huge number, return this modulo 10^9 + 7.
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 * Example 1:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 10^5
 * 1 <= efficiency[i] <= 10^8
 * 1 <= k <= n
 */
public class MaximumPerformanceofaTeam {
    /**
     * 题意：给你一些人和一些速度，从里面选k个人，团队表现 = 速度和 * 最小效率；求最大效率。
     * 解法：我一开始用暴力去做，但是后来发现，3个人的情况不一定包含2个人的情况时候的所有人。。
     * 看了答案，先按照效率降序排序，然后用heap保存最小速度，遍历一遍就行了，需要理解，不是很好证明，可以参考MaximumPerformanceofaTeam那题。
     * similar: MaximumPerformanceofaTeam
     */
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] ess = new int[n][2];
        for (int i = 0; i < n; ++i)
            ess[i] = new int[] {efficiency[i], speed[i]};
        Arrays.sort(ess, (a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> a - b);
        long res = 0, sumS = 0;
        for (int[] es : ess) {
            pq.add(es[1]);
            sumS = (sumS + es[1]);
            if (pq.size() > k) sumS -= pq.poll(); // 把速度最慢的人踢出去
            res = Math.max(res, (sumS * es[0])); // 当前团队的最小效率就是当前这个人的效率
        }
        return (int) (res % (long)(1e9 + 7));
    }
}
