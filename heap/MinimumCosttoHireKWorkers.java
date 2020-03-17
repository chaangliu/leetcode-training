package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 * Example 1:
 * <p>
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 * <p>
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 */
public class MinimumCosttoHireKWorkers {
    /**
     * 题意：给你一些工人的工作质量和薪水，要求雇佣工人的时候需要按照quality的比例来分配工资；求雇佣K个人的最小薪水总合。
     * 解法：按照wage/quality升序对工人排序，这样一来，从前往后遍历，第i个人的薪水比例一定能让0~i-1这些人满意。
     * similar: MaximumPerformanceofaTeam
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] list = new Worker[quality.length];
        for (int i = 0; i < quality.length; i++) {
            list[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(list); // never forgot this..
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int qualitySum = 0;
        double res = Double.MAX_VALUE;
        for (int i = 0; i < quality.length; i++) {
            q.offer(list[i].quality);
            qualitySum += list[i].quality;
            if (q.size() > K) {
                qualitySum -= q.poll(); // 把quality最【高】的人踢出去，因为要追求最小的总薪水，质量不重要
            }
            if (q.size() == K) res = Math.min(res, qualitySum * list[i].ratio()); // 以当前的人为基准，在他前面的人一定都有满意的薪水
        }
        return res;
    }

    class Worker implements Comparable<Worker> {
        int wage, quality;

        Worker(int q, int w) {
            this.wage = w;
            this.quality = q;
        }

        public double ratio() {
            return (double) wage / quality;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio(), other.ratio());
        }
    }
}
