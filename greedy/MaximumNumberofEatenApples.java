package greedy;

import java.util.PriorityQueue;

/**
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 * 示例 1：
 *
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 20201227
 */
public class MaximumNumberofEatenApples {
    /**
     * 关键：怎么才能不随每次都改变苹果过期的剩余时间？找不变量：过期的日期。长出的日期 + expire时间 = 过期的日期
     */
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length, res = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]); // a[0]: 数量 a[1]: 过期日期
        for (int i = 0; i < n || !q.isEmpty(); i++) {
            // 移除过期的
            while (!q.isEmpty()) {
                if (q.peek()[1] <= i) {
                    q.poll();
                } else {
                    break;
                }
            }
            // 添加今天长出的
            if (i < n && apples[i] > 0 && days[i] > 0) q.offer(new int[]{apples[i], days[i] + i});
            // 吃掉今天的份
            if (!q.isEmpty()) {
                q.peek()[0]--;
                if (q.peek()[0] == 0) q.poll();
                res++;
            }
        }
        return res;
    }
}
