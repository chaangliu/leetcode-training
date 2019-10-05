package greedy;

import java.util.Arrays;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * <p>
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 * <p>
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 * <p>
 * 20190527
 */
public class TaskScheduler {
    /**
     * 题意：有一串任务用大写英文字母表示，同样的任务之间必须有n个间隔，间隔可以做别的任务或者idle。问最短需要多长时间完成所有任务。，
     * Approach 1. sort（Greedy）
     * 这题的思路是按照出现次数从多到少模拟插入数字（每个周期结束就重新计算出现次数（所以是greedy），因为有可能n很小，前几次插入集中用到出现最多的数字）
     * 总之，是保证每个周期内的数字都是不同的，如果不够（map[i] == 0的情况），就插入idle时间
     * 第二种写法就是用最大堆（PriorityQueue），node.val是出现次数；写起来基本一样，就是每次周期结束之后不需要排序了，只需要把poll出来的node减去1再add回去即可。
     * 【类似题目】
     * 1054. Distant Barcodes，358. Rearrange String
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) map[c - 'A']++;
        Arrays.sort(map);//不用管字母是什么
        int res = 0;
        while (map[25] > 0) {
            int i = 0;
            //每个周期开始时，按照【剩余】出现次数从高到低去schedule
            while (i <= n) {
                if (map[25] == 0) break;//最后一个周期后面不需要加idle时间
                if (i <= 25 && map[25 - i] > 0) map[25 - i]--;//模拟插入
                i++;
                res++;
            }
            Arrays.sort(map);
        }
        return res;
    }
}
