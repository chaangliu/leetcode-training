package greedy;

import java.util.PriorityQueue;

/**
 * You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built. A block can only be built by exactly one worker.
 * <p>
 * A worker can either split into two workers (number of workers increases by one) or build a block then go home. Both decisions cost some time.
 * <p>
 * The time cost of spliting one worker into two workers is given as an integer split. Note that if two workers split at the same time, they split in parallel so the cost would be split.
 * <p>
 * Output the minimum time needed to build all blocks.
 * <p>
 * Initially, there is only one worker.
 * Example 1:
 * <p>
 * Input: blocks = [1], split = 1
 * Output: 1
 * Explanation: We use 1 worker to build 1 block in 1 time unit.
 * Example 2:
 * <p>
 * Input: blocks = [1,2], split = 5
 * Output: 7
 * Explanation: We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.
 * Example 3:
 * <p>
 * Input: blocks = [1,2,3], split = 1
 * Output: 4
 * Explanation: Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
 * Then, use the two unassigned workers to build the first two blocks.
 * The cost is 1 + max(3, 1 + max(1, 2)) = 4.
 * Constraints:
 * <p>
 * 1 <= blocks.length <= 1000
 * 1 <= blocks[i] <= 10^5
 * 1 <= split <= 100
 * 20190924
 */
public class MinimumTimeToBuildBlocks {
    /**
     * 这题是说，一开始有一个工人，要建造一些blocks；工人可以克隆，克隆需要时间。最小需要多少时间。
     * 注意一个人只能建造一个block，不能连续建，所以必须分裂成blocks的长度那么多人。那么怎么分裂？肯定是最先分裂出来的去做时间最久的，剩下一个人不停地分裂。
     * 这么模拟下来，用贪心的做法就行。
     * 但是这题是hard（我没思路），过得人很少。可能因为这样，很多人用了更复杂的二分和dp做法？
     **/
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : blocks) queue.offer(num);
        //if (blocks.length == 1) return blocks[0];
        while (queue.size() >= 2) {
            int t1 = queue.poll();
            int t2 = queue.poll();
            queue.offer(split + Math.max(t1, t2));//注意一个人只能建造一个block，不能连续建
        }
        return queue.poll();
    }
}
